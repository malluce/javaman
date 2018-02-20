package bomberman.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Every entity (e.g. players) has a sprite for displaying it in the view package. This class encapsulates the loading
 * of the sprite. Disk I/O is only done once.
 * 
 * @author Felix Bachmann
 *
 */
public abstract class AbstractEntity {
	protected BufferedImage spriteImg = null;

	/**
	 * Reads the sprite from disk (if not done yet) and return a BufferedImage of it.
	 * 
	 * @return BufferedImage instance of the sprite
	 */
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

	/**
	 * Returns the URL of the sprite.
	 * 
	 * @return the sprite url
	 */
	public abstract URL getSpriteURL();
}
