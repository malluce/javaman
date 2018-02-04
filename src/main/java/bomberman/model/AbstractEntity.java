package bomberman.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public abstract class AbstractEntity {
	protected BufferedImage spriteImg = null;

	public BufferedImage getSprite() {
		if (spriteImg == null) {
			try {
				spriteImg = ImageIO.read(getSpriteURL());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return spriteImg;
	}

	public abstract URL getSpriteURL();
}
