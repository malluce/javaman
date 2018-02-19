package bomberman.view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bomberman.model.StandardArena;

public class Window extends JFrame {
	private BufferedImage img = new BufferedImage(480, 480, BufferedImage.TYPE_INT_RGB);
	private StandardArena arena = new StandardArena(15);
	private JLabel imgLabel = new JLabel(new ImageIcon(img));

	public Window() {
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

	public StandardArena getArena() {
		return arena;
	}

	public JLabel getImgLabel() {
		return imgLabel;
	}
}
