package bomberman.model;

import java.net.URL;

/**
 * Encapsulates everything the players have in common. (e.g. moving, planting
 * bombs)
 * 
 * @author Felix Bachmann
 *
 */
public class Player extends AbstractEntity {
	private final String SPRITE_NAME;
	private XYCoordinate position;
	private int bombsLeft;
	private int speed = 2;
	private final int ID;

	private Game game;

	/**
	 * Creates a new player.
	 * 
	 * @param spriteName
	 *            the name of the sprite file (e.g. "player_one.png")
	 * @param initialPosition
	 *            the initial position of the player
	 * @param amountOfBombs
	 *            the amount of bombs the player has (at the start of the game)
	 * @param size
	 *            the size of the player
	 * @param game
	 *            the game the player is alive in
	 */
	public Player(String spriteName, XYCoordinate initialPosition, int amountOfBombs, Game game, int id) {
		this.SPRITE_NAME = spriteName;
		this.position = initialPosition;
		this.bombsLeft = amountOfBombs;
		this.game = game;
		this.ID = id;
	}

	/**
	 * Returns the amount of bombs left for the player.
	 * 
	 * @return the amount of bombs left
	 */
	public int getBombsLeft() {
		return bombsLeft;
	}

	/**
	 * Returns the x-position of the player.
	 * 
	 * @return x-position of the player
	 */
	public int getX() {
		return position.getX();
	}

	/**
	 * Returns the y-position of the player.
	 * 
	 * @return y-position of the player
	 */
	public int getY() {
		return position.getY();
	}

	public int getId() {
		return this.ID;
	}

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource(SPRITE_NAME);
	}

	public void setGame(Game game) {
		this.game = game;
	}

	// TODO
	public void moveUp() {
		this.position = new XYCoordinate(position.getX(), position.getY() - speed);
	}

	public void moveDown() {
		this.position = new XYCoordinate(position.getX(), position.getY() + speed);
	}

	public void moveLeft() {
		this.position = new XYCoordinate(position.getX() - speed, position.getY());
	}

	public void moveRight() {
		this.position = new XYCoordinate(position.getX() + speed, position.getY());
	}

	public boolean plantBomb() {
		return false;
	}
}
