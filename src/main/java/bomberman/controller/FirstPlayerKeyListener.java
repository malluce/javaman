package bomberman.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import bomberman.model.Player;

public class FirstPlayerKeyListener implements KeyListener {
	private HashMap<Integer, Boolean> isPressed = new HashMap<Integer, Boolean>();
	private Player player;

	public FirstPlayerKeyListener(Player player) {
		this.player = player;
	}

	public void updateFromPressedKeys() {
		Set<Integer> keys = isPressed.keySet();
		Iterator iter = keys.iterator();
		while (iter.hasNext()) {
			int keyCode = (Integer) iter.next();
			if (isPressed.get(keyCode)) {
				invokeMovement(keyCode);
			}
		}
	}

	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		isPressed.put(arg0.getKeyCode(), true);
		invokeMovement(keyCode);
	}

	private void invokeMovement(int keyCode) {
		switch (keyCode) {
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
			System.err.println("Someone pressed a key which does nothing.");
			break;
		}
	}

	public void keyReleased(KeyEvent arg0) {
		isPressed.put(arg0.getKeyCode(), false);
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO

	}

}
