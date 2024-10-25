package com.thealgorithms.slidingwindow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
