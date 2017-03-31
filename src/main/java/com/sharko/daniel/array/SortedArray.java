package com.sharko.daniel.array;

import java.util.Arrays;
import java.util.Random;

/**
 * Class with method for creating an array filled increasingly
 */
class SortedArray {

    /**
     * Creates an array with ascending elements in it.
     *
     * @param arrayLength an array length
     * @return An array with increasing elements.
     */
    int[] createArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(100);
        }
        Arrays.sort(array);
        return array;
    }
}
