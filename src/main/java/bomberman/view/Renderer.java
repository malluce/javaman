package bomberman.view;

import java.awt.Graphics2D;
import java.util.List;

import bomberman.Main;
import bomberman.model.AbstractTile;
import bomberman.model.ArenaI;
import bomberman.model.Bomb;
import bomberman.model.Game;
import bomberman.model.Player;
import bomberman.model.TileCoordinate;
import bomberman.model.XYCoordinate;

/**
 * Renders the game.
 * 
 * @author Felix Bachmann
 *
 */
public class Renderer {
	private Game game;
	private Window win;
	private Graphics2D gr;

	/**
	 * Creates a new renderer to render a {@link Game}.
	 * 
	 * @param game
	 *            the game to render
	 * @param win
	 *            the window in which the game is drawn into, contains gr. is repainted after rednering
	 * @param gr
	 *            graphics to render into
	 */
	public Renderer(Game game, Window win, Graphics2D gr) {
		this.game = game;
		this.win = win;
		this.gr = gr;
	}

	/**
	 * Renders the game.
	 */
	public void render() {
		drawArena();

		drawBombs();

		drawPlayers();

		win.repaint();
	}

	private void drawArena() {
		ArenaI arena = game.getArena();
		for (int i = 0; i < Main.GAME_SIZE; i++) {
			for (int j = 0; j < Main.GAME_SIZE; j++) {
				AbstractTile tile = arena.getTile(new TileCoordinate(i, j));
				gr.drawImage(tile.getSprite(Main.TILE_SIZE), i * Main.TILE_SIZE, j * Main.TILE_SIZE, null);
			}
		}
	}

	private void drawPlayers() {
		List<Player> players = game.getPlayers();
		for (Player pl : players) {
			if (pl.isAlive()) {
				gr.drawImage(pl.getSprite(Main.TILE_SIZE), pl.getX(), pl.getY(), null);
			}
		}
	}

	private void drawBombs() {
		Bomb[] bombs = game.getBombs();
		for (Bomb bo : bombs) {
			if (bo == null || bo.isExploding()) {
				continue;
			}
			XYCoordinate xy = bo.getTileCoordinate().toXYCoordinates(Main.TILE_SIZE);
			gr.drawImage(bo.getSprite(Main.TILE_SIZE), xy.getX(), xy.getY(), null);
		}
	}
}
