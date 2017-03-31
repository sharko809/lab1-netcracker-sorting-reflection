package com.sharko.daniel.sort.implementations;

import com.sharko.daniel.sort.annotations.Sorter;
import com.sharko.daniel.sort.annotations.SorterClass;
import com.sharko.daniel.sort.SortingClass;

import java.util.Arrays;

@SorterClass
public class MergeSort extends SortingClass {

    private static int[] merge(int[] arr_1, int[] arr_2) {
        int len_1 = arr_1.length, len_2 = arr_2.length;
        int a = 0, b = 0, len = len_1 + len_2; // a,b - counters in array
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            if (b < len_2 && a < len_1) {
                if (arr_1[a] > arr_2[b])
                    result[i] = arr_2[b++];
                else
                    result[i] = arr_1[a++];
            } else if (b < len_2) {
                result[i] = arr_2[b++];
            } else {
                result[i] = arr_1[a++];
            }
        }
        return result;
    }

    @Sorter(str = 4)
    @Override
    public void sort(int[] arr) {
        int len = arr.length;
        int n = 1; // multiplicity of comparison
        int shift; // shift in array
        int two_size; // elements in second array
        int[] arr2;
        while (n < len) {
            shift = 0;
            while (shift < len) {
                if (shift + n >= len)
                    break;
                two_size = (shift + n * 2 > len) ? (len - (shift + n)) : n;
                arr2 = merge(Arrays.copyOfRange(arr, shift, shift + n),
                        Arrays.copyOfRange(arr, shift + n, shift + n + two_size));
                System.arraycopy(arr2, 0, arr, shift, n + two_size);
                shift += n * 2;
            }
            n *= 2;
        }
    }

}
