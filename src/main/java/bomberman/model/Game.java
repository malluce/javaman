package bomberman.model;

import java.util.Iterator;
import java.util.List;

/**
 * Represents the game. Handles the actions players may try to do. (e.g. moving,
 * planting a bomb etc.)
 * 
 * @author Felix Bachmann
 *
 */
public class Game {
	private ArenaI arena;
	private List<Player> players;
	private Bomb[] bombs;
	private final int tileSize;
	private final int gameSize;
	private int currentId = 0;

	/**
	 * Creates a new game.
	 * 
	 * @param arena
	 *            the arena is which the game is taking place
	 * @param players
	 *            the players that will play the game
	 * @param tileSize
	 *            the size of a tile in pixels
	 * @param gameSize
	 *            the size of the game. hence all arenas are quadratic this
	 *            value refers to the amount of tiles in a row or column
	 */
	public Game(ArenaI arena, List<Player> players, int tileSize, int gameSize) {
		this.tileSize = tileSize;
		this.gameSize = gameSize;
		this.arena = arena;
		this.players = players;
		int maxBombs = 0;
		for (Player player : players) {
			maxBombs += player.getMaxBombs();
		}
		this.bombs = new Bomb[maxBombs];

	}

	/**
	 * Returns the size of the game.
	 * 
	 * @return size of the game
	 */
	public int getGameSize() {
		return this.gameSize;
	}

	/**
	 * Returns the size of a tile.
	 * 
	 * @return size of a tile
	 */
	public int getTileSize() {
		return this.tileSize;
	}

	/**
	 * Returns the arena in which the game is taking place.
	 * 
	 * @return the arena
	 */
	public ArenaI getArena() {
		return arena;
	}

	/**
	 * Returns the players which are playing the game.
	 * 
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Returns the array of bombs. Entries may be null if not all bombs are
	 * currently planted.
	 * 
	 * @return the bombs
	 */
	public Bomb[] getBombs() {
		return bombs;
	}

	/**
	 * Allocates a new player id and returns it.
	 * 
	 * @return a id
	 * @throws IllegalIdRequestException
	 *             thrown if there are no more ids to distribute.
	 */
	public int nextId() throws IllegalIdRequestException {
		if (currentId < players.size()) {
			return currentId++;
		} else {
			throw new IllegalIdRequestException("all available IDs have been assigned to players.");
		}
	}

	/**
	 * Plants a bomb in the arena. The caller must assure that the player is
	 * allowed to plant the bomb.
	 * 
	 * @param plantingPlayer
	 *            the player which is planting the bomb
	 * @param plantedBomb
	 *            the bomb which will be planted
	 */
	public void plantBomb(Player plantingPlayer, Bomb plantedBomb) {
		arena.setTile(plantingPlayer.getTileCoordinate(), plantedBomb);
		int id = plantingPlayer.getId();
		for (int curId = id; curId < id + plantingPlayer.getMaxBombs(); curId++) {
			// search the area in the bomb array of a player for free entries (=
			// null entries)
			if (bombs[curId] == null) {
				bombs[curId] = plantedBomb;
				break;
			}
		}
	}

	public void tickBombs() {
		for (int i = 0; i < bombs.length; i++) {
			Bomb b = bombs[i];
			if (b != null) {
				if (b.isTicking()) {
					b.tick();
				} else if (b.isExploding()) {
					if (b.getExplosionTicks() == b.getMaxExplosionTicks()) {
						explodeBomb(b);
					}
					b.tick();
				} else if (b.hasFinished()) {
					clearExplosions(b);
					bombs[i] = null;
				}
			}
		}
	}

	private void clearExplosions(Bomb b) {
		List<TileCoordinate> tilesToClearIfEmpty = b.getAffectedTileCoordinates();
		Iterator<TileCoordinate> iter = tilesToClearIfEmpty.iterator();
		while (iter.hasNext()) {
			TileCoordinate coord = iter.next();
			AbstractTile tile = arena.getTile(coord);
			if (tile instanceof ExplodingTile) {
				arena.setTile(coord, EmptyTile.getInstance());
			}
		}
		Player pl = b.getPlayer();
		pl.setBombsLeft(pl.getBombsLeft() + 1);
	}

	private void explodeBomb(Bomb b) {
		List<TileCoordinate> tilesToExplode = b.getAffectedTileCoordinates();

		for (int i = 0; i < tilesToExplode.size(); i++) {
			TileCoordinate curCoord = tilesToExplode.get(i);
			AbstractTile tile = arena.getTile(curCoord);
			if (tile.isDestroyable() || tile instanceof Bomb) {
				arena.setTile(curCoord, ExplodingTile.getInstance());
			}
		}

	}

}
