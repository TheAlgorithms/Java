package com.thealgorithms.compression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RunLengthEncodingTest {

    @Test
    void testNullInputs() {
        // Test that a null input to compress returns an empty string
        assertEquals("", RunLengthEncoding.compress(null));

        // Test that a null input to decompress returns an empty string
        assertEquals("", RunLengthEncoding.decompress(null));
    }

    @Test
    void testCompressionSimple() {
        // Test a typical string with multiple runs
        String input = "AAAABBBCCDAA";
        String expected = "4A3B2C1D2A";
        assertEquals(expected, RunLengthEncoding.compress(input));
    }

    @Test
    void testCompressionWithNoRuns() {
        // Test a string with no consecutive characters
        String input = "ABCDE";
        String expected = "1A1B1C1D1E";
        assertEquals(expected, RunLengthEncoding.compress(input));
    }

    @Test
    void testCompressionEdgeCases() {
        // Test with an empty string
        assertEquals("", RunLengthEncoding.compress(""));

        // Test with a single character
        assertEquals("1A", RunLengthEncoding.compress("A"));

        // Test with a long run of a single character
        assertEquals("10Z", RunLengthEncoding.compress("ZZZZZZZZZZ"));
    }

    @Test
    void testDecompressionSimple() {
        // Test decompression of a typical RLE string
        String input = "4A3B2C1D2A";
        String expected = "AAAABBBCCDAA";
        assertEquals(expected, RunLengthEncoding.decompress(input));
    }

    @Test
    void testDecompressionWithNoRuns() {
        // Test decompression of a string with single characters
        String input = "1A1B1C1D1E";
        String expected = "ABCDE";
        assertEquals(expected, RunLengthEncoding.decompress(input));
    }

    @Test
    void testDecompressionWithMultiDigitCount() {
        // Test decompression where a run count is greater than 9
        String input = "12A1B3C";
        String expected = "AAAAAAAAAAAABCCC";
        assertEquals(expected, RunLengthEncoding.decompress(input));
    }

    @Test
    void testDecompressionEdgeCases() {
        // Test with an empty string
        assertEquals("", RunLengthEncoding.decompress(""));

        // Test with a single character run
        assertEquals("A", RunLengthEncoding.decompress("1A"));
    }

    @Test
    void testSymmetry() {
        // Test that compressing and then decompressing returns the original string
        String original1 = "WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWB";
        String compressed = RunLengthEncoding.compress(original1);
        String decompressed = RunLengthEncoding.decompress(compressed);
        assertEquals(original1, decompressed);

        String original2 = "A";
        assertEquals(original2, RunLengthEncoding.decompress(RunLengthEncoding.compress(original2)));
    }
}
