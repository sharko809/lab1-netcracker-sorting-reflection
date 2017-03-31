package com.sharko.daniel.sort.implementations;

import com.sharko.daniel.sort.annotations.Sorter;
import com.sharko.daniel.sort.annotations.SorterClass;
import com.sharko.daniel.sort.SortingClass;

@SorterClass
public class BubbleSortDesc extends SortingClass {

    /**
     * Performs bubble sorting (descending)
     *
     * @param arr An array to sort
     */
    @Sorter(str = 2)
    public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] < arr[j]) {
                    swap(arr, j);
                }
            }
        }
    }
}
