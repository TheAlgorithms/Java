package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class OneTimePadCipherTest {

    @Test
    void encryptAndDecryptWithRandomKeyRestoresPlaintext() {
        String plaintext = "The quick brown fox jumps over the lazy dog.";
        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);

        byte[] key = OneTimePadCipher.generateKey(plaintextBytes.length);

        byte[] ciphertext = OneTimePadCipher.encrypt(plaintextBytes, key);
        byte[] decrypted = OneTimePadCipher.decrypt(ciphertext, key);

        assertArrayEquals(plaintextBytes, decrypted);
        assertEquals(plaintext, new String(decrypted, StandardCharsets.UTF_8));
    }

    @Test
    void generateKeyWithNegativeLengthThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> OneTimePadCipher.generateKey(-1));
    }

    @Test
    void encryptWithMismatchedKeyLengthThrowsException() {
        byte[] data = "hello".getBytes(StandardCharsets.UTF_8);
        byte[] shortKey = OneTimePadCipher.generateKey(2);

        assertThrows(
                IllegalArgumentException.class,
                () -> OneTimePadCipher.encrypt(data, shortKey));
    }

    @Test
    void decryptWithMismatchedKeyLengthThrowsException() {
        byte[] data = "hello".getBytes(StandardCharsets.UTF_8);
        byte[] key = OneTimePadCipher.generateKey(data.length);
        byte[] ciphertext = OneTimePadCipher.encrypt(data, key);

        byte[] wrongSizedKey = OneTimePadCipher.generateKey(data.length + 1);

        assertThrows(
                IllegalArgumentException.class,
                () -> OneTimePadCipher.decrypt(ciphertext, wrongSizedKey));
    }
}
