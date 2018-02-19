package bomberman.model;

import java.util.List;

public class StandardArena implements ArenaI {
	private int size;
	private AbstractTile[][] currentMap;
	private List<AbstractPlayer> players;

	public StandardArena(int size) {
		this.size = size;
		this.currentMap = new AbstractTile[size][size];
		this.initArena();
	}

	private void initArena() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (x == 0 || y == 0 || x == (size - 1) || y == (size - 1)) {
					currentMap[x][y] = UndestroyableTile.getInstance();
				} else {
					if (Math.random() > 0.25d) {
						currentMap[x][y] = EmptyTile.getInstance();
					} else {
						currentMap[x][y] = DestroyableTile.getInstance();
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

}
