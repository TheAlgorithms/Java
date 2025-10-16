package com.thealgorithms.compression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ArithmeticCodingTest {

    @Test
    void testThrowsExceptionForNullOrEmptyInput() {
        // Test that null input throws IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> ArithmeticCoding.compress(null));

        // Test that empty string throws IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> ArithmeticCoding.compress(""));
    }

    @Test
    void testCompressionAndDecompressionSimple() {
        String original = "BABA";
        Map<Character, ArithmeticCoding.Symbol> probTable = ArithmeticCoding.calculateProbabilities(original);
        BigDecimal compressed = ArithmeticCoding.compress(original);

        // Verify that compression produces a valid number in [0, 1)
        assertNotNull(compressed);
        assertTrue(compressed.compareTo(BigDecimal.ZERO) >= 0);
        assertTrue(compressed.compareTo(BigDecimal.ONE) < 0);

        // Verify decompression restores the original string
        String decompressed = ArithmeticCoding.decompress(compressed, original.length(), probTable);
        assertEquals(original, decompressed);
    }

    @Test
    void testSymmetryWithComplexString() {
        String original = "THE_QUICK_BROWN_FOX_JUMPS_OVER_THE_LAZY_DOG";
        Map<Character, ArithmeticCoding.Symbol> probTable = ArithmeticCoding.calculateProbabilities(original);
        BigDecimal compressed = ArithmeticCoding.compress(original);

        // Verify compression produces a number in valid range
        assertTrue(compressed.compareTo(BigDecimal.ZERO) >= 0);
        assertTrue(compressed.compareTo(BigDecimal.ONE) < 0);

        // Verify symmetry: decompress(compress(x)) == x
        String decompressed = ArithmeticCoding.decompress(compressed, original.length(), probTable);
        assertEquals(original, decompressed);
    }

    @Test
    void testSymmetryWithRepetitions() {
        String original = "MISSISSIPPI";
        Map<Character, ArithmeticCoding.Symbol> probTable = ArithmeticCoding.calculateProbabilities(original);
        BigDecimal compressed = ArithmeticCoding.compress(original);

        // Verify compression produces a number in valid range
        assertTrue(compressed.compareTo(BigDecimal.ZERO) >= 0);
        assertTrue(compressed.compareTo(BigDecimal.ONE) < 0);

        // Verify the compression-decompression cycle
        String decompressed = ArithmeticCoding.decompress(compressed, original.length(), probTable);
        assertEquals(original, decompressed);
    }

    @Test
    void testSingleCharacterString() {
        String original = "AAAAA";
        Map<Character, ArithmeticCoding.Symbol> probTable = ArithmeticCoding.calculateProbabilities(original);
        BigDecimal compressed = ArithmeticCoding.compress(original);

        // Even with a single unique character, compression should work
        assertTrue(compressed.compareTo(BigDecimal.ZERO) >= 0);
        assertTrue(compressed.compareTo(BigDecimal.ONE) < 0);

        String decompressed = ArithmeticCoding.decompress(compressed, original.length(), probTable);
        assertEquals(original, decompressed);
    }

    @Test
    void testCompressionOutputDemo() {
        // Demonstrate actual compression output similar to LZW test
        String original = "BABA";
        BigDecimal compressed = ArithmeticCoding.compress(original);

        // Example: "BABA" compresses to approximately 0.625
        // This shows that the entire message is encoded as a single number
        System.out.println("Original: " + original);
        System.out.println("Compressed to: " + compressed);
        System.out.println("Compression: " + original.length() + " characters -> 1 BigDecimal number");

        // Verify the compressed value is in valid range [0, 1)
        assertTrue(compressed.compareTo(BigDecimal.ZERO) >= 0);
        assertTrue(compressed.compareTo(BigDecimal.ONE) < 0);
    }

    @Test
    void testProbabilityTableCalculation() {
        // Test that probability table is calculated correctly
        String text = "AABBC";
        Map<Character, ArithmeticCoding.Symbol> probTable = ArithmeticCoding.calculateProbabilities(text);

        // Verify all characters are in the table
        assertTrue(probTable.containsKey('A'));
        assertTrue(probTable.containsKey('B'));
        assertTrue(probTable.containsKey('C'));

        // Verify probability ranges are valid
        for (ArithmeticCoding.Symbol symbol : probTable.values()) {
            assertTrue(symbol.low().compareTo(BigDecimal.ZERO) >= 0);
            assertTrue(symbol.high().compareTo(BigDecimal.ONE) <= 0);
            assertTrue(symbol.low().compareTo(symbol.high()) < 0);
        }
    }
}
