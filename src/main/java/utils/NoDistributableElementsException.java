package utils;

/**
 * Thrown when a {@link Distributer} can not distribute an item because it has no tiems left.
 * 
 * @author Felix Bachmann
 *
 */
public class NoDistributableElementsException extends Exception {

	/**
	 * Creates a new instance.
	 */
	public NoDistributableElementsException() {
		super();
	}

	/**
	 * Creates a new instance with an error message.
	 * 
	 * @param message
	 *            the error message
	 */
	public NoDistributableElementsException(String message) {
		super(message);
	}

}
