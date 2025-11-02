package com.thealgorithms.ciphers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OneTimePadCipherTest {
    @Test
    void testGenerateKeyLength() {
        int length = 16;
        byte[] key = OneTimePadCipher.generateKey(length);
        assertNotNull(key, "Key should not be null");
        assertEquals(length, key.length, "Key length should match requested length");
    }

    @Test
    void testEncryptDecrypt() {
        String plaintext = "Hello, OneTimePad!";
        byte[] input = plaintext.getBytes();

        byte[] key = OneTimePadCipher.generateKey(input.length);

        byte[] ciphertext = OneTimePadCipher.applyOTP(input, key);
        assertNotNull(ciphertext);
        assertNotEquals(plaintext, new String(ciphertext), "Ciphertext should differ from plaintext");

        byte[] decrypted = OneTimePadCipher.applyOTP(ciphertext, key);
        assertEquals(plaintext, new String(decrypted), "Decrypted text should match original plaintext");
    }

    @Test
    void testInvalidKeyLength() {
        byte[] input = "Hello, OneTimePad!".getBytes();
        byte[] wrongKey = OneTimePadCipher.generateKey(input.length + 1);

        assertThrows(IllegalArgumentException.class, () -> {
            OneTimePadCipher.applyOTP(input, wrongKey);
        });
    }

    @Test
    void testEmptyInputAndKey() {
        byte[] input = new byte[0];
        byte[] key = new byte[0];
        byte[] result = OneTimePadCipher.applyOTP(input, key);
        assertEquals(0, result.length, "Empty input should return empty output");
    }

    @Test
    void testDeterministicXorProperty() {
        byte[] input = "XORTest".getBytes();
        byte[] key = OneTimePadCipher.generateKey(input.length);

        byte[] once = OneTimePadCipher.applyOTP(input, key);
        byte[] twice = OneTimePadCipher.applyOTP(once, key);

        assertArrayEquals(input, twice, "Applying OTP twice with same key should return original input");
    }

    @Test
    void testRandomKeyUniqueness() {
        byte[] key1 = OneTimePadCipher.generateKey(32);
        byte[] key2 = OneTimePadCipher.generateKey(32);

        assertFalse(Arrays.equals(key1, key2), "Two generated keys should not be identical");
    }

    @Test
    void testUnicodeCharacters() {
        String plaintext = "Hello, OneTimePad!";
        byte[] input = plaintext.getBytes();

        byte[] key = OneTimePadCipher.generateKey(input.length);
        byte[] ciphertext = OneTimePadCipher.applyOTP(input, key);
        byte[] decrypted = OneTimePadCipher.applyOTP(ciphertext, key);

        assertEquals(plaintext, new String(decrypted), "Decrypted Unicode text should match original");
    }

    @Test
    void testNullInputs() {
        byte[] validKey = OneTimePadCipher.generateKey(4);
        byte[] validInput = "Test".getBytes();

        assertThrows(NullPointerException.class, () -> OneTimePadCipher.applyOTP(null, validKey));
        assertThrows(NullPointerException.class, () -> OneTimePadCipher.applyOTP(validInput, null));
    }

    @Test
    void testNegativeKeyLength() {
        assertThrows(NegativeArraySizeException.class, () -> OneTimePadCipher.generateKey(-5));
    }
}