package com.thealgorithms.slidingwindow;
import java.util.HashSet;

/**
 * The Longest Substring Without Repeating Characters algorithm finds the length of
 * the longest substring without repeating characters in a given string.
 *
 * <p>
 * Worst-case performance O(n)
 * Best-case performance O(n)
 * Average performance O(n)
 * Worst-case space complexity O(min(n, m)), where n is the length of the string
 * and m is the size of the character set.
 *
 * @author  (https://github.com/Chiefpatwal)
 */
public final class LongestSubstringWithoutRepeatingCharacters {

    // Prevent instantiation
    private LongestSubstringWithoutRepeatingCharacters() {
    }

    /**
     * This method finds the length of the longest substring without repeating characters.
     *
     * @param s is the input string
     * @return the length of the longest substring without repeating characters
     */
    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int left = 0;
        HashSet<Character> charSet = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {
            // If the character is already in the set, remove characters from the left
            while (charSet.contains(s.charAt(right))) {
                charSet.remove(s.charAt(left));
                left++;
            }
            charSet.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
