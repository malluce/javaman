package bomberman.model;

import java.net.URL;

/**
 * Represents a tile that may be destroyed by exploding bombs.
 * 
 * @author Felix Bachmann
 *
 */
public class DestroyableTile extends AbstractTile {
	private static DestroyableTile instance = null;

	private DestroyableTile() {

	}

	/**
	 * Returns an instance of this class. Always returns the same instance.
	 * 
	 * @return an instance of DestroyableTile
	 */
	public static DestroyableTile getInstance() {
		if (instance == null) {
			instance = new DestroyableTile();
		}
		return instance;
	}

	@Override
	public boolean isPassable() {
		return false;
	}

	@Override
	public boolean isDestroyable() {
		return true;
	}

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource("destroyable_block.png");
	}

}
