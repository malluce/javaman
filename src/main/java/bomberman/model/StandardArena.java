package bomberman.model;

public class StandardArena implements ArenaI {
	private final int MAX_PLAYERS = 2;

	private int size;
	private AbstractTile[][] currentMap;

	private final TileCoordinate[] spawnPoints = new TileCoordinate[MAX_PLAYERS];

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

	public int getSize() {
		return size;
	}

	public AbstractTile[][] getCurrentMap() {
		return currentMap;
	}

	public int getMaxPlayers() {
		return MAX_PLAYERS;
	}

	public TileCoordinate[] getSpawnPoints() {
		return this.spawnPoints;
	}

}
