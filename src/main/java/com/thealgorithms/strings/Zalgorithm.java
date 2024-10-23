package com.thealgorithms.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to implement the Z Algorithm for pattern matching in strings.
 * The Z Algorithm finds all occurrences of a pattern within a text.
 */
public final class Zalgorithm {
    private Zalgorithm() {
    }

    /**
     * Calculates the Z array for a given string.
     *
     * @param s the input string
     * @return the Z array where Z[i] indicates the length of the longest substring
     *         starting from s[i] that is also a prefix of s
     */
    public static int[] calculateZ(String s) {
        int n = s.length();
        int[] Z = new int[n];
        int left = 0, right = 0;

        for (int i = 1; i < n; i++) {
            if (i <= right) {
                Z[i] = Math.min(right - i + 1, Z[i - left]);
            }
            while (i + Z[i] < n && s.charAt(Z[i]) == s.charAt(i + Z[i])) {
                Z[i]++;
            }
            if (i + Z[i] - 1 > right) {
                left = i;
                right = i + Z[i] - 1;
            }
        }
        return Z;
    }

    /**
     * Finds all occurrences of a pattern in the text using the Z Algorithm.
     *
     * @param text the text in which to search for the pattern
     * @param pattern the pattern to search for
     * @return a list of starting indices of occurrences of the pattern in the text
     */
    public static List<Integer> findPatternOccurrences(String text, String pattern) {
        String combined = pattern + "$" + text;
        int[] Z = calculateZ(combined);
        List<Integer> occurrences = new ArrayList<>();
        int patternLength = pattern.length();

        for (int i = 0; i < Z.length; i++) {
            if (Z[i] == patternLength) {
                occurrences.add(i - patternLength - 1);
            }
        }
        return occurrences;
    }
}
