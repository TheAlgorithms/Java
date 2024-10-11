package com.thealgorithms.bitmanipulation;

/**
 * Lowest Set Bit
 * @author Prayas Kumar (https://github.com/prayas7102)
 */

public final class LowestSetBit {
    // Private constructor to hide the default public one
    private LowestSetBit() {
    }
    /**
     * Isolates the lowest set bit of the given number. For example, if n = 18
     * (binary: 10010), the result will be 2 (binary: 00010).
     *
     * @param n the number whose lowest set bit will be isolated
     * @return the isolated lowest set bit of n
     */
    public static int isolateLowestSetBit(int n) {
        // Isolate the lowest set bit using n & -n
        return n & -n;
    }
    /**
     * Clears the lowest set bit of the given number.
     * For example, if n = 18 (binary: 10010), the result will be 16 (binary: 10000).
     *
     * @param n the number whose lowest set bit will be cleared
     * @return the number after clearing its lowest set bit
     */
    public static int clearLowestSetBit(int n) {
        // Clear the lowest set bit using n & (n - 1)
        return n & (n - 1);
    }
}
