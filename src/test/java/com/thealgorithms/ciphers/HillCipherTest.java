package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void rejectsNonInvertibleKeyWithEvenDeterminant() {
        // det = 2, gcd(2, 26) = 2: passes the old zero-check but has no inverse mod 26
        int[][] keyMatrix = {{1, 0}, {0, 2}};
        assertThrows(IllegalArgumentException.class, () -> hillCipher.encrypt("AA", keyMatrix));
    }

    @Test
    void rejectsNonInvertibleKeyWithDeterminantThirteen() {
        // det = 13, gcd(13, 26) = 13: also not invertible mod 26
        int[][] keyMatrix = {{13, 0}, {0, 1}};
        assertThrows(IllegalArgumentException.class, () -> hillCipher.encrypt("BB", keyMatrix));
    }

    @Test
    void acceptsKeyWithNegativeCoprimeDeterminant() {
        // det = -1, coprime with 26: a valid key that a naive signed modulo would misclassify
        int[][] keyMatrix = {{1, 2}, {1, 1}};
        assertEquals("XP", hillCipher.encrypt("HI", keyMatrix));
    }
}
