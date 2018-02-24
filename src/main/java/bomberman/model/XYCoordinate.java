package bomberman.model;

public class XYCoordinate {
	private int x;
	private int y;

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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public TileCoordinate toTileCoordinates(int tileSize) {
		int row = y / tileSize;
		int col = x / tileSize;
		return new TileCoordinate(col, row);
	}

}
