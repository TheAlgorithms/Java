package com.thealgorithms.bitmanipulation;

/**
 * Count the total number of set bits in binary representations of all numbers from 1 to N.
 *
 * <p>This implementation uses bit manipulation and mathematical observation to
 * efficiently calculate the total number of set bits in O(log N) time.
 *
 * <p>Example:
 * N = 3 -> Binary(1):01, Binary(2):10, Binary(3):11 => Total Set Bits = 4
 *
 * <p>Reference: https://www.geeksforgeeks.org/count-total-set-bits-in-all-numbers-from-1-to-n/
 */
public final class CountTotalSetBits {

    private CountTotalSetBits() {
        // utility class
    }

    /**
     * Returns the total count of set bits in binary representations of all numbers from 1 to n.
     *
     * @param n the upper limit of the range
     * @return total number of set bits from 1 to n
     */
    public static int countTotalSetBits(int n) {
        if (n == 0) {
            return 0;
        }

        int x = largestPowerOf2(n);
        int bitsTill2x = x * (1 << (x - 1));
        int msbBits = n - (1 << x) + 1;
        int rest = n - (1 << x);

        return bitsTill2x + msbBits + countTotalSetBits(rest);
    }

    /**
     * Helper function to find the largest power of 2 less than or equal to n.
     */
    private static int largestPowerOf2(int n) {
        int x = 0;
        while ((1 << x) <= n) {
            x++;
        }
        return x - 1;
    }
}
