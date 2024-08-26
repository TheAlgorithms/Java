package com.thealgorithms.strings;

/**
 * Class for calculating the Hamming distance between two strings of equal length.
 * <p>
 * The Hamming distance is the number of positions at which the corresponding symbols are different.
 * It is used in information theory, coding theory, and computer science.
 * </p>
 * @see <a href="https://en.wikipedia.org/wiki/Hamming_distance">Hamming distance - Wikipedia</a>
 */
public final class HammingDistance {
    private HammingDistance() {
    }

    /**
     * Calculates the Hamming distance between two strings of equal length.
     * <p>
     * The Hamming distance is defined only for strings of equal length. If the strings are not
     * of equal length, this method throws an {@code IllegalArgumentException}.
     * </p>
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return the Hamming distance between the two strings
     * @throws IllegalArgumentException if the lengths of {@code s1} and {@code s2} are not equal
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
}
