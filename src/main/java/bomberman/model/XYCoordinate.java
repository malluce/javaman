package bomberman.model;

/**
 * Represents a coordinate of a not tile-aligned entity in the game. (e.g. a player)
 * 
 * @author Felix Bachmann
 *
 */
public class XYCoordinate {
	private int x;
	private int y;

	/**
	 * Creates a new XYCoordinate.
	 * 
	 * @param x
	 *            the x position of the XYCoordinate
	 * @param y
	 *            the y position of the XYCoordinate
	 */
	public XYCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof XYCoordinate)) {
			return false;
		}

		if (o == this) {
			return true;
		}

		XYCoordinate oCoordinate = (XYCoordinate) o;

		if (oCoordinate.getX() == this.getX() && oCoordinate.getY() == this.getY()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the x position of the XYCoordinate.
	 * 
	 * @return x position
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y position of the XYCoordinate.
	 * 
	 * @return y position
	 */
	public int getY() {
		return y;
	}

	/**
	 * Calculates the tile coordinate of this XYCoordinate. May be used for calculating the "hitbox" of an moving entity
	 * (e.g. a player).
	 * 
	 * @param tileSize
	 *            the size of a tile
	 * @return the TileCoordinate of this XYCoordinate
	 */
	public TileCoordinate toTileCoordinates(int tileSize) {
		int row = y / tileSize;
		int col = x / tileSize;
		return new TileCoordinate(col, row);
	}

}
