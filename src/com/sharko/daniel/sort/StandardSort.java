package com.sharko.daniel.sort;

import java.util.Arrays;

@SorterClass
public class StandardSort extends SortingClass {

	/**
	 * Standard sorting using Arrays.sort
	 * 
	 * @param arr
	 *            array to sort
	 */
	@Sorter(str = 5)
	public void sort(int[] arr) {
		Arrays.sort(arr);// default sorting using java.util.Arrays
	}

}
