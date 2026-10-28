package com.thealgorithms.compression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class HuffmanCodingTest {

    @Test
    void testStandardLifecycle() {
        String input = "efficiency is key";
        HuffmanCoding huffman = new HuffmanCoding(input);

        String encoded = huffman.encode(input);
        assertNotNull(encoded);
        assertTrue(encoded.matches("[01]+"));
        assertEquals(input, huffman.decode(encoded));
    }

    @Test
    void testNullAndEmptyHandling() {
        HuffmanCoding huffman = new HuffmanCoding("");
        assertEquals("", huffman.encode(""));
        assertEquals("", huffman.decode(""));

        HuffmanCoding huffmanNull = new HuffmanCoding(null);
        assertEquals("", huffmanNull.encode(null));
        assertEquals("", huffmanNull.decode(null));
    }

    @Test
    void testSingleCharacterEdgeCase() {
        String input = "aaaaa";
        HuffmanCoding huffman = new HuffmanCoding(input);

        String encoded = huffman.encode(input);
        assertEquals("00000", encoded);
        assertEquals(input, huffman.decode(encoded));
    }

    @Test
    void testUnicodeAndSpecialCharacters() {
        // Tests spacing, symbols, non-latin alphabets, and surrogate pairs (emojis)
        String input = "Hello, World! 🚀\nLine 2: こんにちは";
        HuffmanCoding huffman = new HuffmanCoding(input);

        String encoded = huffman.encode(input);
        assertEquals(input, huffman.decode(encoded));
    }

    @Test
    void testFailFastOnUnseenCharacter() {
        HuffmanCoding huffman = new HuffmanCoding("abc");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> huffman.encode("abcd") // 'd' was not in the original tree
        );
        assertTrue(exception.getMessage().contains("not found in Huffman dictionary"));
    }

    @Test
    void testFailFastOnInvalidBinaryCharacter() {
        HuffmanCoding huffman = new HuffmanCoding("abc");
        String encoded = huffman.encode("abc");

        // Inject a '2' into the binary stream
        String corruptedEncoded = encoded + "2";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> huffman.decode(corruptedEncoded));
        assertTrue(exception.getMessage().contains("contains invalid characters"));
    }

    @Test
    void testFailFastOnIncompleteSequence() {
        HuffmanCoding huffman = new HuffmanCoding("abcd");
        String encoded = huffman.encode("abc");

        // Truncate the last bit to simulate an incomplete byte/sequence transfer
        String truncatedEncoded = encoded.substring(0, encoded.length() - 1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> huffman.decode(truncatedEncoded));
        assertTrue(exception.getMessage().contains("incomplete sequence"));
    }

    @Test
    void testImmutabilityOfDictionary() {
        HuffmanCoding huffman = new HuffmanCoding("abc");
        var codes = huffman.getHuffmanCodes();

        assertThrows(UnsupportedOperationException.class, () -> codes.put('z', "0101"));
    }

    @Test
    void testStressVolume() {
        StringBuilder sb = new StringBuilder();
        // Generate a 100,000 character string
        for (int i = 0; i < 100000; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        String largeInput = sb.toString();

        HuffmanCoding huffman = new HuffmanCoding(largeInput);
        String encoded = huffman.encode(largeInput);

        assertEquals(largeInput, huffman.decode(encoded));
    }
}
