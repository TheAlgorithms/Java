package com.thealgorithms.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Z-algorithm implementation to find all occurrences of a pattern in a text.
 */
public final class Zalgorithm {
    private Zalgorithm() {
    }

    /**
     * Finds occurrences of a pattern in a text using Z-algorithm.
     *
     * @param text    the input text in which we are searching the pattern
     * @param pattern the pattern to search for
     * @return a list of indices where the pattern occurs in the text
     */
    public static List<Integer> findPatternOccurrences(String text, String pattern) {
        String combined = pattern + "$" + text;
        int[] zArray = calculateZ(combined);
        List<Integer> occurrences = new ArrayList<>();
        int patternLength = pattern.length();

        for (int i = patternLength + 1; i < zArray.length; i++) {
            if (zArray[i] == patternLength) {
                occurrences.add(i - patternLength - 1);
            }
        }
        return occurrences;
    }

    /**
     * Helper method to calculate Z-array for a given string.
     *
     * @param s the input string
     * @return the Z-array where Z[i] is the length of the longest substring
     * starting from i that is also a prefix of s
     */
    private static int[] calculateZ(String s) {
        int n = s.length();
        int[] z = new int[n];
        int l = 0, r = 0;

        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }
}
