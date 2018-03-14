package bomberman.model;

public interface ArenaI {

	int getMaxPlayers();

	int getSize();

	AbstractTile getTile(TileCoordinate coord);

}
