package bomberman.model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EmptyTile implements TileI {

	public boolean isPassable() {
		return true;
	}

	public boolean isDestroyable() {
		return false;
	}

	public BufferedImage getSprite() {
		BufferedImage spriteImg = null;

		try {
			spriteImg = ImageIO.read(getClass().getClassLoader().getResource("empty_tile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		assert spriteImg != null : "spriteImg was null";

		return spriteImg;
	}

}
