package bomberman.model;

import java.net.URL;

public class UndestroyableTile extends AbstractTile {
	private static UndestroyableTile instance = null;

	private UndestroyableTile() {

	}

	public static UndestroyableTile getInstance() {
		if (instance == null) {
			instance = new UndestroyableTile();
		}
		return instance;
	}

	public boolean isPassable() {
		return false;
	}

	public boolean isDestroyable() {
		return false;
	}

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource("undestroyable_block.png");
	}

}
