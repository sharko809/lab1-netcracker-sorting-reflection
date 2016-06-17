package com.sharko.daniel.array;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author Sharko Daniel
 *         <p>
 *         Class with method for creating an array filled increasingly
 *         </p>
 */
public class SortedArray {

	/**
	 * Creates an array with increasing elements in it.
	 * 
	 * @param arrayLength
	 *            an array length
	 * @return An array with increasing elements.
	 */
	public int[] createArray(int arrayLength) {
		Random rand = new Random();
		int[] array = new int[arrayLength];
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(100);
		}
		Arrays.sort(array);
		return array;
	}
}
