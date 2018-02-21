package bomberman.model;

public class TileCoordinate {
	private int column;
	private int row;

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

		if (oCoordinate.getColumn() == this.getColumn() && oCoordinate.getRow() == this.getRow()) {
			return true;
		} else {
			return false;
		}
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public XYCoordinate toXYCoordinates(int tileSize) {
		int xCoord = column * tileSize;
		int yCoord = row * tileSize;
		return new XYCoordinate(xCoord, yCoord);
	}
}
