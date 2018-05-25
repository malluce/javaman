package bomberman.model;

import java.net.URL;

/**
 * Represents a tile that is empty, so is passable by players.
 * 
 * @author Felix Bachmann
 *
 */
public final class EmptyTile extends AbstractTile {
	private static EmptyTile instance = null;
	private final String spriteName = "empty_tile.png";

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
		return true;
	}

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource(spriteName);
	}

}
