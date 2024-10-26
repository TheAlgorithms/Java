package com.thealgorithms.bitmanipulation;

/**
 * This class provides a method to detect if two integers
 * differ by exactly one bit flip.
 *
 * Example:
 * 1 (0001) and 2 (0010) differ by exactly one bit flip.
 * 7 (0111) and 3 (0011) differ by exactly one bit flip.
 *
 * @author Hardvan
 */
public final class OneBitDifference {
    private OneBitDifference() {
    }

    /**
     * Checks if two integers differ by exactly one bit.
     *
     * @param x the first integer
     * @param y the second integer
     * @return true if x and y differ by exactly one bit, false otherwise
     */
    public static boolean differByOneBit(int x, int y) {
        if (x == y) {
            return false;
        }

        int xor = x ^ y;
        return (xor & (xor - 1)) == 0;
    }
}
