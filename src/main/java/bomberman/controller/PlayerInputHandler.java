package bomberman.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import bomberman.model.Player;

public class PlayerInputHandler implements KeyListener {
	private HashMap<Integer, Boolean> isPressed = new HashMap<Integer, Boolean>();
	private Player player;
	private final int keyCodeLeft;
	private final int keyCodeRight;
	private final int keyCodeUp;
	private final int keyCodeDown;

	public PlayerInputHandler(Player player, int keyCodeLeft, int keyCodeRight, int keyCodeUp, int keyCodeDown) {
		this.player = player;
		this.keyCodeLeft = keyCodeLeft;
		this.keyCodeRight = keyCodeRight;
		this.keyCodeUp = keyCodeUp;
		this.keyCodeDown = keyCodeDown;
	}

	public void updateFromPressedKeys() {
		Set<Integer> keys = isPressed.keySet();
		Iterator<Integer> iter = keys.iterator();
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
		if (keyCode == keyCodeLeft) {
			System.out.println("Player " + player.getId() + ": Try to move left.");
			player.moveLeft();
		} else if (keyCode == keyCodeRight) {
			System.out.println("Player  " + player.getId() + ": Try to move right.");
			player.moveRight();
		} else if (keyCode == keyCodeUp) {
			System.out.println("Player " + player.getId() + ": Try to move up.");
			player.moveUp();
		} else if (keyCode == keyCodeDown) {
			System.out.println("Player " + player.getId() + ": Try to move down.");
			player.moveDown();
		} else {

		}
	}

	public void keyReleased(KeyEvent arg0) {
		isPressed.put(arg0.getKeyCode(), false);
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO

	}

}
