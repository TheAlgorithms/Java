package com.thealgorithms.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for finding the length of the longest substring without repeating characters.
 *
 * Uses the sliding window technique with a HashMap to track
 * the last seen index of each character.
 */
final class LongestNonRepetitiveSubstring {

    private LongestNonRepetitiveSubstring() {
    }

    /**
     * Finds the length of the longest substring without repeating characters.
     *
     * Uses the sliding window approach to maintain a window of unique characters.
     * When a duplicate character is found within the current window,
     * the starting index is updated accordingly.
     *
     * Time Complexity: O(n), where n is the length of the input string.
     * Space Complexity: O(min(n, m)), where m is the size of the character set.
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

            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= start) {
                start = charIndexMap.get(currentChar) + 1;
            }

            charIndexMap.put(currentChar, i);
            maxLength = Math.max(maxLength, i - start + 1);
        }

        return maxLength;
    }
}
