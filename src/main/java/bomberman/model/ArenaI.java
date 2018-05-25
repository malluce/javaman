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
	 * @return the tile
	 */
	AbstractTile getTile(TileCoordinate coord);

	/**
	 * Sets a tile at a specific TileCoordinate.
	 * 
	 * @param coord
	 *            the coordinate of the tile to be set
	 * @param newTile
	 *            the new tile at coord position
	 * 
	 */
	void setTile(TileCoordinate coord, AbstractTile newTile);

	/**
	 * Returns the spawn points for players. At TileCoordinates in the returned array there will be an EmptyTile. Maybe
	 * there are also some "buffer" empty tiles so that initial movement is possible. The returned array has a size of
	 * getMaxPlayers().
	 * 
	 * @return the spawn points
	 */
	TileCoordinate[] getSpawnPoints();

}
