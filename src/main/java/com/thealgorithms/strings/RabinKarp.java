package com.thealgorithms.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Prateek Kumar Oraon (https://github.com/prateekKrOraon)
 *
 *         An implementation of Rabin-Karp string matching algorithm
 *         Program will simply end if there is no match
 */
public final class RabinKarp {
    private RabinKarp() {
    }

    private static final int ALPHABET_SIZE = 256;

    public static List<Integer> search(String text, String pattern) {
        return search(text, pattern, 101);
    }

    public static List<Integer> search(String text, String pattern, int q) {
        List<Integer> occurrences = new ArrayList<>();
        if (text == null || pattern == null || pattern.isEmpty()) {
            return occurrences;
        }

        int m = pattern.length();
        int n = text.length();
        int t = 0;
        int p = 0;
        int h = 1;
        int j = 0;
        int i = 0;

        if (m > n) {
            return new ArrayList<>();
        }

        // h = pow(ALPHABET_SIZE, m-1) % q
        for (i = 0; i < m - 1; i++) {
            h = h * ALPHABET_SIZE % q;
        }

        for (i = 0; i < m; i++) {
            p = (ALPHABET_SIZE * p + pattern.charAt(i)) % q;
            t = (ALPHABET_SIZE * t + text.charAt(i)) % q;
        }

        for (i = 0; i <= n - m; i++) {
            if (p == t) {
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                }

                if (j == m) {
                    occurrences.add(i);
                }
            }

            if (i < n - m) {
                t = (t - text.charAt(i) * h % q);
                if (t < 0) {
                    t += q;
                }
                t = t * ALPHABET_SIZE % q;
                t = (t + text.charAt(i + m)) % q;
            }
        }
        return occurrences;
    }
}
