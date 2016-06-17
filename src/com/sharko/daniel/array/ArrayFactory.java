package com.sharko.daniel.array;

/**
 * 
 * @author Daniel Sharko
 *         <p>
 *         Class for creating an array with any type of filling
 *         </p>
 */
public class ArrayFactory {

	/**
	 * An array length
	 */
	int arrayLength;

	/**
	 * Returns the array length.
	 * 
	 * @return array length
	 */
	public int getArrayLength() {
		return arrayLength;
	}

	public ArrayFactory(int arrayLength) {
		this.arrayLength = arrayLength;
	}

	/**
	 * This method returns an array filled in particular way
	 * 
	 * @param id
	 *            An id specifying an array type:
	 *            <li>SORTED - an array sorted increasingly</li>
	 *            <li>NEARLY_SORTED - an array sorted increasingly with some
	 *            random numbers in the end of it</li>
	 *            <li>REVERSE_SORTED - an array sorted decreasingly</li>
	 *            <li>RANDOM_SORTED - an array filled with random numbers</li>
	 * @return An array specified by <b>id</b>
	 */
	public int[] getArray(ArrayType id) {
		switch (id) {
		case SORTED:
			return new SortedArray().createArray(arrayLength);
		case NEARLY_SORTED:
			return new NearlySorted().createArray(arrayLength);
		case REVERSE_SORTED:
			return new ReverseSorted().createArray(arrayLength);
		case RANDOM_SORTED:
			return new RandomSorted().createArray(arrayLength);
		default:
			System.out.println("Wrong array type");
			return null;
		}

	}

}
