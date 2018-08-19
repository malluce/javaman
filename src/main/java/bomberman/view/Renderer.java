package bomberman.view;

import java.awt.Graphics2D;
import java.util.List;

import javax.swing.SwingUtilities;

import bomberman.model.Game;
import bomberman.model.arena.ArenaI;
import bomberman.model.bomb.Bomb;
import bomberman.model.config.GameConfig;
import bomberman.model.coord.TileCoordinate;
import bomberman.model.coord.XYCoordinate;
import bomberman.model.player.Player;
import bomberman.model.tile.AbstractTile;
import bomberman.view.game.GameWindow;

/**
 * Renders the game.
 * 
 * @author Felix Bachmann
 *
 */
public class Renderer {
	private static final int FPS = 60;

	/**
	 * The milliseconds per frame.
	 */
	public static final double MILLIS_PER_FRAME = 1000 / FPS;

	private Game game;
	private GameWindow win;
	private Graphics2D gr;

	/**
	 * Creates a new renderer to render a {@link Game}.
	 * 
	 * @param game
	 *            the game to render
	 * @param win
	 *            the window in which the game is drawn into
	 * 
	 */
	public Renderer(Game game, GameWindow win) {
		this.game = game;
		this.win = win;
		this.gr = win.getImg().createGraphics();
	}

	/**
	 * Renders the game.
	 */
	public void render() {

		drawArena();

		drawBombs();

		drawPlayers();

		SwingUtilities.invokeLater(() -> win.repaint());
	}

	private void drawArena() {
		ArenaI arena = game.getArena();
		for (int i = 0; i < GameConfig.GAME_SIZE; i++) {
			for (int j = 0; j < GameConfig.GAME_SIZE; j++) {
				AbstractTile tile = arena.getTile(new TileCoordinate(i, j));
				final int finI = i;
				final int finJ = j;
				SwingUtilities.invokeLater(() -> gr.drawImage(tile.getSprite(GameConfig.TILE_SIZE),
						finI * GameConfig.TILE_SIZE, finJ * GameConfig.TILE_SIZE, null));

			}
		}
	}

	private void drawPlayers() {
		List<Player> players = game.getPlayers();
		for (Player pl : players) {
			if (pl.isAlive()) {
				SwingUtilities.invokeLater(
						() -> gr.drawImage(pl.getSprite(GameConfig.TILE_SIZE), pl.getX(), pl.getY(), null));

			}
		}
	}

	private void drawBombs() {
		Bomb[] bombs = game.getBombs();
		for (Bomb bo : bombs) {
			if (bo == null || bo.isExploding()) {
				continue;
			}
			XYCoordinate xy = bo.getTileCoordinate().toXYCoordinates(GameConfig.TILE_SIZE);
			SwingUtilities
					.invokeLater(() -> gr.drawImage(bo.getSprite(GameConfig.TILE_SIZE), xy.getX(), xy.getY(), null));
		}
	}
}
