package com.sharko.daniel.sort.implementations;

import com.sharko.daniel.sort.annotations.Sorter;
import com.sharko.daniel.sort.annotations.SorterClass;
import com.sharko.daniel.sort.SortingClass;

@SorterClass
public class SwapSort extends SortingClass {

    @Sorter(str = 3)
    @Override
    public void sort(int[] arr) {
        int length = arr.length;
        int limitIndex = 1;
        while (limitIndex < length) {
            int currentIndex = length - 1;
            while (currentIndex > limitIndex) {
                if (arr[currentIndex - 1] > arr[currentIndex]) {
                    int temp = arr[currentIndex - 1];
                    arr[currentIndex - 1] = arr[currentIndex];
                    arr[currentIndex] = temp;
                }
                currentIndex = currentIndex - 1;
            }
            limitIndex = limitIndex + 1;
        }
    }

}
