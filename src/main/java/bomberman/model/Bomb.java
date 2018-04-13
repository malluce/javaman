package bomberman.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
	private long maxTicks;
	private long explosionTicks;
	private long maxExplosionTicks;
	private BombState state;
	private Player player;

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
	 *            the amount of ticks this bomb needs for the explosion to take
	 *            place
	 * @param player
	 *            the player which planted this bomb
	 */
	public Bomb(String spriteName, int radius, int ticks, TileCoordinate tileCoord, int explosionTicks, Player player) {
		if (radius < 1 || ticks < 1) {
			throw new IllegalArgumentException("Radius and ticks must be at least 1");
		}

		this.SPRITE_NAME = spriteName;
		this.radius = radius;
		this.ticks = ticks;
		this.maxTicks = ticks;
		this.tileCoord = tileCoord;
		this.state = BombState.TICKING;
		this.explosionTicks = explosionTicks;
		this.maxExplosionTicks = explosionTicks;
		this.player = player;
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

	public long getTicks() {
		return ticks;
	}

	public long getMaxTicks() {
		return maxTicks;
	}

	public long getExplosionTicks() {
		return explosionTicks;
	}

	public long getMaxExplosionTicks() {
		return maxExplosionTicks;
	}

	public int getRadius() {
		return radius;
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource(SPRITE_NAME);
	}

	/**
	 * If the bomb is ticking, ticks down the tick counter of this bomb. If the
	 * counter reaches 0 the bomb will explode. If the bomb is exploding, the
	 * explosion tick counter will be counted down. If the counter reaches 0 the
	 * bomb will stop exploding.
	 */
	public void tick() {
		if (isTicking()) {
			ticks--;
			if (ticks == 0) {
				state = BombState.EXPLODING;
			}
		} else if (isExploding()) {
			explosionTicks--;
			if (explosionTicks == 0) {
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

	public List<TileCoordinate> getAffectedTileCoordinates() {
		TileCoordinate coord = getTileCoordinate();
		List<TileCoordinate> affectedCoordinates = new ArrayList<TileCoordinate>();
		int radius = getRadius();
		int row = coord.getRow();
		int col = coord.getColumn();

		affectedCoordinates.add(coord);

		for (int r = row - radius; r <= row + radius; r++) {
			affectedCoordinates.add(new TileCoordinate(col, r));
		}

		for (int c = col - radius; c <= col + radius; c++) {
			affectedCoordinates.add(new TileCoordinate(c, row));
		}

		return affectedCoordinates;
	}

}
