package bomberman.controller.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bomberman.controller.Controller;
import bomberman.controller.commands.Command;
import bomberman.model.Game;
import bomberman.model.arena.ArenaI;
import bomberman.model.config.ControlsConfig;
import bomberman.model.config.GameConfig;
import bomberman.model.config.PlayerConfig;
import bomberman.model.coord.XYCoordinate;
import bomberman.model.exceptions.IllegalIdRequestException;
import bomberman.model.player.Player;
import bomberman.view.Renderer;
import bomberman.view.config.GameConfigFrame;
import bomberman.view.config.PlayerConfigPanel;
import bomberman.view.game.GameWindow;
import bomberman.view.listener.PlayerInputListener;
import utils.Distributer;
import utils.NoDistributableElementsException;

/**
 * Starts the game when {@link bomberman.controller.commands.Command#START_GAME} is passed to
 * {@link bomberman.controller.Controller#handle(Command)}.
 * 
 * @author Felix Bachmann
 *
 */
public class GameStartHandler implements HandlerI {
	private Game game;
	private Controller ctrlr;
	private GameConfigFrame gcf;
	private PlayerConfigPanel pcp;
	private Distributer<String> spriteDistr;
	private Distributer<ControlsConfig> controlDistr;
	private List<Player> playerList;

	/**
	 * Creates a new instance.
	 * 
	 * @param ctrlr
	 *            the controller
	 */
	public GameStartHandler(Controller ctrlr) {
		Objects.requireNonNull(ctrlr);
		this.ctrlr = ctrlr;
	}

	@Override
	public void handle(Command comm) {
		assert (comm == Command.START_GAME);

		this.gcf = ctrlr.getGcf();
		this.pcp = gcf.getPcpPanel();
		this.spriteDistr = ctrlr.getSpriteDistributer();
		this.controlDistr = ctrlr.getControlsDistributer();

		/*
		 * create first the GameConfig..
		 */
		GameConfig gc = null;
		try {
			gc = createGameConfig();
		} catch (NoDistributableElementsException e) {
			System.err.println("Problems when creating game config, no distributable elements");
		}

		// .. then the game
		game = new Game(gc);

		/*
		 * players instances are created prior to the game, so their game instance needs to be set
		 */
		game.getPlayers().forEach(p -> {
			try {
				p.setGame(game);
			} catch (IllegalIdRequestException e) {
				System.err.println("no id's left, too many players in this game");
			}
		});

		// controller existed prior to the game, so the game of the controller needs to be set
		ctrlr.setGame(game);

		List<PlayerInputListener> inputListener = null;
		try {
			inputListener = createPlayerInputList();
		} catch (NoDistributableElementsException e) {
			System.err.println("Problems when creating player input handler list");
		}

		// make config frame unvisible..
		gcf.setVisible(false);

		GameWindow win = new GameWindow(game, inputListener);

		Renderer render = new Renderer(game, win);

		// .. and start game
		game.start(render, inputListener);

	}

	/**
	 * Creates the game config.
	 * 
	 * @return the created game config
	 * @throws NoDistributableElementsException
	 *             if there are not enough sprite names or game configs supplied to the distributers respectively this
	 *             eception will be thrown
	 */
	private GameConfig createGameConfig() throws NoDistributableElementsException {
		ArenaI arena = gcf.getArenaConfigPanel().getSelectedArena();
		playerList = createPlayerList();
		return new GameConfig(arena, playerList);
	}

	private List<Player> createPlayerList() throws NoDistributableElementsException {
		ArenaI arena = gcf.getArenaConfigPanel().getSelectedArena();
		Objects.requireNonNull(arena);

		List<Player> playerList = new ArrayList<Player>();

		for (int i = 1; i <= pcp.getPlayerCount(); i++) {
			String spriteName = spriteDistr.distribute();
			XYCoordinate spawn = arena.getSpawnPoints()[i - 1].toXYCoordinates(GameConfig.TILE_SIZE);
			int bombs = pcp.getBombs(i);
			int lifes = pcp.getLives(i);
			PlayerConfig config = new PlayerConfig(spriteName, spawn, bombs, lifes, null);
			playerList.add(new Player(config));
		}

		return playerList;
	}

	private List<PlayerInputListener> createPlayerInputList() throws NoDistributableElementsException {

		List<PlayerInputListener> listeners = new ArrayList<PlayerInputListener>();
		for (int i = 0; i < playerList.size(); i++) {
			ControlsConfig controls = controlDistr.distribute();
			listeners.add(new PlayerInputListener(game, playerList.get(i), controls));
		}

		return listeners;
	}

}
