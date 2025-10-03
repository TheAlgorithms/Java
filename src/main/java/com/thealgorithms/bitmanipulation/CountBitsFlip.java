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
     * Counts the number of bits that need to be flipped to convert A to B
     *
     * Algorithm:
     * 1. XOR A and B to get positions where bits differ
     * 2. Count the number of set bits in the XOR result
     * 3. Use Brian Kernighan's algorithm: n & (n-1) removes rightmost set bit
     *
     * @param A the source number
     * @param B the target number
     * @return the number of bits to flip to convert A to B
     */
    public static long countBitsFlip(long A, long B) {
        int count = 0;

        // XOR gives us positions where bits differ
        long xorResult = A ^ B;

        // Count set bits using Brian Kernighan's algorithm
        while (xorResult != 0) {
            xorResult = xorResult & (xorResult - 1); // Remove rightmost set bit
            count++;
        }

        return count;
    }

    /**
     * Alternative implementation using Long.bitCount()
     *
     * @param A the source number
     * @param B the target number
     * @return the number of bits to flip to convert A to B
     */
    public static long countBitsFlipAlternative(long A, long B) {
        return Long.bitCount(A ^ B);
    }
}
