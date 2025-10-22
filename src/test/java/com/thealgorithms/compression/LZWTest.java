package com.thealgorithms.compression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class LZWTest {

    @Test
    void testNullAndEmptyInputs() {
        // Test that a null input to compress returns an empty list
        assertTrue(LZW.compress(null).isEmpty());

        // Test that a null input to decompress returns an empty string
        assertEquals("", LZW.decompress(null));

        // Test that an empty input to compress returns an empty list
        assertTrue(LZW.compress("").isEmpty());

        // Test that an empty input to decompress returns an empty string
        assertEquals("", LZW.decompress(Collections.emptyList()));
    }

    @Test
    void testCompressionAndDecompressionWithSimpleString() {
        // Test a classic example string
        String original = "TOBEORNOTTOBEORTOBEORNOT";
        List<Integer> compressed = LZW.compress(original);

        // Create the expected output list
        List<Integer> expectedOutput = List.of(84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263);

        // This assertion will fail if the output is not what we expect
        assertEquals(expectedOutput, compressed);

        // This assertion ensures the decompressed string is correct
        String decompressed = LZW.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    void testCompressionWithRepeatedChars() {
        // Test a string with long runs of the same character
        String original = "AAAAABBBBBAAAAA";
        List<Integer> compressed = LZW.compress(original);
        String decompressed = LZW.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    void testCompressionWithUniqueChars() {
        // Test a string with no repetitions
        String original = "ABCDEFG";
        List<Integer> compressed = LZW.compress(original);
        String decompressed = LZW.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    void testSymmetry() {
        // Test that compressing and then decompressing a complex string returns the
        // original
        String original = "THE_QUICK_BROWN_FOX_JUMPS_OVER_THE_LAZY_DOG";
        List<Integer> compressed = LZW.compress(original);
        String decompressed = LZW.decompress(compressed);
        assertEquals(original, decompressed);

        // Another symmetry test with special characters and patterns
        String original2 = "ababcbababa";
        List<Integer> compressed2 = LZW.compress(original2);
        String decompressed2 = LZW.decompress(compressed2);
        assertEquals(original2, decompressed2);
    }

    @Test
    void testInvalidCompressedData() {
        // Test that decompressing with an invalid code throws IllegalArgumentException
        // Create a list with a code that doesn't exist in the dictionary
        List<Integer> invalidCompressed = new ArrayList<>();
        invalidCompressed.add(65); // 'A' - valid
        invalidCompressed.add(999); // Invalid code (not in dictionary)

        // This should throw IllegalArgumentException with message "Bad compressed k: 999"
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> LZW.decompress(invalidCompressed));

        assertTrue(exception.getMessage().contains("Bad compressed k: 999"));
    }

    @Test
    void testDecompressionWithGapInDictionary() {
        // Test with codes that skip dictionary entries
        List<Integer> invalidCompressed = new ArrayList<>();
        invalidCompressed.add(84); // 'T' - valid
        invalidCompressed.add(500); // Way beyond current dictionary size

        // This should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> LZW.decompress(invalidCompressed));
    }
}
