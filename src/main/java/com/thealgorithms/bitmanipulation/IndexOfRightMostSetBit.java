package com.thealgorithms.bitmanipulation;

/**
 * Utility class for bit manipulation operations.
 * This class provides methods to work with bitwise operations.
 * Specifically, it includes a method to find the index of the rightmost set bit
 * in an integer.
 * This class is not meant to be instantiated.
 *
 * Author: Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */
public final class IndexOfRightMostSetBit {

    private IndexOfRightMostSetBit() {
    }

    /**
     * Finds the index of the rightmost set bit in the given integer.
     * The index is zero-based, meaning the rightmost bit has an index of 0.
     *
     * @param n the integer to check for the rightmost set bit
     * @return the index of the rightmost set bit; -1 if there are no set bits
     *         (i.e., the input integer is 0)
     */
    public static int indexOfRightMostSetBit(int n) {
        if (n == 0) {
            return -1; // No set bits
        }

        // Handle negative numbers by finding the two's complement
        if (n < 0) {
            n = -n;
            n = n & (~n + 1); // Isolate the rightmost set bit
        }

        int index = 0;
        while ((n & 1) == 0) {
            n = n >> 1;
            index++;
        }

        return index;
    }
}
