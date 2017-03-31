package com.sharko.daniel.util;

import java.util.Random;
import java.util.function.Function;

/**
 * Class containing util methods and constants.
 */
public class Util {

    /**
     * Package storing sort classes
     */
    public static final String SORT_PACKAGE = "com.sharko.daniel.sort.implementations";
    /**
     * Excel file extension to work with
     */
    public static final String EXCEL_FILE_EXTENSION = ".xlsx";

    /**
     * Generates a number to serve as an array length (10k-15k).
     */
    public static final Function<Void, Integer> getArrayElementsNumber = (Void) -> new Random().nextInt(5000) + 10000;

}
