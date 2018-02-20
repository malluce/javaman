package bomberman.model;

import java.net.URL;

public class PlayerOne extends AbstractPlayer {
	private Point position;

	private int bombsLeft;

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource("player_one.png");
	}

	@Override
	public int getX() {
		return position.getX();
	}

	@Override
	public int getY() {
		return position.getY();
	}

	@Override
	public int getBombsLeft() {
		return bombsLeft;
	}

}
