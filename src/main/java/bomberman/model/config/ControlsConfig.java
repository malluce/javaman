package bomberman.model.config;

/**
 * Represents the configuration of the controls for one player (see {@link bomberman.model.player.Player}).
 * 
 * @author Felix Bachmann
 *
 */
public class ControlsConfig {
	private int up;
	private int down;
	private int left;
	private int right;
	private int plant;
	private int pause;

	/**
	 * Creates a new instance with supplied key codes.
	 * 
	 * @param up
	 *            {@link java.awt.event.KeyEvent} constant for a virtual key for moving upwards
	 * @param down
	 *            {@link java.awt.event.KeyEvent} constant for a virtual key for moving downwards
	 * @param left
	 *            {@link java.awt.event.KeyEvent} constant for a virtual key for moving leftwards
	 * @param right
	 *            {@link java.awt.event.KeyEvent} constant for a virtual key for moving rightwards
	 * @param plant
	 *            {@link java.awt.event.KeyEvent} constant for a virtual key for planting a bomb
	 * @param pause
	 *            {@link java.awt.event.KeyEvent} constant for a virtual key for (un)pausing the game
	 */
	public ControlsConfig(int up, int down, int left, int right, int plant, int pause) {
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.plant = plant;
		this.pause = pause;
	}

	/**
	 * Getter for the up virtual key constant from {@link java.awt.event.KeyEvent}.
	 * 
	 * @return up constant
	 */
	public int getUp() {
		return up;
	}

	/**
	 * Getter for the down virtual key constant from {@link java.awt.event.KeyEvent}.
	 * 
	 * @return down constant
	 */
	public int getDown() {
		return down;
	}

	/**
	 * Getter for the left virtual key constant from {@link java.awt.event.KeyEvent}.
	 * 
	 * @return left constant
	 */
	public int getLeft() {
		return left;
	}

	/**
	 * Getter for the right virtual key constant from {@link java.awt.event.KeyEvent}.
	 * 
	 * @return right constant
	 */
	public int getRight() {
		return right;
	}

	/**
	 * Getter for the plant virtual key constant from {@link java.awt.event.KeyEvent}.
	 * 
	 * @return plant constant
	 */
	public int getPlant() {
		return plant;
	}

	/**
	 * Getter for the (un)pause virtual key constant from {@link java.awt.event.KeyEvent}.
	 * 
	 * @return (un)pause constant
	 */
	public int getPause() {
		return pause;
	}

}
