package bomberman.model;

import java.net.URL;

public class EmptyTile extends AbstractTile {
	private static EmptyTile instance = null;

	private EmptyTile() {

	}

	public static EmptyTile getInstance() {
		if (instance == null) {
			instance = new EmptyTile();
		}
		return instance;
	}

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
