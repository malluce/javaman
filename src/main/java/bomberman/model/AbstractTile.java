package bomberman.model;

/**
 * A tile in an arena is represented in this class.
 * 
 * @author Felix Bachmann
 *
 */
public abstract class AbstractTile extends AbstractEntity {

	/**
	 * Indicates whether the tile is passable by a player.
	 * 
	 * @return true if the tile is passable, false otherwise
	 */
	public abstract boolean isPassable();

	/**
	 * Indicates whether the tile is destroyable by a bomb.
	 * 
	 * @return true if the tile is destroyable, false otherwise
	 */
	public abstract boolean isDestroyable();

}
