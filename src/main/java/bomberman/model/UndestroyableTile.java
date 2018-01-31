package bomberman.model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UndestroyableTile implements TileI {

	public boolean isPassable() {
		return false;
	}

	public boolean isDestroyable() {
		return false;
	}

	public BufferedImage getSprite() {
		BufferedImage spriteImg = null;

		try {
			spriteImg = ImageIO.read(getClass().getClassLoader().getResource("undestroyable_block.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		assert spriteImg != null : "spriteImg was null";

		return spriteImg;
	}

}
