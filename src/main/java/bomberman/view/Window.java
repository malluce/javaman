package bomberman.view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bomberman.controller.PlayerInputHandler;
import bomberman.model.Game;

/**
 * Represents a window in which the game is displayed.
 * 
 * @author Felix Bachmann
 *
 */
public class Window extends JFrame {
	private BufferedImage img;
	private JLabel imgLabel;

	/**
	 * Creates a new window and displays it.
	 * 
	 * @param game
	 *            the game displayed in this window
	 * @param inputHandlers
	 *            keyListeners, added to this window
	 */
	public Window(Game game, List<PlayerInputHandler> inputHandlers) {
		int size = game.getGameSize() * game.getTileSize();
		this.setSize(new Dimension(size, size));
		img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		imgLabel = new JLabel(new ImageIcon(img));
		this.add(imgLabel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("javaman");
		for (PlayerInputHandler handler : inputHandlers) {
			this.addKeyListener(handler);
		}
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Returns the image which actually displays the game.
	 * 
	 * @return the image
	 */
	public BufferedImage getImg() {
		return img;
	}

}
