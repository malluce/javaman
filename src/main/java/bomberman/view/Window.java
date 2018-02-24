package bomberman.view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bomberman.controller.FirstPlayerKeyListener;
import bomberman.model.ArenaI;
import bomberman.model.Game;

public class Window extends JFrame {
	private Game game;
	private BufferedImage img;
	private JLabel imgLabel;
	private FirstPlayerKeyListener firstPlayerListener;

	public Window(Game game, int size) {
		this.game = game;
		this.setSize(new Dimension(size, size));
		img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		imgLabel = new JLabel(new ImageIcon(img));
		this.add(imgLabel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("javaman");
		firstPlayerListener = new FirstPlayerKeyListener(game.getPlayers().get(0));
		this.addKeyListener(firstPlayerListener);
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

	public FirstPlayerKeyListener getFirstPlayerKeyListener() {
		return firstPlayerListener;
	}
}
