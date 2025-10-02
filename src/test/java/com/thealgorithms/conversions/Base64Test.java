package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Test cases for Base64 encoding and decoding.
 *
 * Author: Nithin U.
 * Github: https://github.com/NithinU2802
 */

class Base64Test {

    @Test
    void testBase64Alphabet() {
        // Test that all Base64 characters are handled correctly
        String allChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        String encoded = Base64.encode(allChars);
        String decoded = Base64.decodeToString(encoded);
        assertEquals(allChars, decoded);
    }

    @ParameterizedTest
    @CsvSource({"'', ''", "A, QQ==", "AB, QUI=", "ABC, QUJD", "ABCD, QUJDRA==", "Hello, SGVsbG8=", "'Hello World', SGVsbG8gV29ybGQ=", "'Hello, World!', 'SGVsbG8sIFdvcmxkIQ=='", "'The quick brown fox jumps over the lazy dog', 'VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wcyBvdmVyIHRoZSBsYXp5IGRvZw=='",
        "123456789, MTIzNDU2Nzg5", "'Base64 encoding test', 'QmFzZTY0IGVuY29kaW5nIHRlc3Q='"})
    void
    testStringEncoding(String input, String expected) {
        assertEquals(expected, Base64.encode(input));
    }

    @ParameterizedTest
    @CsvSource({"'', ''", "QQ==, A", "QUI=, AB", "QUJD, ABC", "QUJDRA==, ABCD", "SGVsbG8=, Hello", "'SGVsbG8gV29ybGQ=', 'Hello World'", "'SGVsbG8sIFdvcmxkIQ==', 'Hello, World!'", "'VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wcyBvdmVyIHRoZSBsYXp5IGRvZw==', 'The quick brown fox jumps over the lazy dog'",
        "MTIzNDU2Nzg5, 123456789", "'QmFzZTY0IGVuY29kaW5nIHRlc3Q=', 'Base64 encoding test'"})
    void
    testStringDecoding(String input, String expected) {
        assertEquals(expected, Base64.decodeToString(input));
    }

    @Test
    void testByteArrayEncoding() {
        byte[] input = {72, 101, 108, 108, 111};
        String expected = "SGVsbG8=";
        assertEquals(expected, Base64.encode(input));
    }

    @Test
    void testByteArrayDecoding() {
        String input = "SGVsbG8=";
        byte[] expected = {72, 101, 108, 108, 111};
        assertArrayEquals(expected, Base64.decode(input));
    }

    @Test
    void testRoundTripEncoding() {
        String[] testStrings = {"", "A", "AB", "ABC", "Hello, World!", "The quick brown fox jumps over the lazy dog", "1234567890", "Special chars: !@#$%^&*()_+-=[]{}|;:,.<>?",
            "Unicode: வணக்கம்", // Tamil for "Hello"
            "Multi-line\nstring\rwith\tdifferent\nwhitespace"};

        for (String original : testStrings) {
            String encoded = Base64.encode(original);
            String decoded = Base64.decodeToString(encoded);
            assertEquals(original, decoded, "Round trip failed for: " + original);
        }
    }

    @Test
    void testRoundTripByteArrayEncoding() {
        byte[][] testArrays = {{}, {0}, {-1}, {0, 1, 2, 3, 4, 5}, {-128, -1, 0, 1, 127}, {72, 101, 108, 108, 111, 44, 32, 87, 111, 114, 108, 100, 33}};

        for (byte[] original : testArrays) {
            String encoded = Base64.encode(original);
            byte[] decoded = Base64.decode(encoded);
            assertArrayEquals(original, decoded, "Round trip failed for byte array");
        }
    }

    @Test
    void testBinaryData() {
        // Test with binary data that might contain null bytes
        byte[] binaryData = new byte[256];
        for (int i = 0; i < 256; i++) {
            binaryData[i] = (byte) i;
        }

        String encoded = Base64.encode(binaryData);
        byte[] decoded = Base64.decode(encoded);
        assertArrayEquals(binaryData, decoded);
    }

    @Test
    void testNullInputEncoding() {
        assertThrows(IllegalArgumentException.class, () -> Base64.encode((String) null));
        assertThrows(IllegalArgumentException.class, () -> Base64.encode((byte[]) null));
    }

    @Test
    void testNullInputDecoding() {
        assertThrows(IllegalArgumentException.class, () -> Base64.decode(null));
        assertThrows(IllegalArgumentException.class, () -> Base64.decodeToString(null));
    }

    @Test
    void testInvalidBase64Characters() {
        assertThrows(IllegalArgumentException.class, () -> Base64.decode("SGVsbG8@"));
        assertThrows(IllegalArgumentException.class, () -> Base64.decode("SGVsbG8#"));
        assertThrows(IllegalArgumentException.class, () -> Base64.decode("SGVsbG8$"));
        assertThrows(IllegalArgumentException.class, () -> Base64.decode("SGVsbG8%"));
    }

    @Test
    void testInvalidLength() {
        // Length must be multiple of 4
        assertThrows(IllegalArgumentException.class, () -> Base64.decode("Q"));
        assertThrows(IllegalArgumentException.class, () -> Base64.decode("QQ"));
        assertThrows(IllegalArgumentException.class, () -> Base64.decode("QQQ"));
    }

    @Test
    void testInvalidPaddingPosition() {
        // '=' can only appear at the end
        assertThrows(IllegalArgumentException.class, () -> Base64.decode("Q=QQ"));
        assertThrows(IllegalArgumentException.class, () -> Base64.decode("Q=Q="));
        assertThrows(IllegalArgumentException.class, () -> Base64.decode("=QQQ"));
    }

    @Test
    void testPaddingVariations() {
        // Test different padding scenarios '='
        assertEquals("A", Base64.decodeToString("QQ=="));
        assertEquals("AB", Base64.decodeToString("QUI="));
        assertEquals("ABC", Base64.decodeToString("QUJD"));
    }

    @Test
    void testPaddingConsistency() {
        // Ensure that strings requiring different amounts of padding encode/decode correctly
        String[] testCases = {"A", "AB", "ABC", "ABCD", "ABCDE", "ABCDEF"};

        for (String test : testCases) {
            String encoded = Base64.encode(test);
            String decoded = Base64.decodeToString(encoded);
            assertEquals(test, decoded);

            // Verify padding is correct
            int expectedPadding = (3 - (test.length() % 3)) % 3;
            int actualPadding = 0;
            for (int i = encoded.length() - 1; i >= 0 && encoded.charAt(i) == '='; i--) {
                actualPadding++;
            }
            assertEquals(expectedPadding, actualPadding, "Incorrect padding for: " + test);
        }
    }

    @Test
    void testLargeData() {
        // Test with larger data to ensure scalability
        StringBuilder largeString = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeString.append("This is a test string for Base64 encoding. ");
        }

        String original = largeString.toString();
        String encoded = Base64.encode(original);
        String decoded = Base64.decodeToString(encoded);
        assertEquals(original, decoded);
    }

    @Test
    void testEmptyAndSingleCharacter() {
        // Test edge cases
        assertEquals("", Base64.encode(""));
        assertEquals("", Base64.decodeToString(""));

        assertEquals("QQ==", Base64.encode("A"));
        assertEquals("A", Base64.decodeToString("QQ=="));
    }
}
