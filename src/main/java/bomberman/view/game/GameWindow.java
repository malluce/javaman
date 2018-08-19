package bomberman.view.game;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import bomberman.model.Game;
import bomberman.view.listener.PlayerInputListener;

/**
 * Represents a window in which the game is displayed.
 * 
 * @author Felix Bachmann
 *
 */
public class GameWindow extends JFrame {
	private BufferedImage img;
	private JLabel imgLabel;

	/**
	 * Creates a new window and displays it.
	 * 
	 * @param game
	 *            the game displayed in this window
	 * @param inputListeners
	 *            keyListeners, added to this window
	 */
	public GameWindow(Game game, List<PlayerInputListener> inputListeners) {
		int size = game.getGameSize() * game.getTileSize();
		img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		imgLabel = new JLabel(new ImageIcon(img));

		SwingUtilities.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}

			this.setSize(new Dimension(size, size));

			this.add(imgLabel);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);

			this.setResizable(false);

			this.setTitle("javaman");
			for (PlayerInputListener handler : inputListeners) {
				this.addKeyListener(handler);
			}
			this.pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		});

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
