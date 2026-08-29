package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RailFenceCipherTest {

    private final RailFenceCipher railFenceCipher = new RailFenceCipher();

    @Test
    void testEncrypt() {
        assertEquals("WECRLTEERDSOEEFEAOCAIVDEN", railFenceCipher.encrypt("WEAREDISCOVEREDFLEEATONCE", 3));
    }

    @Test
    void testDecrypt() {
        assertEquals("WEAREDISCOVEREDFLEEATONCE", railFenceCipher.decrypt("WECRLTEERDSOEEFEAOCAIVDEN", 3));
    }

    @ParameterizedTest
    @CsvSource({"HELLOWORLD, 2", "HELLOWORLD, 3", "HELLOWORLD, 4", "ATTACKATDAWN, 5", "abcdefghij, 6"})
    void testRoundTrip(String message, int rails) {
        assertEquals(message, railFenceCipher.decrypt(railFenceCipher.encrypt(message, rails), rails));
    }

    /**
     * Every character of the input must survive encryption, including the ones that used to collide
     * with the placeholder that marked unused cells of the rail matrix.
     */
    @ParameterizedTest
    @ValueSource(strings = {"ab\ncdef", "line1\nline2\nline3", "\n\n\n\n\n", "a\nb", "tabs\tand\nnewlines\r\n"})
    void testControlCharactersArePreserved(String message) {
        for (int rails = 2; rails <= 5; rails++) {
            String encrypted = railFenceCipher.encrypt(message, rails);
            assertEquals(message.length(), encrypted.length(), "characters were dropped with " + rails + " rails");
            assertEquals(message, railFenceCipher.decrypt(encrypted, rails), "round trip failed with " + rails + " rails");
        }
    }

    @Test
    void testEncryptWithNewlineMatchesReferencePattern() {
        // Rails of "ab\ncdef" with 3 rails: {a, d} / {b, c, e} / {\n, f}
        assertEquals("adbce\nf", railFenceCipher.encrypt("ab\ncdef", 3));
    }

    @ParameterizedTest
    @CsvSource({"HELLO, 1", "HELLO, 5", "HELLO, 9", "'', 1", "'', 4"})
    void testDegenerateRailCountsReturnInput(String message, int rails) {
        assertEquals(message, railFenceCipher.encrypt(message, rails));
        assertEquals(message, railFenceCipher.decrypt(message, rails));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -7})
    void testNonPositiveRailCountThrows(int rails) {
        assertThrows(IllegalArgumentException.class, () -> railFenceCipher.encrypt("HELLO", rails));
        assertThrows(IllegalArgumentException.class, () -> railFenceCipher.decrypt("HELLO", rails));
    }

    @Test
    void testNullInputThrows() {
        assertThrows(IllegalArgumentException.class, () -> railFenceCipher.encrypt(null, 3));
        assertThrows(IllegalArgumentException.class, () -> railFenceCipher.decrypt(null, 3));
    }
}
