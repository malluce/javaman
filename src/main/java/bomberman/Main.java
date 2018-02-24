package bomberman;

import java.util.ArrayList;

import bomberman.controller.FirstPlayerKeyListener;
import bomberman.model.Game;
import bomberman.model.Player;
import bomberman.model.StandardArena;
import bomberman.model.TileCoordinate;
import bomberman.view.IllegalRenderSizesException;
import bomberman.view.Renderer;
import bomberman.view.Window;

public class Main {

	public static final int TILE_SIZE = 32;

	public static final int GAME_SIZE = 10;

	public static void main(String[] args) throws IllegalRenderSizesException {

		StandardArena arena = new StandardArena(GAME_SIZE);
		TileCoordinate[] spawnPoints = arena.getSpawnPoints();
		Player playerOne = new Player("player_one.png", spawnPoints[0].toXYCoordinates(TILE_SIZE), 1, null);
		Player playerTwo = new Player("player_two.png", spawnPoints[1].toXYCoordinates(TILE_SIZE), 1, null);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(playerOne);
		players.add(playerTwo);
		Game game = new Game(arena, players);
		playerOne.setGame(game);
		playerTwo.setGame(game);
		Window win = new Window(game, GAME_SIZE * TILE_SIZE);
		FirstPlayerKeyListener firstPlayerListener = win.getFirstPlayerKeyListener();
		Renderer renderer = new Renderer(GAME_SIZE * TILE_SIZE, TILE_SIZE);
		while (true) {
			changeState();
			firstPlayerListener.updateFromPressedKeys();
			renderer.render(game, win.getImg());
			win.repaint();
		}
	}

	static void handleInput() {

	}

	static void changeState() {

	}

}
