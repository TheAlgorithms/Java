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
}
