package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ADFGVXCipherTest {

    private final ADFGVXCipher adfgvxCipher = new ADFGVXCipher();

    @Test
    void testEncrypt() {
        String message = "attack at 1200am";
        String key = "PRIVACY";

        String encrypted = adfgvxCipher.encrypt(message, key);
        assertEquals("DGDDDAGDDGAFADDFDADVDVFAADVX", encrypted);
    }

    @Test
    void testDecrypt() {
        String encrypted = "DGDDDAGDDGAFADDFDADVDVFAADVX";
        String key = "PRIVACY";

        String decrypted = adfgvxCipher.decrypt(encrypted, key);
        assertEquals("ATTACKAT1200AM", decrypted);
    }

    @Test
    void testEmptyInput() {
        String encrypted = adfgvxCipher.encrypt("", "PRIVACY");
        String decrypted = adfgvxCipher.decrypt("", "PRIVACY");
        assertEquals("", encrypted);
        assertEquals("", decrypted);
    }

    @Test
    void testShortKey() {
        String message = "TESTING";
        String key = "A";

        String encrypted = adfgvxCipher.encrypt(message, key);
        String decrypted = adfgvxCipher.decrypt(encrypted, key);
        assertEquals("TESTING", decrypted);
    }

    @Test
    void testRoundTripWhenLengthIsNotMultipleOfKeyLength() {
        // "HELLO" fractionates to 10 ADFGVX letters; 10 % 7 != 0, so the last row of
        // the transposition grid is partial. This used to decrypt to "S35LL".
        String key = "PRIVACY";
        String encrypted = adfgvxCipher.encrypt("HELLO", key);
        assertEquals("HELLO", adfgvxCipher.decrypt(encrypted, key));
    }

    @Test
    void testRoundTripWithRepeatedLettersInKey() {
        // indexOf-based column order maps both Bs and both As to the same column,
        // which used to corrupt the message and leak "null" into the output.
        String key = "BADCAB";
        String encrypted = adfgvxCipher.encrypt("ATTACKATDAWN", key);
        assertEquals("ATTACKATDAWN", adfgvxCipher.decrypt(encrypted, key));
    }

    @Test
    void testDecryptRejectsInvalidCiphertext() {
        // Z is not an ADFGVX letter; the old code silently appended "null".
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> adfgvxCipher.decrypt("ZZ", "KEY"));
    }
}
