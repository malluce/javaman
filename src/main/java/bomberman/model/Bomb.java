package bomberman.model;

import java.net.URL;

public class Bomb extends AbstractTile {
	private final String SPRITE_NAME;
	private int row;
	private int col;
	private int radius;
	private int ticks;

	public Bomb(String spriteName, int radius, int ticks, int row, int col) {
		this.SPRITE_NAME = spriteName;
		this.radius = radius;
		this.ticks = ticks;
		this.row = row;
		this.col = col;
	}

	@Override
	public boolean isPassable() {
		return false;
	}

	@Override
	public boolean isDestroyable() {
		return false;
	}

	public int getCol() {
		return this.col;
	}

	public int getRow() {
		return this.row;
	}

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource(SPRITE_NAME);
	}

}
