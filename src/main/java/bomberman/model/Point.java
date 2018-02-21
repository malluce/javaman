package bomberman.model;

public class Point {
	private int x;
	private int y;
	private CoordinateType type;

	public Point(int x, int y, CoordinateType type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Point)) {
			return false;
		}

		if (o == this) {
			return true;
		}

		Point oPoint = (Point) o;

		if (oPoint.getX() == this.getX() && oPoint.getY() == this.getY()) {
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

	public Point toXYCoordinates() {
		if (this.type == CoordinateType.XY_COORDINATE) {
			return this;
		}
		return null;
		// TODO which arguments needed? and calculation
	}

	public Point toTileCoordinates() {
		return null;
		// TODO which arguments needed? and calculation
	}

}
