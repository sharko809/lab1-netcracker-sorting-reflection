package com.sharko.daniel.array;

import java.util.Random;

/**
 * Class with method for creating an array filled randomly
 */
class RandomSorted {

    /**
     * Creates an array with random elements in it.
     *
     * @param arrayLength an array length
     * @return NOT sorted array with elements in range from 0 to 100.
     */
    int[] createArray(int arrayLength) {
        int[] array = new int[arrayLength];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(100);
        }
        return array;
    }
}
