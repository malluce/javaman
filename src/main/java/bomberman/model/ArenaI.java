package bomberman.model;

public interface ArenaI {

	void initArena();

	int getSize();

	AbstractTile[][] getCurrentMap();
}
