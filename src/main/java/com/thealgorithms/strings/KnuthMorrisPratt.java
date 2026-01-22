package com.thealgorithms.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Knuth–Morris–Pratt (KMP) string matching algorithm.
 * KMP efficiently searches for occurrences of a pattern within a text by
 * utilizing a pre-computed failure function to avoid redundant comparisons.
 * Time Complexity: O(n + m) where n is text length and m is pattern length.
 */
final class KnuthMorrisPratt {
    private KnuthMorrisPratt() {
    }

    /**
     * Compute the Longest Proper Prefix which is also Suffix (LPS) array
     * for the given pattern. This array is used to avoid unnecessary
     * character comparisons during the search phase.
     *
     * @param pattern the pattern to compute LPS for
     * @return the LPS array
     */
    public static int[] computeLps(final String pattern) {
        final int n = pattern.length();
        final int[] lps = new int[n];
        int len = 0;
        lps[0] = 0;
        for (int i = 1; i < n; ) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    /**
     * Search for all occurrences of the pattern in the text.
     * Returns a list of starting indices where the pattern is found.
     *
     * @param text the text to search in
     * @param pattern the pattern to search for
     * @return list of starting indices of pattern occurrences
     */
    public static List<Integer> search(final String text, final String pattern) {
        final List<Integer> occurrences = new ArrayList<>();
        if (pattern == null || pattern.isEmpty() || text == null) {
            return occurrences;
        }

        final int[] lps = computeLps(pattern);
        int i = 0;
        int j = 0;
        final int n = text.length();
        final int m = pattern.length();
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    occurrences.add(i - j);
                    j = lps[j - 1];
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return occurrences;
    }

    /**
     * Main method demonstrating the KMP algorithm with an example.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        final String text = "AAAAABAAABA";
        final String pattern = "AAAA";
        final List<Integer> idx = search(text, pattern);
        for (int pos : idx) {
            System.out.println("Pattern found at index: " + pos);
        }
    }
}
