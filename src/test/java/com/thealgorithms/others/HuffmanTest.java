package com.thealgorithms.others;

import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for Huffman coding algorithm.
 * Tests various scenarios including normal cases, edge cases, and error
 * conditions.
 */
class HuffmanTest {

    @Test
    void testBuildHuffmanTreeWithBasicInput() {
        char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] charFreq = {5, 9, 12, 13, 16, 45};

        HuffmanNode root = Huffman.buildHuffmanTree(charArray, charFreq);

        Assertions.assertNotNull(root);
        Assertions.assertEquals(100, root.data); // Total frequency
    }

    @Test
    void testGenerateCodesWithBasicInput() {
        char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] charFreq = {5, 9, 12, 13, 16, 45};

        HuffmanNode root = Huffman.buildHuffmanTree(charArray, charFreq);
        Map<Character, String> codes = Huffman.generateCodes(root);

        Assertions.assertNotNull(codes);
        Assertions.assertEquals(6, codes.size());

        // Verify that all characters have codes
        for (char c : charArray) {
            Assertions.assertTrue(codes.containsKey(c), "Missing code for character: " + c);
            Assertions.assertNotNull(codes.get(c), "Null code for character: " + c);
        }

        // Verify that higher frequency characters have shorter codes
        // 'f' has the highest frequency (45), so it should have one of the shortest
        // codes
        Assertions.assertTrue(codes.get('f').length() <= codes.get('a').length());
    }

    @Test
    void testSingleCharacter() {
        char[] charArray = {'a'};
        int[] charFreq = {10};

        HuffmanNode root = Huffman.buildHuffmanTree(charArray, charFreq);
        Map<Character, String> codes = Huffman.generateCodes(root);

        Assertions.assertNotNull(codes);
        Assertions.assertEquals(1, codes.size());
        Assertions.assertEquals("0", codes.get('a')); // Single character gets code "0"
    }

    @Test
    void testTwoCharacters() {
        char[] charArray = {'a', 'b'};
        int[] charFreq = {3, 7};

        HuffmanNode root = Huffman.buildHuffmanTree(charArray, charFreq);
        Map<Character, String> codes = Huffman.generateCodes(root);

        Assertions.assertNotNull(codes);
        Assertions.assertEquals(2, codes.size());

        // Verify both characters have codes
        Assertions.assertTrue(codes.containsKey('a'));
        Assertions.assertTrue(codes.containsKey('b'));

        // Verify codes are different
        Assertions.assertNotEquals(codes.get('a'), codes.get('b'));
    }

    @Test
    void testEqualFrequencies() {
        char[] charArray = {'a', 'b', 'c'};
        int[] charFreq = {5, 5, 5};

        HuffmanNode root = Huffman.buildHuffmanTree(charArray, charFreq);
        Map<Character, String> codes = Huffman.generateCodes(root);

        Assertions.assertNotNull(codes);
        Assertions.assertEquals(3, codes.size());

        // Verify all characters have codes
        for (char c : charArray) {
            Assertions.assertTrue(codes.containsKey(c));
        }
    }

    @Test
    void testLargeFrequencyDifference() {
        char[] charArray = {'a', 'b', 'c'};
        int[] charFreq = {1, 10, 100};

        HuffmanNode root = Huffman.buildHuffmanTree(charArray, charFreq);
        Map<Character, String> codes = Huffman.generateCodes(root);

        Assertions.assertNotNull(codes);
        Assertions.assertEquals(3, codes.size());

        // Character 'c' with highest frequency should have shortest code
        Assertions.assertTrue(codes.get('c').length() <= codes.get('b').length());
        Assertions.assertTrue(codes.get('c').length() <= codes.get('a').length());
    }

    @Test
    void testNullCharacterArray() {
        int[] charFreq = {5, 9, 12};

        Assertions.assertThrows(IllegalArgumentException.class, () -> { Huffman.buildHuffmanTree(null, charFreq); });
    }

    @Test
    void testNullFrequencyArray() {
        char[] charArray = {'a', 'b', 'c'};

        Assertions.assertThrows(IllegalArgumentException.class, () -> { Huffman.buildHuffmanTree(charArray, null); });
    }

    @Test
    void testEmptyArrays() {
        char[] charArray = {};
        int[] charFreq = {};

        Assertions.assertThrows(IllegalArgumentException.class, () -> { Huffman.buildHuffmanTree(charArray, charFreq); });
    }

    @Test
    void testMismatchedArrayLengths() {
        char[] charArray = {'a', 'b', 'c'};
        int[] charFreq = {5, 9};

        Assertions.assertThrows(IllegalArgumentException.class, () -> { Huffman.buildHuffmanTree(charArray, charFreq); });
    }

    @Test
    void testNegativeFrequency() {
        char[] charArray = {'a', 'b', 'c'};
        int[] charFreq = {5, -9, 12};

        Assertions.assertThrows(IllegalArgumentException.class, () -> { Huffman.buildHuffmanTree(charArray, charFreq); });
    }

    @Test
    void testZeroFrequency() {
        char[] charArray = {'a', 'b', 'c'};
        int[] charFreq = {0, 5, 10};

        HuffmanNode root = Huffman.buildHuffmanTree(charArray, charFreq);
        Map<Character, String> codes = Huffman.generateCodes(root);

        Assertions.assertNotNull(codes);
        Assertions.assertEquals(3, codes.size());
        Assertions.assertTrue(codes.containsKey('a')); // Even with 0 frequency, character should have a code
    }

    @Test
    void testGenerateCodesWithNullRoot() {
        Map<Character, String> codes = Huffman.generateCodes(null);

        Assertions.assertNotNull(codes);
        Assertions.assertTrue(codes.isEmpty());
    }

    @Test
    void testPrefixProperty() {
        // Verify that no code is a prefix of another (Huffman property)
        char[] charArray = {'a', 'b', 'c', 'd', 'e'};
        int[] charFreq = {5, 9, 12, 13, 16};

        HuffmanNode root = Huffman.buildHuffmanTree(charArray, charFreq);
        Map<Character, String> codes = Huffman.generateCodes(root);

        // Check that no code is a prefix of another
        for (Map.Entry<Character, String> entry1 : codes.entrySet()) {
            for (Map.Entry<Character, String> entry2 : codes.entrySet()) {
                if (!entry1.getKey().equals(entry2.getKey())) {
                    String code1 = entry1.getValue();
                    String code2 = entry2.getValue();
                    Assertions.assertTrue(!code1.startsWith(code2) && !code2.startsWith(code1), "Code " + code1 + " is a prefix of " + code2);
                }
            }
        }
    }

    @Test
    void testBinaryCodesOnly() {
        // Verify that all codes contain only '0' and '1'
        char[] charArray = {'a', 'b', 'c', 'd'};
        int[] charFreq = {1, 2, 3, 4};

        HuffmanNode root = Huffman.buildHuffmanTree(charArray, charFreq);
        Map<Character, String> codes = Huffman.generateCodes(root);

        for (String code : codes.values()) {
            Assertions.assertTrue(code.matches("[01]+"), "Code contains non-binary characters: " + code);
        }
    }

    @Test
    void testMultipleCharactersWithLargeAlphabet() {
        char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        int[] charFreq = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

        HuffmanNode root = Huffman.buildHuffmanTree(charArray, charFreq);
        Map<Character, String> codes = Huffman.generateCodes(root);

        Assertions.assertNotNull(codes);
        Assertions.assertEquals(10, codes.size());

        // Verify all characters have codes
        for (char c : charArray) {
            Assertions.assertTrue(codes.containsKey(c));
        }
    }
}
