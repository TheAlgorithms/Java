package com.thealgorithms.slidingwindow;

import java.util.HashMap;

/**
 * Finds the minimum window substring in 's' that contains all characters of 't'.
 *
 * Worst-case performance O(n)
 * Best-case performance O(n)
 * Average performance O(n)
 * Worst-case space complexity O(1)
 *
 * @author https://github.com/Chiefpatwal
 */
public final class MinimumWindowSubstring {
    // Prevent instantiation
    private MinimumWindowSubstring() {
    }

    /**
     * Finds the minimum window substring of 's' containing all characters of 't'.
     *
     * @param s The input string to search within.
     * @param t The string with required characters.
     * @return The minimum window substring, or empty string if not found.
     */
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        HashMap<Character, Integer> tFreq = new HashMap<>();
        for (char c : t.toCharArray()) {
            tFreq.put(c, tFreq.getOrDefault(c, 0) + 1);
        }

        HashMap<Character, Integer> windowFreq = new HashMap<>();
        int left = 0;
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        int count = 0;
        String result = "";

        while (right < s.length()) {
            char c = s.charAt(right);
            windowFreq.put(c, windowFreq.getOrDefault(c, 0) + 1);

            if (tFreq.containsKey(c) && windowFreq.get(c).intValue() <= tFreq.get(c).intValue()) {
                count++;
            }

            while (count == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    result = s.substring(left, right + 1);
                }

                char leftChar = s.charAt(left);
                windowFreq.put(leftChar, windowFreq.get(leftChar) - 1);
                if (tFreq.containsKey(leftChar) && windowFreq.get(leftChar) < tFreq.get(leftChar)) {
                    count--;
                }
                left++;
            }
            right++;
        }
        return result;
    }
}
