package com.sharko.daniel.sort.implementations;

import com.sharko.daniel.sort.annotations.Sorter;
import com.sharko.daniel.sort.annotations.SorterClass;
import com.sharko.daniel.sort.SortingClass;

@SorterClass
public class RecursiveSort extends SortingClass {

    private static void doSort(int start, int end, int[] array) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (array[i] <= array[cur])) {
                i++;
            }
            while (j > cur && (array[cur] <= array[j])) {
                j--;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(start, cur, array);
        doSort(cur + 1, end, array);
    }

    @Sorter(str = 6)
    public void sort(int[] arr) {
        int startIndex = 0;
        int endIndex = arr.length - 1;
        doSort(startIndex, endIndex, arr);
    }
}
