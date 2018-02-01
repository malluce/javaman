package bomberman.model;

import java.net.URL;

public class Bomb extends AbstractTile {

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
		return getClass().getClassLoader().getResource("bomb.png");
	}

}
