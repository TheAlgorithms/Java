package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HillCipherTest {

    HillCipher hillCipher = new HillCipher();

    @Test
    void hillCipherEncryptTest() {
        // given
        String message = "ACT"; // Plaintext message
        int[][] keyMatrix = {{6, 24, 1}, {13, 16, 10}, {20, 17, 15}}; // Encryption key matrix

        // when
        String cipherText = hillCipher.encrypt(message, keyMatrix);

        // then
        assertEquals("POH", cipherText);
    }

    @Test
    void hillCipherDecryptTest() {
        // given
        String cipherText = "POH"; // Ciphertext message
        int[][] inverseKeyMatrix = {{8, 5, 10}, {21, 8, 21}, {21, 12, 8}}; // Decryption (inverse key) matrix

        // when
        String plainText = hillCipher.decrypt(cipherText, inverseKeyMatrix);

        // then
        assertEquals("ACT", plainText);
    }
}
