package bomberman.model;

import java.net.URL;

public class Bomb extends AbstractTile {
	private final String SPRITE_NAME;
	private int radius;
	private int ticks;

	public Bomb(String spriteName, int radius, int ticks) {
		this.SPRITE_NAME = spriteName;
		this.radius = radius;
		this.ticks = ticks;
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
		return getClass().getClassLoader().getResource(SPRITE_NAME);
	}

}
