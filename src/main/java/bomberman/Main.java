package bomberman;

import java.awt.image.BufferedImage;

import bomberman.view.Window;

public class Main {

	public static void main(String[] args) {
		Window win = new Window();

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
