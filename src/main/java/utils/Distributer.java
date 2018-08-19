package utils;

import java.util.Stack;

/**
 * A class that takes instances of E and distributes them. There is no guarantee in which order the items are
 * distributed.
 * 
 * @author Felix Bachmann
 *
 * @param <E>
 *            the class of the items to distribute
 */
public class Distributer<E> {
	private Stack<E> toDistribute;

	/**
	 * Creates a new distributer with no elements.
	 */
	public Distributer() {
		toDistribute = new Stack<E>();
	}

	/**
	 * Adds an item to the distributer.
	 * 
	 * @param item
	 *            the item to add
	 */
	public void add(E item) {
		toDistribute.push(item);
	}

	/**
	 * Distributes an item. Removes this item from the distributer.
	 * 
	 * @return an item
	 * @throws NoDistributableElementsException
	 *             if there are no items left this exception is thrown
	 */
	public E distribute() throws NoDistributableElementsException {
		if (toDistribute.empty()) {
			throw new NoDistributableElementsException(
					"No more elements. Please call add to add items that i can distribute.");
		}

		return toDistribute.pop();

	}

}
