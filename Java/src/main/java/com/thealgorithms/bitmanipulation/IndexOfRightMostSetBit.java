package com.thealgorithms.bitmanipulation;

/**
 * Find The Index Of Right Most SetBit
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public final class IndexOfRightMostSetBit {
    private IndexOfRightMostSetBit() {
    }
    public static int indexOfRightMostSetBit(int n) {
        if (n == 0) {
            return -1; // No set bits
        }

        // Handle negative numbers by finding the two's complement
        if (n < 0) {
            n = -n;
            n = n & (~n + 1); // Get the rightmost set bit in positive form
        }

        int index = 0;
        while ((n & 1) == 0) {
            n = n >> 1;
            index++;
        }

        return index;
    }
}
