package bomberman.model;

public class IllegalIdRequest extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 384137059519930451L;

	public IllegalIdRequest(String message) {
		super(message);
	}
}
