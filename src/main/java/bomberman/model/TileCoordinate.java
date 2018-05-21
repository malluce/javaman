package bomberman.model;

/**
 * Represents a coordinate of a fixed tile.
 * 
 * @author Felix Bachmann
 *
 */
public class TileCoordinate {
	private int column;
	private int row;

	/**
	 * Creates a new TileCoordinate.
	 * 
	 * @param column
	 *            the column of the tile
	 * @param row
	 *            the row of the tile
	 */
	public TileCoordinate(int column, int row) {
		this.column = column;
		this.row = row;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof TileCoordinate)) {
			return false;
		}

		if (o == this) {
			return true;
		}

		TileCoordinate oCoordinate = (TileCoordinate) o;

		return oCoordinate.getColumn() == this.getColumn() && oCoordinate.getRow() == this.getRow();
	}

	/**
	 * Returns the column of this TileCoordinate.
	 * 
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Returns the row of this TileCoordinate
	 * 
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Calculates the XYCoordinate of this TileCoordinate. May be used for rendering a tile.
	 * 
	 * @param tileSize
	 *            the size of a tile
	 * @return the XYCoordinate
	 */
	public XYCoordinate toXYCoordinates(int tileSize) {
		int xCoord = column * tileSize;
		int yCoord = row * tileSize;
		return new XYCoordinate(xCoord, yCoord);
	}
}
