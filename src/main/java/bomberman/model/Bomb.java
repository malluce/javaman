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
	private long ticks;
	private long explosionTicks;
	private BombState state;

	/**
	 * Creates a new bomb.
	 * 
	 * @param spriteName
	 *            the name of the sprite on disk used for rendering bombs
	 * @param radius
	 *            the radius of an explosion of this bomb
	 * @param ticks
	 *            the amount of ticks this bomb needs until it explodes
	 * @param tileCoord
	 *            the coordinate of the tile at which this bomb is located
	 * @param explosionTicks
	 *            the amount of ticks this bomb needs for the explosion to take place
	 */
	public Bomb(String spriteName, int radius, int ticks, TileCoordinate tileCoord, int explosionTicks) {
		if (radius < 1 || ticks < 1) {
			throw new IllegalArgumentException("Radius and ticks must be at least 1");
		}

		this.SPRITE_NAME = spriteName;
		this.radius = radius;
		this.ticks = ticks;
		this.tileCoord = tileCoord;
		this.state = BombState.TICKING;
		this.explosionTicks = explosionTicks;
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

	// TODO overide getSprite -> sprite of bomb is changing with its state

	/**
	 * If the bomb is ticking, ticks down the tick counter of this bomb. If the counter reaches 0 the bomb will explode.
	 * If the bomb is exploding, the explosion tick counter will be counted down. If the counter reaches 0 the bomb will
	 * stop exploding.
	 */
	public void tick() {
		if (isTicking()) {
			ticks--;
			if (ticks == 0) {
				System.out.println("exploding now");
				state = BombState.EXPLODING;
			}
		} else if (isExploding()) {
			explosionTicks--;
			if (explosionTicks == 0) {
				System.out.println("finished");
				state = BombState.FINISHED;
			}
		}
	}

	public boolean isTicking() {
		return state == BombState.TICKING;
	}

	public boolean isExploding() {
		return state == BombState.EXPLODING;
	}

	public boolean hasFinished() {
		return state == BombState.FINISHED;
	}

}
