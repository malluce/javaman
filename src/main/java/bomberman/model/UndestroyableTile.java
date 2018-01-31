package bomberman.model;

import java.net.URL;

public class UndestroyableTile extends TileI {

	public boolean isPassable() {
		return false;
	}

	public boolean isDestroyable() {
		return false;
	}

	@Override
	protected URL getSpriteURL() {
		return getClass().getClassLoader().getResource("undestroyable_block.png");
	}

}
