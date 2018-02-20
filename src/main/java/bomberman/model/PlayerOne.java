package bomberman.model;

import java.net.URL;

public class PlayerOne extends AbstractPlayer {
	private int x;
	private int y;

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource("player_one.png");
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
