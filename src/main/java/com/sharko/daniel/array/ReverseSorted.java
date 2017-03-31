package com.sharko.daniel.array;

import java.util.Arrays;
import java.util.Random;

/**
 * Class with method for creating an array filled decreasingly
 */
class ReverseSorted {

    /**
     * Creates an array with descending elements in it.
     *
     * @param arrayLength an array length
     * @return An array with decreasing elements.
     */
    int[] createArray(int arrayLength) {
        int[] array = new int[arrayLength];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt();
        }
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            reverseElements(array, i);
        }
        return array;
    }

    /**
     * Reverses an elements of the array
     *
     * @param array array to reverse
     * @param i     element of the array
     */
    private void reverseElements(int[] array, int i) {
        int temp = array[i];// holding variable
        array[i] = array[array.length - i - 1];
        array[array.length - i - 1] = temp;
    }
}
