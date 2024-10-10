package com.thealgorithms.bitmanipulation;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Given two integers x and y, calculate the Hamming distance.
 * Example:
 * Input: x = 1, y = 4
 * Output: 2
 * Explanation: 1 (0001) and 4 (0100) have 2 differing bits.
 *
 * @author Hardvan
 */
public final class HammingDistance {
    private HammingDistance() {
    }

    /**
     * Calculates the Hamming distance between two integers.
     * The Hamming distance is the number of differing bits between the two integers.
     *
     * @param x The first integer.
     * @param y The second integer.
     * @return The Hamming distance (number of differing bits).
     */
    public static int hammingDistance(int x, int y) {
        int xor = x ^ y;
        return Integer.bitCount(xor);
    }
}
