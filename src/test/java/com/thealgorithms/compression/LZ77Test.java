package com.thealgorithms.compression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LZ77Test {

    @Test
    @DisplayName("Test compression and decompression of a simple repeating string")
    void testSimpleRepeatingString() {
        String original = "ababcbababaa";
        List<LZ77.Token> compressed = LZ77.compress(original, 10, 4);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test compression and decompression of a string with no repeats initially")
    void testNoInitialRepeats() {
        String original = "abcdefgh";
        List<LZ77.Token> compressed = LZ77.compress(original);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test compression and decompression of a longer example")
    void testLongerExample() {
        String original = "TOBEORNOTTOBEORTOBEORNOT";
        List<LZ77.Token> compressed = LZ77.compress(original, 20, 10);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test empty string compression and decompression")
    void testEmptyString() {
        String original = "";
        List<LZ77.Token> compressed = LZ77.compress(original);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
        assertTrue(compressed.isEmpty());
    }

    @Test
    @DisplayName("Test null string compression")
    void testNullStringCompress() {
        List<LZ77.Token> compressed = LZ77.compress(null);
        assertTrue(compressed.isEmpty());
    }

    @Test
    @DisplayName("Test null list decompression")
    void testNullListDecompress() {
        String decompressed = LZ77.decompress(null);
        assertEquals("", decompressed);
    }

    @Test
    @DisplayName("Test invalid buffer sizes throw exception")
    void testInvalidBufferSizes() {
        assertThrows(IllegalArgumentException.class, () -> LZ77.compress("test", 0, 5));
        assertThrows(IllegalArgumentException.class, () -> LZ77.compress("test", 5, 0));
        assertThrows(IllegalArgumentException.class, () -> LZ77.compress("test", -1, 5));
        assertThrows(IllegalArgumentException.class, () -> LZ77.compress("test", 5, -1));
    }

    @Test
    @DisplayName("Test string with all same characters")
    void testAllSameCharacters() {
        String original = "AAAAAA";
        List<LZ77.Token> compressed = LZ77.compress(original, 10, 5);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);

        // Should achieve good compression for repeated characters
        assertTrue(compressed.size() < original.length());
    }

    @Test
    @DisplayName("Test string with all unique characters")
    void testAllUniqueCharacters() {
        String original = "abcdefghijklmnop";
        List<LZ77.Token> compressed = LZ77.compress(original, 10, 5);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);

        // No compression expected for unique characters
        assertEquals(original.length(), compressed.size());
    }

    @Test
    @DisplayName("Test single character string")
    void testSingleCharacter() {
        String original = "a";
        List<LZ77.Token> compressed = LZ77.compress(original);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
        assertEquals(1, compressed.size());
    }

    @Test
    @DisplayName("Test match that goes exactly to the end")
    void testMatchToEnd() {
        String original = "abcabc";
        List<LZ77.Token> compressed = LZ77.compress(original, 10, 10);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test with very small window size")
    void testSmallWindowSize() {
        String original = "ababababab";
        List<LZ77.Token> compressed = LZ77.compress(original, 2, 4);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test with very small lookahead buffer")
    void testSmallLookaheadBuffer() {
        String original = "ababcbababaa";
        List<LZ77.Token> compressed = LZ77.compress(original, 10, 2);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test repeating pattern at the end")
    void testRepeatingPatternAtEnd() {
        String original = "xyzabcabcabcabc";
        List<LZ77.Token> compressed = LZ77.compress(original, 15, 8);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test overlapping matches (run-length encoding case)")
    void testOverlappingMatches() {
        String original = "aaaaaa";
        List<LZ77.Token> compressed = LZ77.compress(original, 10, 10);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test complex pattern with multiple repeats")
    void testComplexPattern() {
        String original = "abcabcabcxyzxyzxyz";
        List<LZ77.Token> compressed = LZ77.compress(original, 20, 10);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test with special characters")
    void testSpecialCharacters() {
        String original = "hello world! @#$%^&*()";
        List<LZ77.Token> compressed = LZ77.compress(original);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test with numbers")
    void testWithNumbers() {
        String original = "1234567890123456";
        List<LZ77.Token> compressed = LZ77.compress(original, 15, 8);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test long repeating sequence")
    void testLongRepeatingSequence() {
        String original = "abcdefgh".repeat(10);
        List<LZ77.Token> compressed = LZ77.compress(original, 50, 20);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);

        // Should achieve significant compression
        assertTrue(compressed.size() < original.length() / 2);
    }

    @Test
    @DisplayName("Test compression effectiveness")
    void testCompressionEffectiveness() {
        String original = "ababababababab";
        List<LZ77.Token> compressed = LZ77.compress(original, 20, 10);

        // Verify that compression actually reduces the data size
        // Each token represents potentially multiple characters
        assertTrue(compressed.size() <= original.length());

        // Verify decompression
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test with mixed case letters")
    void testMixedCase() {
        String original = "AaBbCcAaBbCc";
        List<LZ77.Token> compressed = LZ77.compress(original);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test default parameters")
    void testDefaultParameters() {
        String original = "This is a test string with some repeated patterns. This is repeated.";
        List<LZ77.Token> compressed = LZ77.compress(original);
        String decompressed = LZ77.decompress(compressed);
        assertEquals(original, decompressed);
    }
}
