package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ADFGVXCipherTest {

    ADFGVXCipher adfgvxCipher = new ADFGVXCipher();

    @Test
    void adfgvxCipherEncryptTest() {
        // given
        String message = "attack at 1200am"; // Plaintext message
        String keyword = "PRIVACY";

        // when
        String cipherText = adfgvxCipher.encrypt(message, keyword);

        // then
        assertEquals("DGDDDAGDDGAFADDFDADVDVFAADVX", cipherText);
    }

    @Test
    void adfgvxCipherDecryptTest() {
        // given
        String cipherText = "DGDDDAGDDGAFADDFDADVDVFAADVX"; // Ciphertext message
        String keyword = "PRIVACY";

        // when
        String plainText = adfgvxCipher.decrypt(cipherText, keyword);

        // then
        assertEquals("ATTACKAT1200AM", plainText);
    }
}
