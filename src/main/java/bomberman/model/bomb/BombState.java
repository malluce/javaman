package bomberman.model.bomb;

/**
 * Models the state a bomb can be in.
 * 
 * @author Felix Bachmann
 *
 */
public enum BombState {
	/**
	 * the bomb is ticking (time between planting and exploding)
	 */
	TICKING,
	/**
	 * the bomb is exploding (time between finished ticking and vanishing)
	 */
	EXPLODING,
	/**
	 * the bomb has finished. its life ended and it can be deleted
	 */
	FINISHED
}
