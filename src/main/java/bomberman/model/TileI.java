package bomberman.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public abstract class TileI {

	public abstract boolean isPassable();

	public abstract boolean isDestroyable();

	public BufferedImage getSprite() {
		BufferedImage spriteImg = null;

		try {
			spriteImg = ImageIO.read(getSpriteURL());
		} catch (IOException e) {
			e.printStackTrace();
		}

		assert spriteImg != null : "spriteImg was null";

		return spriteImg;
	}

	protected abstract URL getSpriteURL();

}
