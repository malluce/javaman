package bomberman.model;

import java.util.List;

public class Game {
	private ArenaI arena;
	private List<AbstractPlayer> players;
	private List<Bomb> bombs;

	public Game(ArenaI arena, List<AbstractPlayer> players, List<Bomb> bombs) {
		this.arena = arena;
		this.players = players;
		this.bombs = bombs;
	}

	public ArenaI getArena() {
		return arena;
	}

	public void setArena(ArenaI arena) {
		this.arena = arena;
	}

	public List<AbstractPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(List<AbstractPlayer> players) {
		this.players = players;
	}

	public List<Bomb> getBombs() {
		return bombs;
	}

	public void setBombs(List<Bomb> bombs) {
		this.bombs = bombs;
	}
}
