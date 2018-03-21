package bomberman.model;

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
	private Bomb[] bombs;
	private final int tileSize;
	private final int gameSize;
	private int currentId = 0;

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

	public int getGameSize() {
		return this.gameSize;
	}

	public int getTileSize() {
		return this.tileSize;
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

	public Bomb[] getBombs() {
		return bombs;
	}

	public void setBombs(Bomb[] bombs) {
		this.bombs = bombs;
	}

	public int nextId() throws IllegalIdRequest {
		if (currentId < players.size()) {
			return currentId++;
		} else {
			throw new IllegalIdRequest("all available IDs have been assigned to players.");
		}
	}

	public void plantBomb(Player plantingPlayer, Bomb plantedBomb) {
		arena.setTile(plantingPlayer.getTileCoordinate(), plantedBomb);
		int id = plantingPlayer.getId();
		for (int curId = plantingPlayer.getId(); curId <= id + plantingPlayer.getMaxBombs(); curId++) {
			if (bombs[curId] == null) {
				System.out.println("Player " + plantingPlayer.getId() + " planting bomb in [" + curId + "]");
				bombs[curId] = plantedBomb;
				break;
			}
		}
	}

}
