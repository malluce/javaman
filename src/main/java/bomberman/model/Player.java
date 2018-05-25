package bomberman.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import utils.ImageResizer;

/**
 * Encapsulates everything the players have in common. (e.g. moving, planting bombs)
 * 
 * @author Felix Bachmann
 *
 */
public class Player extends GameElement {
	private final String spriteName;
	private int id;
	private XYCoordinate position;
	private int bombsLeft;
	private int maxBombs;
	private int speed = 1;
	private int lifes;

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
	 * @param lifes
	 *            the initial amount of lifes the player has
	 * @param game
	 *            the game the player is alive in
	 */
	public Player(String spriteName, XYCoordinate initialPosition, int amountOfBombs, int lifes, Game game) {
		this.spriteName = spriteName;
		this.position = initialPosition;
		this.bombsLeft = amountOfBombs;
		this.maxBombs = amountOfBombs;
		this.game = game;
		this.lifes = lifes;
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
		return this.id;
	}

	/**
	 * Returns true if the player is alive, false otherwise.
	 * 
	 * @return if the player is alive
	 */
	public boolean isAlive() {
		return this.lifes > 0;
	}

	@Override
	public URL getSpriteURL() {
		return getClass().getClassLoader().getResource(spriteName);
	}

	/**
	 * Sets the amount of bombs the player is still able to plant.
	 * 
	 * @param bombsLeft
	 *            the amount of bombs, has to be >= 0.
	 */
	public void setBombsLeft(int bombsLeft) {
		if (bombsLeft < 0) {
			throw new IllegalArgumentException("bombsLeft has to be greater or equal to zero");
		}
		this.bombsLeft = bombsLeft;
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
			this.id = game.nextId();
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
		// player should not move when he's dead
		if (!isAlive()) {
			return;
		}

		// relevant variables
		int xPos = position.getX();
		int yPos = position.getY();
		int tileSize = game.getTileSize();
		int gameSize = game.getGameSize();
		ArenaI arena = game.getArena();

		XYCoordinate newXYPosition = null; // the pixel-position if the movement happens

		/*
		 * The tile that may be entered when moving. Note that this is NOT equal to the tile in which newXYPosition is.
		 * The width and height of the player (=tileSize) has to be considered when moving right or down because the
		 * XYPosition of the player is defined by its uppermost left pixel.
		 */
		TileCoordinate firstTileToEnter = null;

		// calculate new position and tile
		switch (dir) {
		case LEFT:
			newXYPosition = new XYCoordinate(xPos - speed, yPos);
			firstTileToEnter = newXYPosition.toTileCoordinates(tileSize);
			break;
		case RIGHT:
			newXYPosition = new XYCoordinate(xPos + speed, yPos);
			firstTileToEnter = new XYCoordinate(xPos + speed + tileSize - 1, yPos).toTileCoordinates(tileSize);
			break;
		case UP:
			newXYPosition = new XYCoordinate(xPos, yPos - speed);
			firstTileToEnter = newXYPosition.toTileCoordinates(tileSize);
			break;
		case DOWN:
			newXYPosition = new XYCoordinate(xPos, yPos + speed);
			firstTileToEnter = new XYCoordinate(xPos, yPos + speed + tileSize - 1).toTileCoordinates(tileSize);
			break;
		default:
			break;
		}

		// do not leave arena of the game
		if (!firstTileToEnter.inGameRange(gameSize)) {
			return;
		}

		/*
		 * The second tile a player may enter.
		 * If the player is not aligned with the tile grid, he may enter two tiles at once because his body is located 
		 * in two tiles at once.
		 */
		TileCoordinate secondTileToEnter = null;

		if (firstTileToEnter.equals(this.position.toTileCoordinates(tileSize))) {
			// no tile change happens here
			this.position = newXYPosition;
			return;
		} else {
			int newRow = firstTileToEnter.getRow();
			int newCol = firstTileToEnter.getColumn();
			// check if player is aligned so it is enough to check one tile
			switch (dir) {
			case LEFT: // fall through
			case RIGHT:
				if (yPos % tileSize == 0) {
					if (arena.getTile(firstTileToEnter).isPassable()) {
						this.position = newXYPosition;
					}
					return;
				} else {
					secondTileToEnter = new TileCoordinate(newCol, newRow + 1);
				}
				break;
			case UP: // fall through
			case DOWN:
				if (xPos % tileSize == 0) {
					if (arena.getTile(firstTileToEnter).isPassable()) {
						this.position = newXYPosition;
					}
					return;
				} else {
					secondTileToEnter = new TileCoordinate(newCol + 1, newRow);
				}
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
	 * Hits the player, that is decrements the lifes of the player by 1.
	 */
	public void hit() {
		if (isAlive()) {
			this.lifes--;
		}
	}

	/**
	 * Tries to plant a bomb. Only has an effect if the player has bombs left to plant. In that case the amount of bombs
	 * left will be decremented and the bomb will be planted.
	 */
	public void plantBomb() {
		if (bombsLeft > 0 && isAlive()) {
			bombsLeft--;
			TileCoordinate playerTile = this.position.toTileCoordinates(game.getTileSize());
			Bomb newBomb = new Bomb("bomb.png", 1, 100, playerTile, 100, this);
			game.plantBomb(this, newBomb);
		}
	}

	@Override
	public BufferedImage getSprite(int spriteSize) {
		if (spriteImg == null) {
			try {
				spriteImg = ImageIO.read(getSpriteURL());
				// adds transparency, needed due to images of players.
				// TODO change on disk, no overriding needed then
				for (int i = 0; i < spriteImg.getWidth(); i++) {
					for (int j = 0; j < spriteImg.getHeight(); j++) {
						if (spriteImg.getRGB(i, j) == 0xffffffff) {
							spriteImg.setRGB(i, j, 0x00000000);
						}
					}
				}
				spriteImg = ImageResizer.scale(spriteImg, spriteSize, spriteSize);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return spriteImg;
	}
}
