package bomberman.view;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

import bomberman.model.Bomb;
import bomberman.model.Game;
import bomberman.model.Player;
import bomberman.model.TileCoordinate;

public class Renderer {
	private Game game;

	public Renderer(Game game) {
		this.game = game;
	}

	public BufferedImage render(BufferedImage renderImage) {
		int tileSize = game.getTileSize();
		int gameSize = game.getGameSize();
		int renderSize = tileSize * gameSize;

		List<Player> players = game.getPlayers();
		List<Bomb> bombs = game.getBombs();

		BufferedImage curSprite = null;
		Player curPlayer = null;
		boolean renderPlayer = false;
		boolean renderBomb = false;
		for (int i = 0; i < renderSize; i++) {
			for (int j = 0; j < renderSize; j++) {
				renderPlayer = false;
				renderBomb = false;
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
				for (Iterator<Bomb> bombIt = bombs.iterator(); bombIt.hasNext();) {
					Bomb bomb = bombIt.next();
					int col = bomb.getCol();
					int row = bomb.getRow();
					if (i / tileSize == col && j / tileSize == row) {
						if (!renderPlayer) {
							curSprite = bomb.getSprite();
						}

						renderBomb = true;
						break;
					} else {
						renderBomb = false;
					}

				}
				if (renderPlayer) {
					renderImage.setRGB(i, j, getPlayerRGB(i, j, curSprite, curPlayer));
				} else if (renderBomb) {
					renderImage.setRGB(i, j, getTileRGB(i, j, curSprite));
				} else {
					curSprite = game.getArena().getTile(new TileCoordinate(i / tileSize, j / tileSize)).getSprite();
					renderImage.setRGB(i, j, getTileRGB(i, j, curSprite));
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
