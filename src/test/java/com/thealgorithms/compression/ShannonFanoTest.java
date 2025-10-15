package com.thealgorithms.compression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.Test;

class ShannonFanoTest {

    @Test
    void testNullInput() {
        // Test with a null string, should return an empty map
        assertTrue(ShannonFano.generateCodes(null).isEmpty());
    }

    @Test
    void testSimpleString() {
        // A simple string to test basic code generation
        String text = "AAABBC";
        Map<Character, String> codes = ShannonFano.generateCodes(text);

        assertEquals(3, codes.size());
        assertEquals("0", codes.get('A'));
        assertEquals("10", codes.get('B'));
        assertEquals("11", codes.get('C'));
    }

    @Test
    void testExampleFromStringIssue() {
        // Example from the original issue proposal: A:15, B:7, C:6, D:6, E:5
        // The code finds a more optimal split: {A,B} | {C,D,E} -> |22-17|=5
        // instead of {A} | {B,C,D,E} -> |15-24|=9.
        String text = "AAAAAAAAAAAAAAABBBBBBBCCCCCCDDDDDDEEEEE";
        Map<Character, String> codes = ShannonFano.generateCodes(text);

        assertEquals(5, codes.size());
        assertEquals("00", codes.get('A'));
        assertEquals("01", codes.get('B'));
        assertEquals("10", codes.get('C'));
        assertEquals("110", codes.get('D'));
        assertEquals("111", codes.get('E'));
    }

    @Test
    void testEdgeCases() {
        // Test with an empty string
        assertTrue(ShannonFano.generateCodes("").isEmpty());

        // Test with a single character
        Map<Character, String> singleCharCodes = ShannonFano.generateCodes("AAAAA");
        assertEquals(1, singleCharCodes.size());
        assertEquals("0", singleCharCodes.get('A')); // A single symbol gets code "0"

        // Test with all unique characters
        String uniqueCharsText = "ABCDEF";
        Map<Character, String> uniqueCharCodes = ShannonFano.generateCodes(uniqueCharsText);
        assertEquals(6, uniqueCharCodes.size());
        // Check that codes are unique and have varying lengths as expected
        assertEquals(6, uniqueCharCodes.values().stream().distinct().count());
    }

    @Test
    void testStringWithTwoChars() {
        String text = "ABABAB";
        Map<Character, String> codes = ShannonFano.generateCodes(text);

        assertEquals(2, codes.size());
        assertTrue(codes.get('A').equals("0") && codes.get('B').equals("1") || codes.get('A').equals("1") && codes.get('B').equals("0"));
    }
}
