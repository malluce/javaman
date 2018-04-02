package bomberman;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import bomberman.controller.PlayerInputHandler;
import bomberman.model.Game;
import bomberman.model.IllegalIdRequestException;
import bomberman.model.Player;
import bomberman.model.StandardArena;
import bomberman.model.TileCoordinate;
import bomberman.view.Renderer;
import bomberman.view.Window;

public class Main {
	private static final int FPS = 144;

	private static final int TPS = 100;

	private static final int TILE_SIZE = 32;

	private static final int GAME_SIZE = 10;

	private static final double MILLIS_PER_RENDER = 1000 / FPS;

	private static final double MILLIS_PER_TICK = 1000 / TPS;

	public static void main(String[] args) throws IllegalIdRequestException, InterruptedException {

		StandardArena arena = new StandardArena(GAME_SIZE);
		TileCoordinate[] spawnPoints = arena.getSpawnPoints();
		Player playerOne = new Player("player_one.png", spawnPoints[0].toXYCoordinates(TILE_SIZE), 1, null);
		Player playerTwo = new Player("player_two.png", spawnPoints[1].toXYCoordinates(TILE_SIZE), 1, null);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(playerOne);
		players.add(playerTwo);
		Game game = new Game(arena, players, TILE_SIZE, GAME_SIZE);
		playerOne.setGame(game);
		playerTwo.setGame(game);

		ArrayList<PlayerInputHandler> inputHandlers = new ArrayList<PlayerInputHandler>();
		PlayerInputHandler firstPlayerInputHandler = new PlayerInputHandler(playerOne, KeyEvent.VK_LEFT,
				KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_NUMPAD0);
		PlayerInputHandler secondPlayerInputHandler = new PlayerInputHandler(playerTwo, KeyEvent.VK_A, KeyEvent.VK_D,
				KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_SPACE);
		inputHandlers.add(firstPlayerInputHandler);
		inputHandlers.add(secondPlayerInputHandler);

		Window win = new Window(game, inputHandlers);
		Renderer renderer = new Renderer(game);
		BufferedImage winImage = win.getImg();

		boolean gameRunning = true;
		long nextTick = System.currentTimeMillis();
		long nextRender = nextTick;

		while (gameRunning) {
			long loopStart = System.currentTimeMillis();

			if (loopStart >= nextRender) {
				renderer.render(winImage);
				win.repaint();
				do {
					nextRender += MILLIS_PER_RENDER;
				} while (loopStart >= nextRender);
			}

			if (loopStart >= nextTick) {
				changeState();
				game.tickBombs();
				for (PlayerInputHandler inputHandler : inputHandlers) {
					inputHandler.updateFromPressedKeys();
				}
				do {
					nextTick += MILLIS_PER_TICK;
				} while (loopStart >= nextTick);
			}

			long loopEnd = System.currentTimeMillis();
			long delay = Math.min(nextTick - loopEnd, nextRender - loopEnd);
			if (delay > 0) {
				Thread.sleep(delay);
			}
		}
	}

	static void changeState() {

	}

}
