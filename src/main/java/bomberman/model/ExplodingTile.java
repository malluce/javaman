package bomberman.model;

import java.net.URL;

public class ExplodingTile extends AbstractTile {
	private static ExplodingTile instance = null;
	private final String SPRITE_NAME = "explosion.png";

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
		return getClass().getClassLoader().getResource(SPRITE_NAME);
	}

}
