package com.thealgorithms.strings;

/**
 * Hamming distance (consolidated implementation).
 *
 * <p>Resolves duplicate implementations: single class for Hamming distance over strings,
 * integers, and binary strings. Previously duplicated in strings, bitmanipulation, and others.cn.
 *
 * <p>The Hamming distance is the number of positions at which the corresponding symbols differ.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Hamming_distance">Hamming distance - Wikipedia</a>
 */
public final class HammingDistance {
    private HammingDistance() {
    }

    /**
     * Calculates the Hamming distance between two strings of equal length.
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return the number of positions where the characters differ
     * @throws IllegalArgumentException if either string is null or lengths differ
     */
    public static int calculateHammingDistance(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        if (s1.length() != s2.length()) {
            throw new IllegalArgumentException("String lengths must be equal");
        }
        int distance = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    /**
     * Calculates the Hamming distance between two integers (number of differing bits).
     *
     * @param x the first integer
     * @param y the second integer
     * @return the number of bit positions where the two integers differ
     */
    public static int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    /**
     * Computes the Hamming distance between two binary strings (only '0' and '1' allowed).
     *
     * @param bitsStrA first binary string
     * @param bitsStrB second binary string (must have same length as bitsStrA)
     * @return the number of positions where the two binary strings differ
     * @throws IllegalArgumentException if lengths differ or any character is not '0' or '1'
     */
    public static int computeBinary(String bitsStrA, String bitsStrB) {
        if (bitsStrA == null || bitsStrB == null) {
            throw new IllegalArgumentException("Input strings must not be null");
        }
        if (bitsStrA.length() != bitsStrB.length()) {
            throw new IllegalArgumentException("Input strings must have the same length.");
        }
        int distance = 0;
        for (int i = 0; i < bitsStrA.length(); i++) {
            char a = bitsStrA.charAt(i);
            char b = bitsStrB.charAt(i);
            if (a != '0' && a != '1') {
                throw new IllegalArgumentException("Input must be a binary string.");
            }
            if (b != '0' && b != '1') {
                throw new IllegalArgumentException("Input must be a binary string.");
            }
            if (a != b) {
                distance++;
            }
        }
        return distance;
    }
}
