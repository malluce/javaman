package bomberman.model.config;

import bomberman.model.Game;
import bomberman.model.coord.XYCoordinate;

/**
 * Represents a configuration of a player (see {@link bomberman.model.player.Player}).
 * 
 * @author Felix Bachmann
 *
 */
public class PlayerConfig {
	private String spriteName;
	private XYCoordinate position;
	private int amountOfBombs;
	private int lives;
	private Game game;

	/**
	 * Creates a new instance.
	 * 
	 * @param spriteName
	 *            the sprite name of this configuration
	 * @param position
	 *            the position
	 * @param amountOfBombs
	 *            the amountOfBombs
	 * @param lives
	 *            the lives
	 * @param game
	 *            the game
	 */
	public PlayerConfig(String spriteName, XYCoordinate position, int amountOfBombs, int lives, Game game) {
		this.spriteName = spriteName;
		this.position = position;
		this.amountOfBombs = amountOfBombs;
		this.lives = lives;
		this.game = game;
	}

	/**
	 * @return the spriteName
	 */
	public String getSpriteName() {
		return spriteName;
	}

	/**
	 * @return the position
	 */
	public XYCoordinate getPosition() {
		return position;
	}

	/**
	 * @return the amountOfBombs
	 */
	public int getAmountOfBombs() {
		return amountOfBombs;
	}

	/**
	 * @return the lives
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

}
