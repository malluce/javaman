package bomberman.model;

import java.net.URL;

/**
 * Encapsulates everything the players have in common. (e.g. moving, planting bombs)
 * 
 * @author Felix Bachmann
 *
 */
public class Player extends AbstractEntity {
	private final String SPRITE_NAME;
	private XYCoordinate position;
	private int bombsLeft;
	private int maxBombs;
	private int speed = 1;
	private int ID;

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
	 * @param game
	 *            the game the player is alive in
	 */
	public Player(String spriteName, XYCoordinate initialPosition, int amountOfBombs, Game game) {
		this.SPRITE_NAME = spriteName;
		this.position = initialPosition;
		this.bombsLeft = amountOfBombs;
		this.maxBombs = amountOfBombs;
		this.game = game;
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
	 * Returns the maximum amount of bombs the player may plant at once.
	 * 
	 * @return the maximum amount of bombs
	 */
	public int getMaxBombs() {
		return maxBombs;
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

	/**
	 * Returns the TileCoordinate of this player. (= "hitbox" of this player)
	 * 
	 * @return the TileCoordinate
	 */
	public TileCoordinate getTileCoordinate() {
		return this.position.toTileCoordinates(game.getTileSize());
	}

	/**
	 * Returns the id of this player. It is unique amongst all players.
	 * 
	 * @return the id
	 */
	public int getId() {
		return this.ID;
	}

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource(SPRITE_NAME);
	}

	/**
	 * Sets the game this player plays. Only has an effect once hence the game is not allowed to change in the lifetime
	 * of a player.
	 * 
	 * @param game
	 *            the game to set
	 * @throws IllegalIdRequestException
	 *             is thrown if there is no id for this player in the game because the game is already full
	 */
	public void setGame(Game game) throws IllegalIdRequestException {
		if (this.game == null) {
			this.game = game;
			this.ID = game.nextId();
		}
	}

	/**
	 * Will move the player upward if possible.
	 */
	public void moveUp() {
		move(Direction.UP);
	}

	/**
	 * Will move the player downward if possible.
	 */
	public void moveDown() {
		move(Direction.DOWN);
	}

	/**
	 * Will move the player leftward if possible.
	 */
	public void moveLeft() {
		move(Direction.LEFT);
	}

	/**
	 * Will move the player rightward if possible.
	 */
	public void moveRight() {
		move(Direction.RIGHT);
	}

	/**
	 * Private method to encapsulate movement (algorithms are quite similar).
	 * 
	 * @param dir
	 *            the direction in which the player may move
	 */
	private void move(Direction dir) {
		// relevant variables
		int xPos = position.getX();
		int yPos = position.getY();
		int tileSize = game.getTileSize();
		int gameSize = game.getGameSize();
		ArenaI arena = game.getArena();

		XYCoordinate newXYPosition = null;
		TileCoordinate firstTileToEnter = null; // position of the new tile that will be entered in any case (the tile in which position is)

		// different moving direction lead to different tiles
		switch (dir) {
		case LEFT:
			newXYPosition = new XYCoordinate(xPos - speed, yPos);
			firstTileToEnter = newXYPosition.toTileCoordinates(tileSize);
			if (firstTileToEnter.getColumn() < 0) { // do not leave arena
				return;
			}
			break;
		case RIGHT:
			newXYPosition = new XYCoordinate(xPos + speed, yPos);
			firstTileToEnter = new XYCoordinate(xPos + speed + tileSize - 1, yPos).toTileCoordinates(tileSize);
			if (firstTileToEnter.getColumn() > gameSize) { // do not leave arena
				return;
			}
			break;
		case UP:
			newXYPosition = new XYCoordinate(xPos, yPos - speed);
			firstTileToEnter = newXYPosition.toTileCoordinates(tileSize);
			if (firstTileToEnter.getRow() < 0) { // do not leave arena
				return;
			}
			break;
		case DOWN:
			newXYPosition = new XYCoordinate(xPos, yPos + speed);
			firstTileToEnter = new XYCoordinate(xPos, yPos + speed + tileSize - 1).toTileCoordinates(tileSize);
			if (firstTileToEnter.getRow() > gameSize) { // do not leave arena
				return;
			}
			break;
		default:
			break;
		}

		int newRow = firstTileToEnter.getRow();
		int newCol = firstTileToEnter.getColumn();
		TileCoordinate secondTileToEnter = null; // if the player is not aligned he must enter two tiles at once

		if (firstTileToEnter.equals(this.position.toTileCoordinates(tileSize))) { // no tile change
			this.position = newXYPosition;
		} else {
			// check if player is aligned so it is enough to check one tile
			switch (dir) {
			case LEFT:
			case RIGHT:
				if (yPos % tileSize == 0) {
					if (arena.getTile(firstTileToEnter).isPassable()) {
						this.position = newXYPosition;
					}
					return;
				}
				secondTileToEnter = new TileCoordinate(newCol, newRow + 1);
				break;
			case UP:
			case DOWN:
				if (xPos % tileSize == 0) {
					if (arena.getTile(firstTileToEnter).isPassable()) {
						this.position = newXYPosition;
					}
					return;
				}
				secondTileToEnter = new TileCoordinate(newCol + 1, newRow);
				break;
			default:
				break;
			}

			// need to check two tiles, because we did not return until now
			if (arena.getTile(secondTileToEnter).isPassable() && arena.getTile(firstTileToEnter).isPassable()) {
				this.position = newXYPosition;
			}

		}
	}

	/**
	 * Tries to plant a bomb. Only has an effect if the player has bombs left to plant. In that case the amount of bombs
	 * left will be decremented and the bomb will be planted.
	 */
	public void plantBomb() {
		if (bombsLeft > 0) {
			bombsLeft--;
			TileCoordinate playerTile = this.position.toTileCoordinates(game.getTileSize());
			Bomb newBomb = new Bomb("bomb.png", 1, 100, playerTile, 100);
			game.plantBomb(this, newBomb);
		}
	}
}
