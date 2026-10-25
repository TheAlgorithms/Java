package com.thealgorithms.strings;

/**
 * Finds the lexicographically smallest cyclic rotation of a string using
 * Booth's algorithm.
 *
 * <p>The algorithm compares candidate rotations without materializing them,
 * running in O(n) time and O(1) auxiliary space.</p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Lexicographically_minimal_string_rotation">
 *     Lexicographically minimal string rotation</a>
 */
public final class BoothsAlgorithm {
    private BoothsAlgorithm() {
    }

    /**
     * Returns the starting index of the lexicographically smallest cyclic
     * rotation of {@code input}. If several rotations are equal and minimal,
     * the algorithm returns the smallest starting index among them.
     *
     * <p>For example, the smallest rotation of {@code "baca"} is
     * {@code "abac"}, which starts at index {@code 3}.</p>
     *
     * @param input the string whose minimal cyclic rotation is sought
     * @return the starting index of the minimal rotation, or {@code 0} for an
     *         empty string
     * @throws IllegalArgumentException if {@code input} is {@code null}
     */
    public static int minimalRotationIndex(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input string must not be null");
        }

        int length = input.length();
        if (length == 0) {
            return 0;
        }

        int first = 0;
        int second = 1;
        int offset = 0;

        while (first < length && second < length && offset < length) {
            char firstChar = input.charAt((first + offset) % length);
            char secondChar = input.charAt((second + offset) % length);

            if (firstChar == secondChar) {
                offset++;
                continue;
            }

            if (firstChar > secondChar) {
                first += offset + 1;
                if (first <= second) {
                    first = second + 1;
                }
            } else {
                second += offset + 1;
                if (second <= first) {
                    second = first + 1;
                }
            }
            offset = 0;
        }

        return Math.min(first, second);
    }
}
