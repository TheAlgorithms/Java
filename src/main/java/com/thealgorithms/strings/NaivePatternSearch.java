package com.thealgorithms.strings;

import java.util.ArrayList;
import java.util.List;

public final class NaivePatternSearch {

    // Private constructor to prevent instantiation
    private NaivePatternSearch() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Naive pattern searching algorithm.
     *
     * @param text the text to be searched
     * @param pattern the pattern to search for
     * @return list of starting indices where pattern is found
     * @throws IllegalArgumentException if text or pattern is null
     */
    public static List<Integer> search(String text, String pattern) {
        if (text == null || pattern == null) {
            throw new IllegalArgumentException("Text and pattern must not be null");
        }

        List<Integer> result = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                result.add(i);
            }
        }

        return result;
    }
}
