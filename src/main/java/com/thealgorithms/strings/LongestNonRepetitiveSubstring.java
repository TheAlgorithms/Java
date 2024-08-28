package com.thealgorithms.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for finding the length of the longest substring without repeating characters.
 */
final class LongestNonRepetitiveSubstring {
    private LongestNonRepetitiveSubstring() {
    }

    /**
     * Finds the length of the longest substring without repeating characters.
     *
     * @param s the input string
     * @return the length of the longest non-repetitive substring
     */
    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int start = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            // If the character is already in the map and its index is within the current window
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= start) {
                // Move the start to the position right after the last occurrence of the current character
                start = charIndexMap.get(currentChar) + 1;
            }

            // Update the last seen index of the current character
            charIndexMap.put(currentChar, i);

            // Calculate the maximum length of the substring without repeating characters
            maxLength = Math.max(maxLength, i - start + 1);
        }

        return maxLength;
    }
}
