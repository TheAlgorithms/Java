package com.thealgorithms.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Knuth–Morris–Pratt algorithm Usage: see the main function
 * for an example
 */
public final class KMP {
    private KMP() {
    }

    /**
     * find the starting index in string haystack[] that matches the search word P[]
     *
     * @param haystack The text to be searched
     * @param needle   The pattern to be searched for
     * @return A list of starting indices where the pattern is found
     */
    public static List<Integer> kmpMatcher(final String haystack, final String needle) {
        List<Integer> occurrences = new ArrayList<>();
        if (haystack == null || needle == null || needle.isEmpty()) {
            return occurrences;
        }

        final int m = haystack.length();
        final int n = needle.length();
        final int[] pi = computePrefixFunction(needle);
        int q = 0;
        for (int i = 0; i < m; i++) {
            while (q > 0 && haystack.charAt(i) != needle.charAt(q)) {
                q = pi[q - 1];
            }

            if (haystack.charAt(i) == needle.charAt(q)) {
                q++;
            }

            if (q == n) {
                occurrences.add(i + 1 - n);
                q = pi[q - 1];
            }
        }
        return occurrences;
    }

    // return the prefix function
    private static int[] computePrefixFunction(final String p) {
        final int n = p.length();
        final int[] pi = new int[n];
        pi[0] = 0;
        int q = 0;
        for (int i = 1; i < n; i++) {
            while (q > 0 && p.charAt(q) != p.charAt(i)) {
                q = pi[q - 1];
            }

            if (p.charAt(q) == p.charAt(i)) {
                q++;
            }

            pi[i] = q;
        }
        return pi;
    }
}
