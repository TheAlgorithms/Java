package com.thealgorithms.bitmanipulation;

/**
 * Implementation to count number of bits to be flipped to convert A to B
 *
 * Problem: Given two numbers A and B, count the number of bits needed to be
 * flipped to convert A to B.
 *
 * Example:
 * A = 10 (01010 in binary)
 * B = 20 (10100 in binary)
 * XOR = 30 (11110 in binary) - positions where bits differ
 * Answer: 4 bits need to be flipped
 *
 * Time Complexity: O(log n) - where n is the number of set bits
 * Space Complexity: O(1)
 *
 *@author [Yash Rajput](https://github.com/the-yash-rajput)
 */
public final class CountBitsFlip {

    private CountBitsFlip() {
        throw new AssertionError("No instances.");
    }

    /**
     * Counts the number of bits that need to be flipped to convert a to b
     *
     * Algorithm:
     * 1. XOR a and b to get positions where bits differ
     * 2. Count the number of set bits in the XOR result
     * 3. Use Brian Kernighan's algorithm: n & (n-1) removes rightmost set bit
     *
     * @param a the source number
     * @param b the target number
     * @return the number of bits to flip to convert A to B
     */
    public static long countBitsFlip(long a, long b) {
        int count = 0;

        // XOR gives us positions where bits differ
        long xorResult = a ^ b;

        // Count set bits using Brian Kernighan's algorithm
        while (xorResult != 0) {
            xorResult = xorResult & (xorResult - 1); // Remove rightmost set bit
            count++;
        }

        return count;
    }

    /**
     * Alternative implementation using Long.bitCount().
     *
     * @param a the source number
     * @param b the target number
     * @return the number of bits to flip to convert a to b
     */
    public static long countBitsFlipAlternative(long a, long b) {
        return Long.bitCount(a ^ b);
    }
}
