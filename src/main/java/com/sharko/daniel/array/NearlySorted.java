package com.sharko.daniel.array;

import java.util.Arrays;
import java.util.Random;

/**
 * Class with method for creating an increasingly filled array with a
 * few random elements in the end
 */
class NearlySorted {

    /**
     * Creates an array with increasing elements in it and a few random numbers
     * in the end.
     *
     * @param arrayLength an array length
     * @return An array with increasing elements and a few random numbers in the
     * end.
     */
    int[] createArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(100);
        }
        Arrays.sort(array);
        array[arrayLength - 1] = rand.nextInt(500);
        return array;
    }

}
