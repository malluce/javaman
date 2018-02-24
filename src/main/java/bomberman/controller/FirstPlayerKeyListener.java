package bomberman.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import bomberman.model.Player;

public class FirstPlayerKeyListener implements KeyListener {
	private Player player;

	public FirstPlayerKeyListener(Player player) {
		this.player = player;
	}

	public void keyPressed(KeyEvent arg0) {
		int pressedChar = arg0.getKeyCode();
		System.out.println("Pressed " + pressedChar);
		switch (pressedChar) {
		case KeyEvent.VK_LEFT:
			player.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			player.moveRight();
			break;
		case KeyEvent.VK_UP:
			player.moveUp();
			break;
		case KeyEvent.VK_DOWN:
			player.moveDown();
			break;
		default:
			System.err.println("Someone pressed " + pressedChar + ", this key does nothing.");
			break;
		}

	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
