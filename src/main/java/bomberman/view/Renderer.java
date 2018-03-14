package bomberman.view;

import java.awt.image.BufferedImage;
import java.util.List;

import bomberman.model.AbstractTile;
import bomberman.model.Game;
import bomberman.model.Player;

public class Renderer {
	private Game game;

	public Renderer(Game game) {
		this.game = game;
	}

	public BufferedImage render(BufferedImage renderImage) throws IllegalRenderSizesException {
		int tileSize = game.getTileSize();
		int gameSize = game.getGameSize();
		int renderSize = tileSize * gameSize;

		AbstractTile[][] map = game.getArena().getCurrentMap();

		assert map.length == map[0].length; // only quadratic maps are allowed

		List<Player> players = game.getPlayers();

		BufferedImage curSprite = null;
		Player curPlayer = null;
		boolean renderPlayer = false;
		for (int i = 0; i < renderSize; i++) {
			for (int j = 0; j < renderSize; j++) {
				renderPlayer = false;
				for (int x = 0; x < players.size(); x++) {
					curPlayer = players.get(x);
					int xPos = curPlayer.getX();
					int yPos = curPlayer.getY();

					if ((i >= xPos) && (i < (xPos + tileSize)) && j >= yPos && j < (yPos + tileSize)) {
						curSprite = curPlayer.getSprite();
						if (getPlayerRGB(i, j, curSprite, curPlayer) == 0xffffffff) {
							renderPlayer = false;
						} else {
							renderPlayer = true;
						}
						break;
					}
				}
				if (!renderPlayer) {
					curSprite = map[j / tileSize][i / tileSize].getSprite();
					renderImage.setRGB(i, j, getTileRGB(i, j, curSprite));
				} else {
					renderImage.setRGB(i, j, getPlayerRGB(i, j, curSprite, curPlayer));
				}

			}
		}
		return renderImage;
	}

	private int getPlayerRGB(int i, int j, BufferedImage sprite, Player player) {
		int tileSize = game.getTileSize();
		return sprite.getRGB((i - player.getX()) % tileSize, (j - player.getY()) % tileSize);
	}

	private int getTileRGB(int i, int j, BufferedImage sprite) {
		int tileSize = game.getTileSize();
		return sprite.getRGB(i % tileSize, j % tileSize);
	}
}
