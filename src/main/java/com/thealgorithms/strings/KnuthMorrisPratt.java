package com.thealgorithms.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Knuth-Morris-Pratt (KMP) algorithm for substring search.
 * This algorithm searches for occurrences of a "pattern" within a main "text" string
 * by employing the observation that when a mismatch occurs, the pattern itself
 * embodies sufficient information to determine where the next match could begin,
 * thus bypassing re-examination of previously matched characters.
 * <p>
 * Time Complexity: O(n + m), where n is the length of the text and m is the length of the pattern.
 * Space Complexity: O(m) for the longest prefix-suffix (LPS) array.
 * <p>
 * Wikipedia: https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm
 *
 * @author Milad Sadeghi
 */
public final class KnuthMorrisPratt {

    // Private constructor to prevent instantiation
    private KnuthMorrisPratt() {
    }

    /**
     * Searches for occurrences of a pattern within a text using the Knuth-Morris-Pratt algorithm.
     *
     * @param text    the text to search within
     * @param pattern the pattern to search for
     * @return a list of starting indices where the pattern is found in the text
     */
    public static List<Integer> search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] lps = computeLPSArray(pattern);
        int i = 0; // index for sample
        int j = 0; // index for pattern
        List<Integer> result = new ArrayList<>();

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            if (j == m) {
                System.out.println("Found pattern at index " + (i - j));
                result.add(i - j);
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return result;
    }

    /**
     * Preprocesses the pattern to create the longest prefix-suffix (LPS) array.
     *
     * @param pattern the pattern to preprocess
     * @return the LPS array
     */
    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int j = 0;
        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                lps[i] = j;
                i++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
