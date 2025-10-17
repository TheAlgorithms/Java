package com.thealgorithms.strings;

import java.util.Arrays;

/**
 * Minimum Window Substring
 *
 * Given two strings s and t, return the minimum window substring of s such that
 * every character in t (including duplicates) is included in the window.
 *
 * If there is no such substring, return an empty string "".
 *
 * Approach: sliding window + frequency table.
 *
 * Time complexity: O(n + m) where n = s.length(), m = t.length()
 * Space complexity: O(1) if using fixed-size arrays for ASCII (256).
 */
public final class MinimumWindowSubstring {

    private MinimumWindowSubstring() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Returns the minimum window substring of s that contains all chars from t.
     *
     * @param s source string
     * @param t target string
     * @return shortest substring of s containing all chars of t, or "" if none
     */
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length() || t.length() == 0) {
            return "";
        }

        int[] need = new int[256];
        for (char c : t.toCharArray()) {
            need[c]++;
        }

        int required = 0;
        for (int i = 0; i < 256; i++) {
            if (need[i] > 0) required++;
        }

        int l = 0, r = 0;
        int formed = 0;
        int[] window = new int[256];
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;

        while (r < s.length()) {
            char c = s.charAt(r);
            window[c]++;
            if (need[c] > 0 && window[c] == need[c]) {
                formed++;
            }

            // Try and contract the window till the point it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                // Update result
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    minLeft = l;
                }

                char d = s.charAt(l);
                window[d]--;
                if (need[d] > 0 && window[d] < need[d]) {
                    formed--;
                }
                l++;
            }

            r++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
}
