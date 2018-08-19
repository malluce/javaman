package bomberman.controller;

import java.util.HashMap;
import java.util.Objects;

import bomberman.controller.commands.Command;
import bomberman.controller.exception.NoHandlerForThisCommandException;
import bomberman.controller.handler.ConfigStartHandler;
import bomberman.controller.handler.GameStartHandler;
import bomberman.controller.handler.HandlerI;
import bomberman.controller.handler.PauseHandler;
import bomberman.model.Game;
import bomberman.model.config.ControlsConfig;
import bomberman.view.config.GameConfigFrame;
import utils.Distributer;

/**
 * This class is the controller in the MVC pattern. It delegates {@link bomberman.controller.commands.Command}s which
 * may be issued from the view to the appropriate {@link bomberman.controller.handler.HandlerI} implementation.
 * 
 * @author Felix Bachmann
 *
 */
public class Controller implements HandlerI {
	private HashMap<Command, HandlerI> handlerMap = new HashMap<Command, HandlerI>();
	private Game game;
	private GameConfigFrame gcf;
	Distributer<String> spriteDistributer;
	Distributer<ControlsConfig> controlsDistributer;

	/**
	 * Creates a new Controller with distributers. The distributers will be used when
	 * {@link bomberman.controller.commands.Command#START_GAME} is passed to {@link #handle(Command)} to start the game.
	 * The distributers will be called to get sprite names and controls to the created players.
	 * 
	 * @param spriteDistributer
	 *            distribute sprite names
	 * @param controlsDistributer
	 *            distribute {@link bomberman.model.config.ControlsConfig}
	 */
	public Controller(Distributer<String> spriteDistributer, Distributer<ControlsConfig> controlsDistributer) {
		Objects.requireNonNull(spriteDistributer);
		Objects.requireNonNull(controlsDistributer);

		this.controlsDistributer = controlsDistributer;
		this.spriteDistributer = spriteDistributer;
		init();
	}

	/**
	 * Initializes the map for the START_* commands.
	 */
	private void init() {
		handlerMap.put(Command.START_CONFIG, new ConfigStartHandler(this));
		handlerMap.put(Command.START_GAME, new GameStartHandler(this));
	}

	/**
	 * Initializes the map for commands for which a valid Game instance is needed.
	 */
	private void initGameRelatedCommands() {
		PauseHandler ph = new PauseHandler(game);
		handlerMap.put(Command.PAUSE_GAME, ph);
	}

	/**
	 * Sets the game of the controller (i.e. the model in the MVC pattern). After this call all available commands may
	 * be issued.
	 * 
	 * @param game
	 *            the game to set
	 */
	public void setGame(Game game) {
		Objects.requireNonNull(game);
		this.game = game;
		initGameRelatedCommands();
	}

	/**
	 * Sets the game config frame of the controller.
	 * 
	 * @param gcf
	 *            the gcf to set
	 */
	public void setGameConfigFrame(GameConfigFrame gcf) {
		this.gcf = gcf;
	}

	@Override
	public void handle(Command comm) throws NoHandlerForThisCommandException {
		HandlerI handler = handlerMap.get(comm);
		if (handler == null) {
			throw new NoHandlerForThisCommandException(
					"No handler found for this command. Check the handler map in the Controller class.");
		}
		handler.handle(comm);
	}

	/**
	 * Getter for the game config frame.
	 * 
	 * @return the game config frame.
	 */
	public GameConfigFrame getGcf() {
		return gcf;
	}

	/**
	 * Getter for the sprite distributer.
	 * 
	 * @return the sprite distributer
	 */
	public Distributer<String> getSpriteDistributer() {
		return spriteDistributer;
	}

	/**
	 * Getter for the controls distributer.
	 * 
	 * @return the controls distributer
	 */
	public Distributer<ControlsConfig> getControlsDistributer() {
		return controlsDistributer;
	}

}
