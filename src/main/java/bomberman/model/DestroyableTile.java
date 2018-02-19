package bomberman.model;

import java.net.URL;

public class DestroyableTile extends AbstractTile {
	private static DestroyableTile instance = null;

	private DestroyableTile() {

	}

	public static DestroyableTile getInstance() {
		if (instance == null) {
			instance = new DestroyableTile();
		}
		return instance;
	}

	@Override
	public boolean isPassable() {
		return false;
	}

	@Override
	public boolean isDestroyable() {
		return true;
	}

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource("destroyable_block.png");
	}

}
