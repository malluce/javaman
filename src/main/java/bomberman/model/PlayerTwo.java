package bomberman.model;

import java.net.URL;

public class PlayerTwo extends AbstractPlayer {
	private Point position;
	private int bombsLeft;

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource("player_two.png");
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
