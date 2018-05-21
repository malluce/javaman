package bomberman.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import bomberman.model.Player;

/**
 * This class handles the input by a player via keyboard.
 * 
 * @author Felix Bachmann
 *
 */
public class PlayerInputHandler implements KeyListener {
	private HashMap<Integer, Boolean> isPressed = new HashMap<Integer, Boolean>();
	private Player player;
	private final int keyCodeLeft;
	private final int keyCodeRight;
	private final int keyCodeUp;
	private final int keyCodeDown;
	private final int keyCodePlant;

	/**
	 * Creates a new input handler.
	 * 
	 * @param player
	 *            the player to which the input will be redirected
	 * @param keyCodeLeft
	 *            the keyCode constant which is used for moving left
	 * @param keyCodeRight
	 *            the keyCode constant which is used for moving right
	 * @param keyCodeUp
	 *            the keyCode constant which is used for moving up
	 * @param keyCodeDown
	 *            the keyCode constant which is used for moving down
	 * @param keyCodePlant
	 *            the keyCode constant which is used for planting a bomb
	 */
	public PlayerInputHandler(Player player, final int keyCodeLeft, final int keyCodeRight, final int keyCodeUp,
			final int keyCodeDown, final int keyCodePlant) {
		this.player = player;
		this.keyCodeLeft = keyCodeLeft;
		this.keyCodeRight = keyCodeRight;
		this.keyCodeUp = keyCodeUp;
		this.keyCodeDown = keyCodeDown;
		this.keyCodePlant = keyCodePlant;
	}

	/**
	 * Invokes movement for hold keys.
	 */
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

	/**
	 * Invoked when a key is pressed. This key is added to the pressed keys, i.e. until the key is released movement may
	 * be invoked.
	 * 
	 * @param arg0
	 *            the event triggered by the pressed key
	 */
	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		if (keyCode == keyCodePlant) {
			// plant key
			invokePlant();
		} else {
			// movement key
			isPressed.put(keyCode, true);
			invokeMovement(keyCode);
		}
	}

	private void invokePlant() {
		this.player.plantBomb();
	}

	/**
	 * Invokes the movement that may be triggered by pressing the button with a keyCode. Just does something if the
	 * keyCode matches one of the keyCodes handed over for this PlayerInputHandler inside the constructor.
	 * 
	 * @param keyCode
	 *            the keyCode of the key which has been pressed
	 */
	private void invokeMovement(int keyCode) {
		if (keyCode == keyCodeLeft) {
			player.moveLeft();
		} else if (keyCode == keyCodeRight) {
			player.moveRight();
		} else if (keyCode == keyCodeUp) {
			player.moveUp();
		} else if (keyCode == keyCodeDown) {
			player.moveDown();
		}
	}

	/**
	 * Invoked when a key is released. This key is removed from the pressed keys, i.e. no more movement for this key is
	 * invoked until pressed again.
	 * 
	 * @param arg0
	 *            the event triggered by the pressed key
	 */
	public void keyReleased(KeyEvent arg0) {
		isPressed.put(arg0.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// method not used, only Unicode inputs are processed by this method. this is not what we want
	}

}
