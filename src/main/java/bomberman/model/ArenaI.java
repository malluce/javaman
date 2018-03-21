package bomberman.model;

/**
 * Represents an arena.
 * 
 * @author Felix Bachmann
 *
 */
public interface ArenaI {

	/**
	 * Returns the amount of players that are able to play inside this arena.
	 * 
	 * @return the amount of player allowed in this arena
	 */
	int getMaxPlayers();

	/**
	 * Returns the size of this arena. The arena is quadratic and has size * size tiles.
	 * 
	 * @return the size
	 */
	int getSize();

	/**
	 * Returns the tile that is located at a specific TileCoordinate.
	 * 
	 * @param coord
	 *            the coordinate of the tile that is returned
	 */
	AbstractTile getTile(TileCoordinate coord);

	/**
	 * Sets a tile at a specific TileCoordinate.
	 * 
	 * @param coord
	 *            the coordinate of the tile to be set
	 * @param tile
	 *            the new tile at coord position
	 */
	void setTile(TileCoordinate coord, AbstractTile newTile);

}
