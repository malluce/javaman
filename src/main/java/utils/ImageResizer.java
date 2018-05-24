package utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Utility class for resizing a BufferedImage.
 * 
 * @author Felix Bachmann
 *
 */
public final class ImageResizer {

	private ImageResizer() {

	}

	/**
	 * Scales a given BufferedImage to a new size.
	 * 
	 * @param toScale
	 *            the bufferedImage to scale
	 * @param newWidth
	 *            the new width
	 * @param newHeight
	 *            the new height
	 * @return the scaled bufferedImage
	 */
	public static BufferedImage scale(BufferedImage toScale, int newWidth, int newHeight) {
		if (toScale == null) {
			throw new IllegalArgumentException("BufferedImage to scale must not be null.");
		}
		if (newWidth == toScale.getWidth() && newHeight == toScale.getHeight()) {
			return toScale;
		}

		// actual scaling
		Image scaledImg = toScale.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

		// convert Image to BufferedImage
		BufferedImage scaledBufImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D bufImgGr = scaledBufImg.createGraphics();
		bufImgGr.drawImage(scaledImg, 0, 0, null);

		return scaledBufImg;
	}

}
