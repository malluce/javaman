package bomberman.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game. Handles the actions players may try to do. (e.g. moving, planting a bomb etc.)
 * 
 * @author Felix Bachmann
 *
 */
public class Game {
	private ArenaI arena;
	private List<Player> players;
	private List<Bomb> bombs;

	public Game(ArenaI arena, List<Player> players) {
		this.arena = arena;
		this.players = players;
		this.bombs = new ArrayList<Bomb>();
	}

	public ArenaI getArena() {
		return arena;
	}

	public void setArena(ArenaI arena) {
		this.arena = arena;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Bomb> getBombs() {
		return bombs;
	}

	public void setBombs(List<Bomb> bombs) {
		this.bombs = bombs;
	}
}
