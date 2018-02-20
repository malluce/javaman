package bomberman.model;

public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
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
}
