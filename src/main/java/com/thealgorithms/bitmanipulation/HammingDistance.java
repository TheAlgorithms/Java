package com.thealgorithms.bitmanipulation;

public class HammingDistance {

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
