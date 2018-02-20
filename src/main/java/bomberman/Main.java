package bomberman;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import bomberman.model.AbstractPlayer;
import bomberman.model.Game;
import bomberman.model.PlayerOne;
import bomberman.model.PlayerTwo;
import bomberman.model.StandardArena;
import bomberman.view.Window;

public class Main {

	public static void main(String[] args) {
		PlayerOne playerOne = new PlayerOne();
		PlayerTwo playerTwo = new PlayerTwo();
		ArrayList<AbstractPlayer> players = new ArrayList<AbstractPlayer>();
		players.add(playerOne);
		players.add(playerTwo);
		StandardArena arena = new StandardArena(15);
		Game game = new Game(arena, players, null);
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
