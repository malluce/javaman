package bomberman.model;

import java.net.URL;

/**
 * Represents a bomb.
 * 
 * @author Felix Bachmann
 *
 */
public class Bomb extends AbstractTile {
	private final String SPRITE_NAME;
	private TileCoordinate tileCoord;
	private int radius;
	private int ticks;
	private boolean ticking;
	private boolean exploding;

	/**
	 * Creates a new bomb.
	 * 
	 * @param spriteName
	 *            the name of the sprite on disk used for rendering bombs
	 * @param radius
	 *            the radius of an explosion of this bomb
	 * @param ticks
	 *            the amount of ticks this bomb needs for exploding
	 * @param tileCoord
	 *            the coordinate of the tile at which this bomb is located
	 */
	public Bomb(String spriteName, int radius, int ticks, TileCoordinate tileCoord) {
		this.SPRITE_NAME = spriteName;
		this.radius = radius;
		this.ticks = ticks;
		this.tileCoord = tileCoord;
		this.ticking = true;
		this.exploding = false;
	}

	@Override
	public boolean isPassable() {
		return false;
	}

	@Override
	public boolean isDestroyable() {
		return false;
	}

	/**
	 * Returns the TileCoordinate of this bomb.
	 * 
	 * @return the tile coordinate
	 */
	public TileCoordinate getTileCoordinate() {
		return this.tileCoord;
	}

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource(SPRITE_NAME);
	}

}
