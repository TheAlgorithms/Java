package com.string;

public class KMPSearch {

    /**
     * isSubstring method finds the first occurrence of pattern in text string
     *
     * @param text    original string
     * @param pattern string to be searched for in the text
     * @return returns the starting index for the first occurrence of pattern in text, if found, else -1
     */
    public static int isSubstring(String text, String pattern) {
        int N = text.length(), M = pattern.length();
        if (M > N) {
            return -1;
        }
        if (M == 0) {
            return 0;
        }
        int[] lps = new int[M];
        computeLPS(pattern, lps);
        for (int i = 0, j = 0; i < N; i++) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                if (j == M) {
                    return i - M + 1;
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                    i--;
                }
            }
        }

        return -1;
    }

    private static void computeLPS(String pattern, int[] lps) {
        int len = 0;
        lps[0] = 0;
        int i = 1, M = pattern.length();
        while (i < M) {
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
    }


}
