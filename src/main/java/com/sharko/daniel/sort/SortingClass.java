package com.sharko.daniel.sort;

import com.sharko.daniel.sort.annotations.Sorter;

/**
 * Parent class for all sorting classes.
 */
public abstract class SortingClass {

    /**
     * Swaps two nearby elements in array.
     *
     * @param arr input array
     * @param i   array element
     */
    protected static void swap(int[] arr, int i) {
        int temp;
        temp = arr[i - 1];
        arr[i - 1] = arr[i];
        arr[i] = temp;
    }

    @Sorter(str = 0)
    protected abstract void sort(int[] arr);

}
