package bomberman.model;

import java.net.URL;

public class Bomb extends AbstractTile {
	private final String SPRITE_NAME;
	private TileCoordinate tileCoord;
	private int radius;
	private int ticks;

	public Bomb(String spriteName, int radius, int ticks, TileCoordinate tileCoord) {
		this.SPRITE_NAME = spriteName;
		this.radius = radius;
		this.ticks = ticks;
		this.tileCoord = tileCoord;
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
		return this.tileCoord.getColumn();
	}

	public int getRow() {
		return this.tileCoord.getRow();
	}

	public TileCoordinate getTileCoordinate() {
		return this.tileCoord;
	}

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource(SPRITE_NAME);
	}

}
