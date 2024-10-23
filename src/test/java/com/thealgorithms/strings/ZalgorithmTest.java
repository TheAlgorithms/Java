package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ZalgorithmTest {
    /**
     * Test 1:
     * Input: text = "ababcababcabc", pattern = "abc"
     * Output: [2, 7, 10]
     * Explanation: The pattern "abc" occurs at indices 2, 7, and 10.
     */
    @Test
    public void testFindPatternOccurrences() {
        String text = "ababcababcabc";
        String pattern = "abc";
        List<Integer> expected = Arrays.asList(2, 7, 10);
        
        List<Integer> actual = Zalgorithm.findPatternOccurrences(text, pattern);
        
        assertEquals(expected, actual);
    }

    /**
     * Test 2:
     * Input: text = "abcdefg", pattern = "xyz"
     * Output: []
     * Explanation: The pattern "xyz" does not occur in the text.
     */
    @Test
    public void testFindPatternOccurrencesNoMatch() {
        String text = "abcdefg";
        String pattern = "xyz";
        List<Integer> expected = Arrays.asList();
        
        List<Integer> actual = Zalgorithm.findPatternOccurrences(text, pattern);
        
        assertEquals(expected, actual);
    }

    /**
     * Test 3:
     * Input: text = "aabbaabbaaa", pattern = "a"
     * Output: [0, 3, 5, 9]
     * Explanation: The pattern "a" occurs at indices 0, 3, 5, and 9.
     */
    @Test
    public void testFindPatternOccurrencesSingleCharacter() {
        String text = "aabbaabbaaa";
        String pattern = "a";
        List<Integer> expected = Arrays.asList(0, 3, 5, 9);
        
        List<Integer> actual = Zalgorithm.findPatternOccurrences(text, pattern);
        
        assertEquals(expected, actual);
    }
}
