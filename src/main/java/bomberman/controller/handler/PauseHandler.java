package bomberman.controller.handler;

import bomberman.controller.commands.Command;
import bomberman.model.Game;

/**
 * Handles the {@link bomberman.controller.commands.Command#PAUSE_GAME} command. Pauses/unpause the game.
 * 
 * @author Felix Bachmann
 *
 */
public class PauseHandler implements HandlerI {
	private Game game;

	/**
	 * Creates a new instance with the supplied game.
	 * 
	 * @param game
	 *            the game to pause/unpause by this handler
	 */
	public PauseHandler(Game game) {
		this.game = game;
	}

	@Override
	public void handle(Command comm) {
		assert (comm == Command.PAUSE_GAME);
		game.toggleRunning();
	}

}
