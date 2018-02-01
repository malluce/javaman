package bomberman.model;

import java.net.URL;

public class EmptyTile extends AbstractTile {

	public boolean isPassable() {
		return true;
	}

	public boolean isDestroyable() {
		return false;
	}

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource("empty_tile.png");
	}

}
