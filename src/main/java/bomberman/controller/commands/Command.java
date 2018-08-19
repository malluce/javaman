package bomberman.controller.commands;

import bomberman.controller.Controller;
import bomberman.controller.handler.HandlerI;
import bomberman.view.config.GameConfigFrame;

/**
 * The commands which may be issued by the user in the view ({@link GameConfigFrame} and are handled by the controller
 * and handlers({@link Controller} {@link HandlerI}).
 * 
 * @author Felix Bachmann
 *
 */
public enum Command {
	/**
	 * Issued to start the game, that is start the game logic in the model and the view.
	 */
	START_GAME,
	/**
	 * Issued to pause or unpause the game.
	 */
	PAUSE_GAME,
	/**
	 * Issued to start the configuration of the game (before starting the game).
	 */
	START_CONFIG
}
