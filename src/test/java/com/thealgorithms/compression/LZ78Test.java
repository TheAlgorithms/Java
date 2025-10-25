package com.thealgorithms.compression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LZ78Test {

    @Test
    @DisplayName("Test compression and decompression of a simple repeating string")
    void testSimpleRepeatingString() {
        String original = "ababcbababaa";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test compression and decompression example ABAABABAABAB")
    void testStandardExample() {
        String original = "ABAABABAABAB";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);

        // Verify the compression produces expected tokens
        // Expected: (0,A)(0,B)(1,A)(2,B)(3,A)(4,B)
        // Where dictionary builds as: 1:A, 2:B, 3:AA, 4:BA, 5:ABA, 6:BAB
        assertEquals(6, compressed.size());
        assertEquals(0, compressed.get(0).index());
        assertEquals('A', compressed.get(0).nextChar());
        assertEquals(0, compressed.get(1).index());
        assertEquals('B', compressed.get(1).nextChar());
        assertEquals(1, compressed.get(2).index());
        assertEquals('A', compressed.get(2).nextChar());
    }

    @Test
    @DisplayName("Test compression and decompression of a longer example")
    void testLongerExample() {
        String original = "TOBEORNOTTOBEORTOBEORNOT";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test empty string compression and decompression")
    void testEmptyString() {
        String original = "";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);
        assertTrue(compressed.isEmpty());
    }

    @Test
    @DisplayName("Test null string compression")
    void testNullStringCompress() {
        List<LZ78.Token> compressed = LZ78.compress(null);
        assertTrue(compressed.isEmpty());
    }

    @Test
    @DisplayName("Test null list decompression")
    void testNullListDecompress() {
        String decompressed = LZ78.decompress(null);
        assertEquals("", decompressed);
    }

    @Test
    @DisplayName("Test string with all same characters")
    void testAllSameCharacters() {
        String original = "AAAAAA";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);

        // Should achieve good compression: (0,A)(1,A)(2,A)...
        assertTrue(compressed.size() <= 4); // Builds: A, AA, AAA, etc.
    }

    @Test
    @DisplayName("Test string with all unique characters")
    void testAllUniqueCharacters() {
        String original = "abcdefg";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);

        // No compression for unique characters
        assertEquals(original.length(), compressed.size());

        // Each token should have index 0 (empty prefix)
        for (LZ78.Token token : compressed) {
            assertEquals(0, token.index());
        }
    }

    @Test
    @DisplayName("Test single character string")
    void testSingleCharacter() {
        String original = "a";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);
        assertEquals(1, compressed.size());
        assertEquals(0, compressed.getFirst().index());
        assertEquals('a', compressed.getFirst().nextChar());
    }

    @Test
    @DisplayName("Test two character string")
    void testTwoCharacters() {
        String original = "ab";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);
        assertEquals(2, compressed.size());
    }

    @Test
    @DisplayName("Test repeating pairs")
    void testRepeatingPairs() {
        String original = "ababab";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);

        // Should compress well: (0,a)(0,b)(1,b) or similar
        assertTrue(compressed.size() < original.length());
    }

    @Test
    @DisplayName("Test growing patterns")
    void testGrowingPatterns() {
        String original = "abcabcdabcde";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test dictionary building correctness")
    void testDictionaryBuilding() {
        String original = "aabaabaab";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);

        // Verify first few tokens
        // Expected pattern: (0,a)(1,b)(2,a)(3,b) building dictionary 1:a, 2:ab, 3:aa, 4:aab
        assertTrue(compressed.size() > 0);
        assertEquals(0, compressed.getFirst().index()); // First char always has index 0
    }

    @Test
    @DisplayName("Test with special characters")
    void testSpecialCharacters() {
        String original = "hello world! hello!";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test with numbers")
    void testWithNumbers() {
        String original = "1234512345";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);

        // Should achieve compression
        assertTrue(compressed.size() < original.length());
    }

    @Test
    @DisplayName("Test long repeating sequence")
    void testLongRepeatingSequence() {
        String original = "abcdefgh".repeat(5);
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);

        // LZ78 should achieve some compression for repeating sequences
        assertTrue(compressed.size() < original.length(), "Compressed size should be less than original length");
    }

    @Test
    @DisplayName("Test alternating characters")
    void testAlternatingCharacters() {
        String original = "ababababab";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test compression effectiveness")
    void testCompressionEffectiveness() {
        String original = "the quick brown fox jumps over the lazy dog the quick brown fox";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);

        // Should achieve some compression due to repeated phrases
        assertTrue(compressed.size() < original.length());
    }

    @Test
    @DisplayName("Test with mixed case letters")
    void testMixedCase() {
        String original = "AaBbCcAaBbCc";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test palindrome string")
    void testPalindrome() {
        String original = "abccba";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test highly compressible pattern")
    void testHighlyCompressible() {
        String original = "aaaaaaaaaa";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);

        // Should achieve excellent compression ratio
        assertTrue(compressed.size() <= 4);
    }

    @Test
    @DisplayName("Test empty list decompression")
    void testEmptyListDecompress() {
        List<LZ78.Token> compressed = List.of();
        String decompressed = LZ78.decompress(compressed);
        assertEquals("", decompressed);
    }

    @Test
    @DisplayName("Test binary-like pattern")
    void testBinaryPattern() {
        String original = "0101010101";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test nested patterns")
    void testNestedPatterns() {
        String original = "abcabcdefabcdefghi";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test whitespace handling")
    void testWhitespace() {
        String original = "a b c a b c";
        List<LZ78.Token> compressed = LZ78.compress(original);
        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    @DisplayName("Test token structure correctness")
    void testTokenStructure() {
        String original = "abc";
        List<LZ78.Token> compressed = LZ78.compress(original);

        // All tokens should have valid indices (>= 0)
        for (LZ78.Token token : compressed) {
            assertTrue(token.index() >= 0);
            assertNotNull(token.nextChar());
        }

        String decompressed = LZ78.decompress(compressed);
        assertEquals(original, decompressed);
    }
}
