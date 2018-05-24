package bomberman.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import utils.ImageResizer;

/**
 * Every entity (e.g. players) has a sprite for displaying it in the view package. This class encapsulates the loading
 * of the sprite. Disk I/O is only done once.
 * 
 * @author Felix Bachmann
 *
 */
public abstract class GameElement {
	protected BufferedImage spriteImg = null;

	/**
	 * Reads the sprite from disk (if not done yet) and return a BufferedImage of it.
	 * 
	 * @param spriteSize
	 *            the size of the (QUADRATIC!) tile
	 * @return BufferedImage instance of the sprite
	 */
	public BufferedImage getSprite(int spriteSize) {
		if (spriteImg == null) {
			try {
				spriteImg = ImageIO.read(getSpriteURL());
				spriteImg = ImageResizer.scale(spriteImg, spriteSize, spriteSize);
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
