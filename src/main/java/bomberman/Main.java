package bomberman;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import bomberman.controller.PlayerInputHandler;
import bomberman.model.ArenaI;
import bomberman.model.Game;
import bomberman.model.IllegalIdRequestException;
import bomberman.model.Player;
import bomberman.model.StandardArena;
import bomberman.model.TileCoordinate;
import bomberman.view.Renderer;
import bomberman.view.Window;

/**
 * The main class, contains the game setup and game loop.
 * 
 * @author Felix Bachmann
 *
 */
public final class Main {

	private static final int FPS = 60;

	private static final int TPS = 120;

	/**
	 * The size of a tile in pixels. Because all tiles are quadratic this constant is the height as well as the width of
	 * a tile.
	 */
	public static final int TILE_SIZE = 64;

	/**
	 * The size of the game, that is the amount of tiles per row and per column. (game is also quadratic)
	 */
	public static final int GAME_SIZE = 10;

	private static final double MILLIS_PER_RENDER = 1000 / FPS;

	private static final double MILLIS_PER_TICK = 1000 / TPS;

	private Main() {

	}

	/**
	 * Contains the main game logic.
	 * 
	 * @param args
	 *            command line arguments
	 * @throws IllegalIdRequestException
	 *             is thrown if more {@link Player}s are supplied to a {@link Game} than indicated by its maxPlayers
	 *             value.
	 * @throws InterruptedException
	 *             is thrown if a thread is interrupted while waiting inside game loop
	 */
	public static void main(String[] args) throws IllegalIdRequestException, InterruptedException {

		ArenaI arena = new StandardArena(GAME_SIZE);
		TileCoordinate[] spawnPoints = arena.getSpawnPoints();
		Player playerOne = new Player("player_one.png", spawnPoints[0].toXYCoordinates(TILE_SIZE), 1, 1, null);
		Player playerTwo = new Player("player_two.png", spawnPoints[1].toXYCoordinates(TILE_SIZE), 1, 1, null);

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
		BufferedImage winImage = win.getImg();
		Renderer renderer = new Renderer(game, win, winImage.createGraphics());

		boolean gameRunning = true;
		long nextTick = System.currentTimeMillis();
		long nextRender = nextTick;

		while (gameRunning) {
			long loopStart = System.currentTimeMillis();

			if (loopStart >= nextRender) {
				renderer.render();
				do {
					nextRender += MILLIS_PER_RENDER;
				} while (loopStart >= nextRender);
			}

			if (loopStart >= nextTick) {
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
}
