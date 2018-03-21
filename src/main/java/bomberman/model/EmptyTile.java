package bomberman.model;

import java.net.URL;

/**
 * Represents a tile that is empty, so is passable by players.
 * 
 * @author Felix Bachmann
 *
 */
public class EmptyTile extends AbstractTile {
	private static EmptyTile instance = null;

	private EmptyTile() {

	}

	/**
	 * Returns an instance of this class. Always returns the same instance.
	 * 
	 * @return an instance of EmptyTile
	 */
	public static EmptyTile getInstance() {
		if (instance == null) {
			instance = new EmptyTile();
		}
		return instance;
	}

	@Override
	public boolean isPassable() {
		return true;
	}

	@Override
	public boolean isDestroyable() {
		return false;
	}

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource("empty_tile.png");
	}

}
