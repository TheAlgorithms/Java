package com.thealgorithms.strings;

/**
 * Minimum Window Substring
 *
 * Given two strings s and t, return the minimum window substring of s such that
 * every character in t (including duplicates) is included in the window.
 *
 * If there is no such substring, return an empty string "".
 *
 * Approach: Sliding Window + Frequency Table.
 *
 * Time complexity: O(n + m)
 * Space complexity: O(1) for ASCII characters.
 *
 * Reference: https://en.wikipedia.org/wiki/Minimum_window_substring
 */
public final class MinimumWindowSubstring {

    private MinimumWindowSubstring() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length() || t.isEmpty()) {
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

        int l = 0, r = 0, formed = 0;
        int[] window = new int[256];
        int minLen = Integer.MAX_VALUE, minLeft = 0;

        while (r < s.length()) {
            char c = s.charAt(r);
            window[c]++;
            if (need[c] > 0 && window[c] == need[c]) formed++;

            while (l <= r && formed == required) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    minLeft = l;
                }

                char d = s.charAt(l);
                window[d]--;
                if (need[d] > 0 && window[d] < need[d]) formed--;
                l++;
            }
            r++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
}
