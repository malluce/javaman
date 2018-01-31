package bomberman.model;

import java.awt.image.BufferedImage;

public interface TileI {

	boolean isPassable();

	boolean isDestroyable();

	BufferedImage getSprite();
}
