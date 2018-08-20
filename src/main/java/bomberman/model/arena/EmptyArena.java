package bomberman.model.arena;

import org.kohsuke.MetaInfServices;

import bomberman.model.config.GameConfig;
import bomberman.model.coord.TileCoordinate;
import bomberman.model.tile.AbstractTile;
import bomberman.model.tile.EmptyTile;
import bomberman.model.tile.UndestroyableTile;

/**
 * An empty arena. Empty means that there is just a one tile thick boundary of {@link UndestroyableTile} and every other
 * tile is an {@link EmptyTile}.
 * 
 * @author Felix Bachmann
 *
 */
@MetaInfServices(ArenaI.class)
public class EmptyArena implements ArenaI {
	private final int maxPlayers = 1;
	private int size;
	private AbstractTile[][] map;

	/**
	 * Delegate the call to the constructor {@link #EmptyArena(int)} with the default game size value of
	 * {@link bomberman.model.config.GameConfig#GAME_SIZE}.
	 */
	public EmptyArena() {
		this(GameConfig.GAME_SIZE);
	}

	/**
	 * Creates a new size x size EmptyArena.
	 * 
	 * @param size
	 *            the size of the quadratic EmptyArena
	 */
	public EmptyArena(int size) {
		if (size < 4) {
			throw new IllegalArgumentException("EmptyArena has to be at least 4 x 4 in size.");
		}

		this.size = size;
		init();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	@Override
	public int getMaxPlayers() {
		return maxPlayers;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public AbstractTile getTile(TileCoordinate coord) {
		return map[coord.getRow()][coord.getColumn()];
	}

	@Override
	public void setTile(TileCoordinate coord, AbstractTile newTile) {
		map[coord.getRow()][coord.getColumn()] = newTile;
	}

	@Override
	public TileCoordinate[] getSpawnPoints() {
		TileCoordinate[] spawns = { new TileCoordinate(1, 1), new TileCoordinate(size - 2, size - 2) };
		return spawns;
	}

	private void init() {
		map = new AbstractTile[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == 0 || i == (size - 1) || j == 0 || j == (size - 1)) {
					map[i][j] = UndestroyableTile.getInstance();
				} else {
					map[i][j] = EmptyTile.getInstance();
				}
			}
		}
	}

}
