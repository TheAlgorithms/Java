package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the LongestSubstringWithoutRepeatingCharacters class.
 *
 * @author  (https://github.com/Chiefpatwal)
 */
public class LongestSubstringWithoutRepeatingCharactersTest {

    @Test
    public void testLengthOfLongestSubstring() {
        // Test cases for the lengthOfLongestSubstring method
        assertEquals(3, LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("pwwkew"));
        assertEquals(0, LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(""));
        assertEquals(5, LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("abcde"));
    }
}
