package com.sharko.daniel.array;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author Sharko Daniel
 *         <p>
 *         Class with method for creating an increasingly filled array with a
 *         few random elements in the end
 *         </p>
 */
public class NearlySorted {

	/**
	 * Creates an array with increasing elements in it and a few random numbers
	 * in the end.
	 * 
	 * @param arrayLength
	 *            an array length
	 * @return An array with increasing elements and a few random numbers in the
	 *         end.
	 */
	public int[] createArray(int arrayLength) {
		Random rand = new Random();
		int[] array = new int[arrayLength];
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(100);
		}
		Arrays.sort(array);
		array[arrayLength-1]=rand.nextInt(500);
		return array;
	}

	/**
	 * Conjunct two arrays
	 * 
	 * @param arr1
	 *            first array
	 * @param arr2
	 *            second array
	 * @param finalArray
	 *            array containing two other arrays
	 * @return The conjunction of two arrays
	 */
	@Deprecated
	private int[] arrayConjunction(int[] arr1, int[] arr2, int[] finalArray) {
		System.arraycopy(arr1, 0, finalArray, 0, arr1.length);
		System.arraycopy(arr2, 0, finalArray, arr1.length, arr2.length);
		return finalArray;
	}
}
