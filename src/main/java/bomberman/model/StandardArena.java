package bomberman.model;

public class StandardArena implements ArenaI {
	private int size;
	private TileI[][] currentMap;

	public StandardArena(int size) {
		this.size = 16;
		this.initArena();
	}

	public void initArena() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (x == 0 || y == 0 || x == (size - 1) || y == (size - 1)) {
					currentMap[x][y] = new UndestroyableTile();
				} else {
					currentMap[x][y] = new EmptyTile();
				}
			}
		}

	}

	public void resetArena() {
		// TODO
	}

}
