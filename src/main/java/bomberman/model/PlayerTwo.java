package bomberman.model;

import java.net.URL;

public class PlayerTwo extends AbstractPlayer {
	private int x;
	private int y;

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean plantBomb() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource("player_two.png");
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

}
