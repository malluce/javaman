package bomberman;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import bomberman.model.Game;
import bomberman.model.Player;
import bomberman.model.Point;
import bomberman.model.StandardArena;
import bomberman.view.Window;

public class Main {

	public static void main(String[] args) {
		StandardArena arena = new StandardArena(15);
		Point[] spawnPoints = arena.getSpawnPoints();
		Player playerOne = new Player("player_one.png", spawnPoints[0], 1);
		Player playerTwo = new Player("player_two.png", spawnPoints[1], 1);

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
		Player p1 = win.getGame().getPlayers().get(0);
		Player p2 = win.getGame().getPlayers().get(1);
		int p1X = p1.getX();
		int p1Y = p1.getY();
		int p2X = p2.getX();
		int p2Y = p2.getY();
		// TODO render players
		for (int i = 0; i < 480; i++) {
			for (int j = 0; j < 480; j++) {
				if (i % 32 == 0 || j % 32 == 0) {
					curSprite = win.getArena().getCurrentMap()[i / 32][j / 32].getSprite();
				}
				win.getImg().setRGB(i, j, curSprite.getRGB(i % 32, j % 32));
			}
		}
		win.repaint();
	}
}
