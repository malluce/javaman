package bomberman;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import bomberman.controller.PlayerInputHandler;
import bomberman.model.Game;
import bomberman.model.Player;
import bomberman.model.StandardArena;
import bomberman.model.TileCoordinate;
import bomberman.view.IllegalRenderSizesException;
import bomberman.view.Renderer;
import bomberman.view.Window;

public class Main {

	private static final int TILE_SIZE = 32;

	private static final int GAME_SIZE = 10;

	public static void main(String[] args) throws IllegalRenderSizesException {

		StandardArena arena = new StandardArena(GAME_SIZE);
		TileCoordinate[] spawnPoints = arena.getSpawnPoints();
		Player playerOne = new Player("player_one.png", spawnPoints[0].toXYCoordinates(TILE_SIZE), 1, null, 1);
		Player playerTwo = new Player("player_two.png", spawnPoints[1].toXYCoordinates(TILE_SIZE), 1, null, 2);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(playerOne);
		players.add(playerTwo);
		Game game = new Game(arena, players, TILE_SIZE, GAME_SIZE);
		playerOne.setGame(game);
		playerTwo.setGame(game);

		ArrayList<PlayerInputHandler> inputHandlers = new ArrayList<PlayerInputHandler>();
		PlayerInputHandler firstPlayerInputHandler = new PlayerInputHandler(playerOne, KeyEvent.VK_LEFT,
				KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
		PlayerInputHandler secondPlayerInputHandler = new PlayerInputHandler(playerTwo, KeyEvent.VK_A, KeyEvent.VK_D,
				KeyEvent.VK_W, KeyEvent.VK_S);
		inputHandlers.add(firstPlayerInputHandler);
		inputHandlers.add(secondPlayerInputHandler);

		Window win = new Window(game, inputHandlers);
		Renderer renderer = new Renderer(game);
		while (true) {
			changeState();
			for (PlayerInputHandler inputHandler : inputHandlers) {
				inputHandler.updateFromPressedKeys();
			}
			renderer.render(win.getImg());
			win.repaint();
		}
	}

	static void handleInput() {

	}

	static void changeState() {

	}

}
