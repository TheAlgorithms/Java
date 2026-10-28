package com.thealgorithms.bitmanipulation;

/**
 * Utility class to count total set bits from 1 to N
 * A set bit is a bit in binary representation that is 1
 *
 * @author navadeep
 */
public final class CountSetBits {

    private CountSetBits() {
        // Utility class, prevent instantiation
    }

    /**
     * Counts total number of set bits in all numbers from 1 to n
     * Time Complexity: O(log n)
     *
     * @param n the upper limit (inclusive)
     * @return total count of set bits from 1 to n
     * @throws IllegalArgumentException if n is negative
     */
    public static int countSetBits(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative");
        }

        if (n == 0) {
            return 0;
        }

        // Find the largest power of 2 <= n
        int x = largestPowerOf2InNumber(n);

        // Total bits at position x: x * 2^(x-1)
        int bitsAtPositionX = x * (1 << (x - 1));

        // Remaining numbers after 2^x
        int remainingNumbers = n - (1 << x) + 1;

        // Recursively count for the rest
        int rest = countSetBits(n - (1 << x));

        return bitsAtPositionX + remainingNumbers + rest;
    }

    /**
     * Finds the position of the most significant bit in n
     *
     * @param n the number
     * @return position of MSB (0-indexed from right)
     */
    private static int largestPowerOf2InNumber(int n) {
        int position = 0;
        while ((1 << position) <= n) {
            position++;
        }
        return position - 1;
    }

    /**
     * Alternative naive approach - counts set bits by iterating through all numbers
     * Time Complexity: O(n log n)
     *
     * @param n the upper limit (inclusive)
     * @return total count of set bits from 1 to n
     */
    public static int countSetBitsNaive(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative");
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += Integer.bitCount(i);
        }
        return count;
    }
}
