package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PatternSearchUsingRabinKarpAlgoTest {

    @Test
    public void testPatternFoundInMiddle() {
        String text = "ABCCDDAEFG";
        String pattern = "CDD";
        List<String> result = PatternSearchUsingRabinKarpAlgo.search(text, pattern);
        assertFalse(result.isEmpty(), "Pattern should be found");
        assertEquals("Start: 3, End: 5, Substring: CDD", result.get(0));
    }

    @Test
    public void testPatternAtStart() {
        String text = "ABCCDDAEFG";
        String pattern = "AB";
        List<String> result = PatternSearchUsingRabinKarpAlgo.search(text, pattern);
        assertFalse(result.isEmpty(), "Pattern should be found");
        assertEquals("Start: 0, End: 1, Substring: AB", result.get(0));
    }

    @Test
    public void testPatternAtEnd() {
        String text = "ABCCDDAEFG";
        String pattern = "EFG";
        List<String> result = PatternSearchUsingRabinKarpAlgo.search(text, pattern);
        assertFalse(result.isEmpty(), "Pattern should be found");
        assertEquals("Start: 7, End: 9, Substring: EFG", result.get(0));
    }

    @Test
    public void testPatternNotFound() {
        String text = "ABCCDDAEFG";
        String pattern = "XYZ";
        List<String> result = PatternSearchUsingRabinKarpAlgo.search(text, pattern);
        assertTrue(result.isEmpty(), "Pattern should not be found");
    }

    @Test
    public void testPatternEqualsText() {
        String text = "ABCCDDAEFG";
        String pattern = "ABCCDDAEFG";
        List<String> result = PatternSearchUsingRabinKarpAlgo.search(text, pattern);
        assertFalse(result.isEmpty(), "Pattern should match entire text");
        assertEquals("Start: 0, End: 9, Substring: ABCCDDAEFG", result.get(0));
    }

    @Test
    public void testMultipleMatches() {
        String text = "AAAAAA";
        String pattern = "AA";
        List<String> result = PatternSearchUsingRabinKarpAlgo.search(text, pattern);
        assertEquals(5, result.size(), "Pattern should appear multiple times");
    }

    @Test
    public void testCaseInsensitiveSearch() {
        String text = "HelloWorld";
        String pattern = "helloworld";
        List<String> result = PatternSearchUsingRabinKarpAlgo.search(text.toLowerCase(), pattern.toLowerCase());
        assertFalse(result.isEmpty(), "Pattern should match regardless of case");
        assertEquals("Start: 0, End: 9, Substring: helloworld", result.get(0));
    }
}
