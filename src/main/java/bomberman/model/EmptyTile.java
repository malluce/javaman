package bomberman.model;

import java.net.URL;

public class EmptyTile extends TileI {

	public boolean isPassable() {
		return true;
	}

	public boolean isDestroyable() {
		return false;
	}

	@Override
	protected URL getSpriteURL() {
		return getClass().getClassLoader().getResource("empty_tile.png");
	}

}
