package com.sharko.daniel.array;

import java.util.Random;

/**
 * 
 * @author Sharko Daniel
 *         <p>
 *         Class with method for creating an array filled randomly
 *         </p>
 */
public class RandomSorted {

	/**
	 * Creates an array with random elements in it.
	 * 
	 * @param arrayLength
	 *            an array length
	 * @return NOT sorted array with elements in range from 0 to 100.
	 */
	public int[] createArray(int arrayLength) {
		int[] array = new int[arrayLength];
		Random rand = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(100);
		}
		return array;
	}
}
