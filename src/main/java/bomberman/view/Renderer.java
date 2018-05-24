package bomberman.view;

import java.awt.image.BufferedImage;
import java.util.List;

import bomberman.model.Bomb;
import bomberman.model.Game;
import bomberman.model.Player;
import bomberman.model.TileCoordinate;

/**
 * Renders the game.
 * 
 * @author Felix Bachmann
 *
 */
public class Renderer {
	private Game game;

	/**
	 * Creates a new renderer to render a {@link Game}.
	 * 
	 * @param game
	 *            the game to render
	 */
	public Renderer(Game game) {
		this.game = game;
	}

	/**
	 * Renders the {@link Game} supplied to this {@link Renderer} in the constructor into a {@link BufferedImage}
	 * 
	 * @param renderImage
	 *            the image to render
	 * @return the modified renderImage
	 */
	// TODO make more readable, add parallelity
	public BufferedImage render(BufferedImage renderImage) {
		int tileSize = game.getTileSize();
		int gameSize = game.getGameSize();
		int renderSize = tileSize * gameSize;

		List<Player> players = game.getPlayers();
		Bomb[] bombs = game.getBombs();

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

					if (!curPlayer.isAlive()) {
						continue;
					}

					int xPos = curPlayer.getX();
					int yPos = curPlayer.getY();

					if ((i >= xPos) && (i < (xPos + tileSize)) && j >= yPos && j < (yPos + tileSize)) {
						curSprite = curPlayer.getSprite(game.getTileSize());
						if (getPlayerRGB(i, j, curSprite, curPlayer) == 0xffffffff) {
							renderPlayer = false;
						} else {
							renderPlayer = true;
						}
						break;
					}
				}

				for (int z = 0; z < bombs.length; z++) {
					Bomb bomb = bombs[z];
					if (bomb == null) {
						renderBomb = false;
						continue;
					}
					TileCoordinate coord = bomb.getTileCoordinate();
					int col = coord.getColumn();
					int row = coord.getRow();
					if (i / tileSize == col && j / tileSize == row) {
						renderBomb = true;
						break;
					} else {
						renderBomb = false;
					}
				}

				if (renderPlayer) {
					renderImage.setRGB(i, j, getPlayerRGB(i, j, curSprite, curPlayer));
				} else {
					curSprite = game.getArena().getTile(new TileCoordinate(i / tileSize, j / tileSize))
							.getSprite(game.getTileSize());
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
