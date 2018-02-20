package bomberman.model;

public abstract class AbstractPlayer extends AbstractEntity {

	public abstract int getBombsLeft();

	public abstract int getX();

	public abstract int getY();

	public void moveUp(int speed) {

	}

	public void moveDown(int speed) {

	}

	public void moveLeft(int speed) {

	}

	public void moveRight(int speed) {

	}

	public boolean plantBomb() {
		return false;
	}
}
