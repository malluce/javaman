package bomberman.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import bomberman.model.arena.ArenaI;
import bomberman.model.bomb.Bomb;
import bomberman.model.config.GameConfig;
import bomberman.model.coord.TileCoordinate;
import bomberman.model.exceptions.IllegalIdRequestException;
import bomberman.model.player.Player;
import bomberman.model.tile.AbstractTile;
import bomberman.model.tile.EmptyTile;
import bomberman.model.tile.ExplodingTile;
import bomberman.view.Renderer;
import bomberman.view.listener.PlayerInputListener;

/**
 * Represents the game. Handles the actions players may try to do. (e.g. moving, planting a bomb etc.)
 * 
 * @author Felix Bachmann
 *
 */
public class Game {
	private static final int TPS = 120; // tick per second
	private static final double MILLIS_PER_TICK = 1000 / TPS;

	private ArenaI arena;
	private List<Player> players;
	private Bomb[] bombs;
	private final int tileSize;
	private final int gameSize;
	private int currentId = 0;
	private volatile boolean running = false;

	/**
	 * Creates a new game.
	 * 
	 * @param config
	 *            the game config which was created by the user
	 */
	public Game(GameConfig config) {
		Objects.requireNonNull(config);
		this.tileSize = GameConfig.TILE_SIZE;
		this.gameSize = GameConfig.GAME_SIZE;
		this.arena = config.getArena();
		this.players = config.getPlayers();
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
	 * Returns the array of bombs. Entries may be null if not all bombs are currently planted.
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
	 * Plants a bomb in the arena. The caller must assure that the player is allowed to plant the bomb.
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

	/**
	 * Ticks down the bombs and handles events indicated by the state of the bombs which are present after ticking.
	 */
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

	/**
	 * Clears the explosions of a given bomb, that is setting the tiles affected by this bomb to an {@link EmptyTile}
	 * 
	 * @param b
	 *            the bomb of which the explosions should be cleared
	 */
	public void clearExplosions(Bomb b) {
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

	/**
	 * Let a bomb explode, that is setting the tiles affected by this bomb to an {@link ExplodingTile}.
	 * 
	 * @param b
	 *            the bomb to explode
	 */
	private void explodeBomb(Bomb b) {
		List<TileCoordinate> tilesToExplode = b.getAffectedTileCoordinates();

		/*
		 * Map to prevent a player from getting hit more than once by an explosion caused by a single bomb. 
		 * This may happen without this map because a player may move to a different tile while the explosion
		 * is still taking place. 
		 */
		Map<Player, Boolean> hitByThisExplosion = new HashMap<Player, Boolean>();
		for (Player pl : players) {
			hitByThisExplosion.put(pl, Boolean.FALSE);
		}

		for (int i = 0; i < tilesToExplode.size(); i++) {
			TileCoordinate curCoord = tilesToExplode.get(i);
			AbstractTile tile = arena.getTile(curCoord);
			if (tile.isDestroyable() || tile instanceof Bomb) {
				arena.setTile(curCoord, ExplodingTile.getInstance());
			}
			for (Player pl : players) {
				if (pl.getTileCoordinate().equals(curCoord) && !hitByThisExplosion.get(pl)) {
					pl.hit();
					hitByThisExplosion.put(pl, Boolean.TRUE);
				}
			}
		}

	}

	/**
	 * Starts the game, that is starts the game loop.
	 * 
	 * @param render
	 *            the renderer in which the game should be rendered.
	 * @param inputHandlers
	 *            the input handlers
	 */
	// TODO avoid this parameters here? at least the inputHandlers
	public void start(Renderer render, List<PlayerInputListener> inputHandlers) {
		running = true;
		gameLoop(render, inputHandlers);
	}

	private void gameLoop(Renderer render, List<PlayerInputListener> inputHandlers) {
		long nextTick = System.currentTimeMillis();
		long nextRender = nextTick;

		while (true) {
			while (running) {
				long loopStart = System.currentTimeMillis();

				if (loopStart >= nextRender) {
					render.render();
					do {
						nextRender += Renderer.MILLIS_PER_FRAME;
					} while (loopStart >= nextRender);
				}

				if (loopStart >= nextTick) {
					this.tickBombs();
					for (PlayerInputListener inputHandler : inputHandlers) {
						inputHandler.updateFromPressedKeys();
					}
					do {
						nextTick += MILLIS_PER_TICK;
					} while (loopStart >= nextTick);
				}

				long loopEnd = System.currentTimeMillis();
				long delay = Math.min(nextTick - loopEnd, nextRender - loopEnd);
				if (delay > 0) {
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	/**
	 * Toggles whether the game is running, i.e. pausing or unpausing the game.
	 */
	public void toggleRunning() {
		running = !running;
	}

}
