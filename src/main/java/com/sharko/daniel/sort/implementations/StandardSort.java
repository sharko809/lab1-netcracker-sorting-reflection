package com.sharko.daniel.sort.implementations;

import com.sharko.daniel.sort.annotations.Sorter;
import com.sharko.daniel.sort.annotations.SorterClass;
import com.sharko.daniel.sort.SortingClass;

import java.util.Arrays;

@SorterClass
public class StandardSort extends SortingClass {

    /**
     * Standard sorting using Arrays.sort
     *
     * @param arr array to sort
     */
    @Sorter(str = 5)
    public void sort(int[] arr) {
        Arrays.sort(arr);
    }

}
