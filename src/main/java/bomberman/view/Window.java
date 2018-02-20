package bomberman.view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bomberman.model.ArenaI;
import bomberman.model.Game;

public class Window extends JFrame {
	private Game game;
	private BufferedImage img = new BufferedImage(480, 480, BufferedImage.TYPE_INT_RGB);
	private JLabel imgLabel = new JLabel(new ImageIcon(img));

	public Window(Game game) {
		this.game = game;
		this.setSize(new Dimension(480, 480));
		this.add(imgLabel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	public BufferedImage getImg() {
		return img;
	}

	public ArenaI getArena() {
		return game.getArena();
	}

	public Game getGame() {
		return game;
	}

	public JLabel getImgLabel() {
		return imgLabel;
	}
}
