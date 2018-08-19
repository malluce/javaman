package bomberman.controller.exception;

/**
 * Exception which indicates that there is no handler for an command which was issued by calling
 * {@link bomberman.controller.Controller#handle(bomberman.controller.commands.Command)}.
 * 
 * @author Felix Bachmann
 *
 */
public class NoHandlerForThisCommandException extends Exception {

	/**
	 * Creates a new exception without any information.
	 */
	public NoHandlerForThisCommandException() {
		super();
	}

	/**
	 * Creates a new exception with a message.
	 * 
	 * @param message
	 *            the message of this exception
	 */
	public NoHandlerForThisCommandException(String message) {
		super(message);
	}
}
