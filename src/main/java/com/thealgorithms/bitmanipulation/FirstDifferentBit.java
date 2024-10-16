package com.thealgorithms.bitmanipulation;

/**
 * This class provides a method to find the first differing bit
 * between two integers.
 *
 * Example:
 *  x = 10 (1010 in binary)
 *  y = 12 (1100 in binary)
 *  The first differing bit is at index 1 (0-based)
 *  So, the output will be 1
 *
 * @author Hardvan
 */
public final class FirstDifferentBit {
    private FirstDifferentBit() {
    }

    /**
     * Identifies the index of the first differing bit between two integers.
     * Steps:
     * 1. XOR the two integers to get the differing bits
     * 2. Find the index of the first set bit in XOR result
     *
     * @param x the first integer
     * @param y the second integer
     * @return the index of the first differing bit (0-based)
     */
    public static int firstDifferentBit(int x, int y) {
        int diff = x ^ y;
        return Integer.numberOfTrailingZeros(diff);
    }
}
