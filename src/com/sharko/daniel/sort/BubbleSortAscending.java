package com.sharko.daniel.sort;

@SorterClass
public class BubbleSortAscending extends SortingClass {

	/**
	 * Performs bubble sorting (ascending)
	 * 
	 * @param arr
	 *            An array to sort
	 */
	@Sorter(str = 1)
	public void sort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {

				if (arr[j - 1] > arr[j]) {
					// swap the elements
					swap(arr, j);
				}

			}
		}
	}

}
