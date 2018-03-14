package bomberman.view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bomberman.controller.PlayerInputHandler;
import bomberman.model.ArenaI;
import bomberman.model.Game;

public class Window extends JFrame {
	private Game game;
	private BufferedImage img;
	private JLabel imgLabel;

	public Window(Game game, List<PlayerInputHandler> inputHandlers) {
		int size = game.getGameSize() * game.getTileSize();
		this.game = game;
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
