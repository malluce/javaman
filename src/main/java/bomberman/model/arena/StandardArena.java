package bomberman.model.arena;

import org.kohsuke.MetaInfServices;

import bomberman.model.config.GameConfig;
import bomberman.model.coord.TileCoordinate;
import bomberman.model.tile.AbstractTile;
import bomberman.model.tile.DestroyableTile;
import bomberman.model.tile.EmptyTile;
import bomberman.model.tile.UndestroyableTile;

/**
 * A standard arena where the borders are undestroyable blocks. Inside the arena there are randomly placed destroyable
 * blocks.
 * 
 * @author Felix Bachmann
 *
 */
@MetaInfServices(ArenaI.class)
public class StandardArena implements ArenaI {
	private final int maxPlayers = 2;

	private int size;
	private AbstractTile[][] currentMap;

	private final TileCoordinate[] spawnPoints = new TileCoordinate[maxPlayers];

	/**
	 * Delegate the call to the constructor {@link #StandardArena(int)} with the default game size value of
	 * {@link bomberman.model.config.GameConfig#GAME_SIZE}.
	 */
	public StandardArena() {
		this(GameConfig.GAME_SIZE);
	}

	/**
	 * Creates a new StandardArena with a specific size.
	 * 
	 * @param size
	 *            The size of the arena to be created. The created arena will have size * size tiles and be quadratic.
	 *            Has to be at least 4, otherwise an Exception is thrown.
	 */
	public StandardArena(int size) {
		if (size < 4) {
			throw new IllegalArgumentException(
					"Arena has to have a size of 4 at least, otherwise it would be quite boring.");
		}
		this.size = size;
		this.currentMap = new AbstractTile[size][size];
		this.initArena();
		this.spawnPoints[0] = new TileCoordinate(1, 1);
		this.spawnPoints[1] = new TileCoordinate(size - 2, size - 2);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	/**
	 * Initializes the arena i.e. places the tiles.
	 */
	private void initArena() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (x == 0 || y == 0 || x == (size - 1) || y == (size - 1)) {
					currentMap[x][y] = UndestroyableTile.getInstance();
				} else {
					if ((y == 1 && (x == 1 || x == 2)) || (x == 1 && y == 2)
							|| (y == (size - 2) && (x == (size - 2) || x == (size - 3)))
							|| (y == (size - 3) && x == (size - 2))) {
						// at spawn point or around spawn point
						currentMap[x][y] = EmptyTile.getInstance();
					} else {
						if (Math.random() > 0.35d) {
							currentMap[x][y] = EmptyTile.getInstance();
						} else {
							currentMap[x][y] = DestroyableTile.getInstance();
						}
					}
				}
			}
		}

	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public AbstractTile getTile(TileCoordinate coord) {
		return currentMap[coord.getRow()][coord.getColumn()];
	}

	@Override
	public void setTile(TileCoordinate coord, AbstractTile tile) {
		currentMap[coord.getRow()][coord.getColumn()] = tile;
	}

	@Override
	public int getMaxPlayers() {
		return maxPlayers;
	}

	@Override
	public TileCoordinate[] getSpawnPoints() {
		return this.spawnPoints;
	}

}
