package bomberman.model;

public abstract class AbstractPlayer extends AbstractEntity {

	public abstract int getX();

	public abstract int getY();

	public abstract void moveUp();

	public abstract void moveDown();

	public abstract void moveLeft();

	public abstract void moveRight();

	public abstract boolean plantBomb();
}
