package bomberman.model.tile;

import java.net.URL;

/**
 * Represents an exploding tile, it is fatal for players to reside inside one of these.
 * 
 * @author Felix Bachmann
 *
 */
public class ExplodingTile extends AbstractTile {
	private static ExplodingTile instance = null;
	private final String spriteName = "explosion.png";

	/**
	 * Returns an instance of this class. Always returns the same instance.
	 * 
	 * @return an instance of ExplodingTile
	 */
	public static ExplodingTile getInstance() {
		if (instance == null) {
			instance = new ExplodingTile();
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
		return getClass().getClassLoader().getResource(spriteName);
	}

}
