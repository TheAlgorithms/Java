package com.thealgorithms.others;

/**
 * Implementation of Knuth–Morris–Pratt algorithm Usage: see the main function
 * for an example
 */
public final class KMP {
    private KMP() {
    }

    // a working example

    public static void main(String[] args) {
        final String haystack = "AAAAABAAABA"; // This is the full string
        final String needle = "AAAA"; // This is the substring that we want to find
        kmpMatcher(haystack, needle);
    }

    // find the starting index in string haystack[] that matches the search word P[]
    public static void kmpMatcher(final String haystack, final String needle) {
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
                System.out.println("Pattern starts: " + (i + 1 - n));
                q = pi[q - 1];
            }
        }
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
