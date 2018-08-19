package bomberman.controller.handler;

import bomberman.controller.Controller;
import bomberman.controller.commands.Command;
import bomberman.controller.exception.NoHandlerForThisCommandException;

/**
 * Subclasses has to be able to handle {@link Command}s.
 * 
 * @author Felix Bachmann
 *
 */
public interface HandlerI {
	/**
	 * Handle a command.
	 * 
	 * @param comm
	 *            the command to handle
	 * @throws NoHandlerForThisCommandException
	 *             may be thrown when the conmmand can not be handled because there is no game set. Avoid this by
	 *             calling {@link Controller#setGame(bomberman.model.Game)}
	 */
	void handle(Command comm) throws NoHandlerForThisCommandException;
}
