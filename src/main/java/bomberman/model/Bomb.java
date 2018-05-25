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
	private final String spriteName;
	private TileCoordinate tileCoord;
	private int radius;
	private long ticks;
	private long maxTicks;
	private long explosionTicks;
	private long maxExplosionTicks;
	private BombState state;
	private Player player;

	/**
	 * Creates a new bomb and sets the bomb state to TICKING.
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
	 * @param player
	 *            the player which planted this bomb
	 */
	public Bomb(String spriteName, int radius, int ticks, TileCoordinate tileCoord, int explosionTicks, Player player) {
		if (radius < 1 || ticks < 1) {
			throw new IllegalArgumentException("Radius and ticks must be at least 1");
		}

		this.spriteName = spriteName;
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

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource(spriteName);
	}

	/**
	 * Returns the TileCoordinate of this bomb.
	 * 
	 * @return the tile coordinate
	 */
	public TileCoordinate getTileCoordinate() {
		return this.tileCoord;
	}

	/**
	 * Returns the ticks left until the bomb ends ticking and starts exploding.
	 * 
	 * @return the ticks left until TICKING ends and EXPLODING starts
	 */
	public long getTicks() {
		return ticks;
	}

	/**
	 * Returns the maximum amount of ticks this bomb has for the time between planting and exploding.
	 * 
	 * @return the maximum amount of ticks for the time between PLANTING and EXPLODING
	 */
	public long getMaxTicks() {
		return maxTicks;
	}

	/**
	 * Returns the ticks left until the explosion period ends.
	 * 
	 * @return the ticks left until EXPLODING ends
	 */
	public long getExplosionTicks() {
		return explosionTicks;
	}

	/**
	 * Returns the maximum amount of ticks this bomb has for the time between EXPLODING start and end.
	 * 
	 * @return the maximum amount of ticks for the EXPLODING period
	 */
	public long getMaxExplosionTicks() {
		return maxExplosionTicks;
	}

	/**
	 * Returns the radius of the bomb, i.e. the number of tiles affected by this bomb in each direction.
	 * 
	 * @return the explosion radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Returns the player who planted this bomb.
	 * 
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Returns whether this bomb is ticking right now.
	 * 
	 * @return true if the bomb is ticking, false otherwise
	 */
	public boolean isTicking() {
		return state == BombState.TICKING;
	}

	/**
	 * Returns whether this bomb is exploding right now.
	 * 
	 * @return true if the bomb is exploding, false otherwise
	 */
	public boolean isExploding() {
		return state == BombState.EXPLODING;
	}

	/**
	 * Returns whether this bomb's lifecylce has finished, i.e. the bomb has ticked down and finished exploding.
	 * 
	 * @return true if the bomb is finisehd, false otherwise
	 */
	public boolean hasFinished() {
		return state == BombState.FINISHED;
	}

	/**
	 * Returns the tile coordinates of the tiles inside this bombs radius.
	 * 
	 * @return the affected tile coordinates
	 */
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

	/**
	 * If the bomb is ticking, ticks down the tick counter of this bomb. If the counter reaches 0 the bomb will explode.
	 * If the bomb is exploding, the explosion tick counter will be counted down. If the counter reaches 0 the bomb will
	 * stop exploding.
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

}
