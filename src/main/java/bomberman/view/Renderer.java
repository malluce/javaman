package bomberman.view;

import java.awt.image.BufferedImage;
import java.util.List;

import bomberman.model.AbstractTile;
import bomberman.model.Game;
import bomberman.model.Player;

public class Renderer {
	private final int RENDER_SIZE;
	private final int TILE_SIZE;

	public Renderer(int renderSize, int tileSize) throws IllegalRenderSizesException {
		if (renderSize % tileSize != 0) {
			throw new IllegalRenderSizesException("tileSize has to be a divisor of renderSize.");
		}
		this.RENDER_SIZE = renderSize;
		this.TILE_SIZE = tileSize;
	}

	public BufferedImage render(Game game, BufferedImage renderImage) throws IllegalRenderSizesException {
		AbstractTile[][] map = game.getArena().getCurrentMap();

		assert map.length == map[0].length; // only quadratic maps are allowed
		if (map.length * TILE_SIZE != RENDER_SIZE) {
			throw new IllegalRenderSizesException("the arena can not be rendered into an image of size " + RENDER_SIZE);
		}

		List<Player> players = game.getPlayers();

		BufferedImage curSprite = null;
		Player curPlayer;
		boolean renderPlayer = false;
		for (int i = 0; i < RENDER_SIZE; i++) {
			for (int j = 0; j < RENDER_SIZE; j++) {
				renderPlayer = false;
				for (int x = 0; x < players.size(); x++) {
					curPlayer = players.get(x);
					int xPos = curPlayer.getX();
					int yPos = curPlayer.getY();
					if ((i >= xPos) && (i < (xPos + TILE_SIZE)) && j >= yPos && j < (yPos + TILE_SIZE)) {
						curSprite = curPlayer.getSprite();
						if (curSprite.getRGB(i % TILE_SIZE, j % TILE_SIZE) == 0xffffffff) {
							renderPlayer = false;
						} else {
							renderPlayer = true;
						}
						break;
					}
				}
				if (!renderPlayer) {
					curSprite = map[i / TILE_SIZE][j / TILE_SIZE].getSprite();
				}
				renderImage.setRGB(i, j, curSprite.getRGB(i % TILE_SIZE, j % TILE_SIZE));
			}
		}
		return renderImage;
	}
}
