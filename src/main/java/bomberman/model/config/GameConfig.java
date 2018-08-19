package bomberman.model.config;

import java.util.List;

import bomberman.model.arena.ArenaI;
import bomberman.model.player.Player;

/**
 * The configuration of a {@link bomberman.model.Game}.
 * 
 * @author Felix Bachmann
 *
 */
public class GameConfig {
	private ArenaI arena;
	private List<Player> players;

	/**
	 * The size of the game, i.e. the amount of tiles per row and column. The map of the Arena has GAME_SIZE * GAME_SIZE
	 * tiles.
	 */
	public static final int GAME_SIZE = 10;

	/**
	 * The pixel size of the game, i.e. the amount of pixels per row and column of a tile. One tile has TILE_SIZE *
	 * TILE_SIZE pixels.
	 */
	public static final int TILE_SIZE = 64;

	/**
	 * Creates a new instance.
	 * 
	 * @param arena
	 *            the arena of the config
	 * @param players
	 *            the list of players of this game config
	 */
	public GameConfig(ArenaI arena, List<Player> players) {
		this.arena = arena;
		this.players = players;
	}

	/**
	 * Returns the arena of this config.
	 * 
	 * @return the arena
	 */
	public ArenaI getArena() {
		return arena;
	}

	/**
	 * Returns the list of players of this config.
	 * 
	 * @return the player list
	 */
	public List<Player> getPlayers() {
		return players;
	}
}
