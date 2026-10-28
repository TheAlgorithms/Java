package com.thealgorithms.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Factorizes a string into its Lyndon words using Duval's algorithm.
 *
 * <p>Duval's algorithm runs in O(n) time and uses O(1) auxiliary working
 * space, excluding the returned list and its strings.
 *
 * <p>Reference:
 * https://cp-algorithms.com/string/lyndon_factorization.html
 */
public final class LyndonFactorization {
    private LyndonFactorization() {
    }

    /**
     * Returns the standard Lyndon factorization of {@code text}.
     *
     * <p>The returned factors are in non-increasing lexicographic order
     * according to Java's UTF-16 code-unit ordering.
     *
     * @param text input string to factorize
     * @return Lyndon factors in non-increasing lexicographic order
     * @throws IllegalArgumentException if {@code text} is null
     */
    public static List<String> factorize(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text must not be null");
        }

        List<String> factors = new ArrayList<>();
        int n = text.length();
        int start = 0;

        while (start < n) {
            int candidate = start + 1;
            int match = start;

            while (candidate < n && text.charAt(match) <= text.charAt(candidate)) {
                if (text.charAt(match) < text.charAt(candidate)) {
                    match = start;
                } else {
                    match++;
                }
                candidate++;
            }

            int length = candidate - match;

            while (start <= match) {
                factors.add(text.substring(start, start + length));
                start += length;
            }
        }

        return factors;
    }
}
