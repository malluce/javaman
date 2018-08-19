package bomberman.view.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import bomberman.model.Game;
import bomberman.model.config.ControlsConfig;
import bomberman.model.player.Player;

/**
 * This class handles the input by a player via keyboard.
 * 
 * @author Felix Bachmann
 *
 */
public class PlayerInputListener implements KeyListener {
	private Game game;
	/*
	 * Keeps track of the currently hold movement keys to constantly move the player while the key is hold.
	 */
	private HashMap<Integer, Boolean> isPressed = new HashMap<Integer, Boolean>();
	private Player player;
	private final int keyCodeLeft;
	private final int keyCodeRight;
	private final int keyCodeUp;
	private final int keyCodeDown;
	private final int keyCodePlant;
	private final int keyCodePause;

	/**
	 * Creates a new input handler.
	 * 
	 * @param game
	 *            the game which is influences by the inputs
	 * @param player
	 *            the player to which the input will be redirected
	 * @param conf
	 *            the controls config of this player
	 */
	public PlayerInputListener(Game game, Player player, ControlsConfig conf) {
		this.game = game;
		this.player = player;
		this.keyCodeLeft = conf.getLeft();
		this.keyCodeRight = conf.getRight();
		this.keyCodeUp = conf.getUp();
		this.keyCodeDown = conf.getDown();
		this.keyCodePlant = conf.getPlant();
		this.keyCodePause = conf.getPause();
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
	 * Invoked when a key is pressed. This key is added to the pressed keys if it is a movement key, i.e. until the key
	 * is released movement may be invoked.
	 * 
	 * @param arg0
	 *            the event triggered by the pressed key
	 */
	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		if (keyCode == keyCodePlant) {
			// plant key
			invokePlant();
		} else if (keyCode == keyCodePause) {
			game.toggleRunning();
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
