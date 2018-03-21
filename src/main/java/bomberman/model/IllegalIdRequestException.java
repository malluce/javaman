package bomberman.model;

/**
 * Thrown when there are no more IDs that a game can distribute but a player tries to join the game.
 * 
 * @author Felix Bachmann
 *
 */
public class IllegalIdRequestException extends Exception {
	private static final long serialVersionUID = 384137059519930451L;

	/**
	 * Creates a new IllegalIdRequestException.
	 * 
	 * @param message
	 *            the message which is attached to this exception
	 */
	public IllegalIdRequestException(String message) {
		super(message);
	}
}
