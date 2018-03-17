package bomberman.model;

public interface ArenaI {

	int getMaxPlayers();

	int getSize();

	AbstractTile getTile(TileCoordinate coord);

	void setTile(TileCoordinate coord, AbstractTile newTile);

}
