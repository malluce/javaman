package bomberman;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import bomberman.model.Game;
import bomberman.model.Player;
import bomberman.model.StandardArena;
import bomberman.model.TileCoordinate;
import bomberman.view.Window;

public class Main {

	private static final int TILE_SIZE = 32; // TODO encapsulate later in view renderer
	private static final int PLAYER_SIZE = 32; // TODO encapsulate later in view renderer

	public static void main(String[] args) {
		StandardArena arena = new StandardArena(15);
		TileCoordinate[] spawnPoints = arena.getSpawnPoints();
		Player playerOne = new Player("player_one.png", spawnPoints[0].toXYCoordinates(TILE_SIZE), 1);
		Player playerTwo = new Player("player_two.png", spawnPoints[1].toXYCoordinates(TILE_SIZE), 1);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(playerOne);
		players.add(playerTwo);
		Game game = new Game(arena, players);
		Window win = new Window(game);

		while (true) {
			handleInput();
			changeState();
			render(win);

		}
	}

	static void handleInput() {

	}

	static void changeState() {

	}

	static void render(Window win) {
		BufferedImage curSprite = null;
		BufferedImage windowImage = win.getImg();
		Player p1 = win.getGame().getPlayers().get(0);
		Player p2 = win.getGame().getPlayers().get(1);
		int p1X = p1.getX();
		int p1Y = p1.getY();
		int p2X = p2.getX();
		int p2Y = p2.getY();

		// TODO add transparency of players

		for (int i = 0; i < 480; i++) {
			for (int j = 0; j < 480; j++) {
				if (i <= (p1X + PLAYER_SIZE - 1) && i >= p1X && j <= (p1Y + PLAYER_SIZE - 1) && j >= p1Y) {
					// render player 1
					curSprite = win.getGame().getPlayers().get(0).getSprite();
				} else if (i <= (p2X + PLAYER_SIZE - 1) && i >= p2X && j <= (p2Y + PLAYER_SIZE - 1) && j >= p2Y) {
					// render player 2
					curSprite = win.getGame().getPlayers().get(1).getSprite();
				} else {
					// render tile
					curSprite = win.getArena().getCurrentMap()[i / 32][j / 32].getSprite();

				}
				windowImage.setRGB(i, j, curSprite.getRGB(i % 32, j % 32));
			}
		}
		win.repaint();
	}
}
