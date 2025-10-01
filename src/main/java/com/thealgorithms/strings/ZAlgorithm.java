package com.thealgorithms.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Z Algorithm for pattern matching in linear time
 *
 * The Z-algorithm computes, for each position i in a string,
 * the length of the longest substring starting at i which is
 * also a prefix of the string.
 *
 * Time Complexity: O(n + m) where n is text length, m is pattern length
 * Space Complexity: O(n + m)
 *
 * @author Your Name
 */
public final class ZAlgorithm {
    private ZAlgorithm() {
    }

    /**
     * Finds all occurrences of pattern in text using Z-algorithm
     *
     * @param text the text to search in
     * @param pattern the pattern to search for
     * @return list of starting indices where pattern occurs
     * @throws IllegalArgumentException if text or pattern is null
     */
    public static List<Integer> search(String text, String pattern) {
        if (text == null || pattern == null) {
            throw new IllegalArgumentException("Text and pattern cannot be null");
        }

        List<Integer> result = new ArrayList<>();

        if (pattern.isEmpty() || pattern.length() > text.length()) {
            return result;
        }

        // Concatenate pattern and text with a separator
        String combined = pattern + "$" + text;
        int[] zArray = computeZArray(combined);

        int patternLength = pattern.length();

        // Find positions where Z-value equals pattern length
        for (int i = patternLength + 1; i < combined.length(); i++) {
            if (zArray[i] == patternLength) {
                result.add(i - patternLength - 1);
            }
        }

        return result;
    }

    /**
     * Computes the Z-array for the given string
     * Z[i] = length of longest substring starting at i which is also a prefix
     *
     * @param str input string
     * @return Z-array
     */
    private static int[] computeZArray(String str) {
        int n = str.length();
        int[] zArray = new int[n];

        int left = 0;
        int right = 0;

        for (int i = 1; i < n; i++) {
            if (i > right) {
                left = right = i;
                while (right < n && str.charAt(right - left) == str.charAt(right)) {
                    right++;
                }
                zArray[i] = right - left;
                right--;
            } else {
                int k = i - left;
                if (zArray[k] < right - i + 1) {
                    zArray[i] = zArray[k];
                } else {
                    left = i;
                    while (right < n && str.charAt(right - left) == str.charAt(right)) {
                        right++;
                    }
                    zArray[i] = right - left;
                    right--;
                }
            }
        }
        return zArray;
    }

    /**
     * Computes only the Z-array for a given string
     * Useful for other applications beyond pattern matching
     *
     * @param str input string
     * @return Z-array where Z[i] is length of longest prefix match at position i
     * @throws IllegalArgumentException if string is null
     */
    public static int[] getZArray(String str) {
        if (str == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        return computeZArray(str);
    }
}
