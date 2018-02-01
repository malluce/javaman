package bomberman.model;

import java.net.URL;

public class UndestroyableTile extends AbstractTile {

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
