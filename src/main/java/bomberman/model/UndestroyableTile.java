package bomberman.model;

import java.net.URL;

/**
 * A tile that may not be destroyed by exploding bombs.
 * 
 * @author Felix Bachmann
 *
 */
public class UndestroyableTile extends AbstractTile {
	private static UndestroyableTile instance = null;

	private UndestroyableTile() {

	}

	/**
	 * Returns an instance of this class. Always returns the same instance.
	 * 
	 * @return an instance of UndestroyableTile
	 */
	public static UndestroyableTile getInstance() {
		if (instance == null) {
			instance = new UndestroyableTile();
		}
		return instance;
	}

	@Override
	public boolean isPassable() {
		return false;
	}

	@Override
	public boolean isDestroyable() {
		return false;
	}

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource("undestroyable_block.png");
	}

}
