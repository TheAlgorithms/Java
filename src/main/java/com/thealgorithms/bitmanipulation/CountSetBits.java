package com.thealgorithms.bitmanipulation;

/**
 * Utility class to count total set bits from 1 to N
 * A set bit is a bit in binary representation that is 1
 *
 * @author Your GitHub Username
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

        // Find the position of the most significant bit
        int powerOf2 = largestPowerOf2(n);

        // Count set bits at position powerOf2
        int bitsAtMsb = powerOf2 * (1 << (powerOf2 - 1));

        // Count remaining set bits from MSB position
        int msbRemainder = n - (1 << powerOf2) + 1;

        // Recursively count for remaining numbers
        int rest = n - (1 << powerOf2);

        return bitsAtMsb + msbRemainder + countSetBits(rest);
    }

    /**
     * Finds the position of the largest power of 2 less than or equal to n
     *
     * @param n the number
     * @return position of largest power of 2
     */
    private static int largestPowerOf2(int n) {
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
