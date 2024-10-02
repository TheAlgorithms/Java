package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MonoAlphabeticTest {
    private MonoAlphabetic monoAlphabetic;

    @BeforeEach
    public void setUp() {
        // Initialize the MonoAlphabetic cipher with a sample key
        String key = "QWERTYUIOPASDFGHJKLZXCVBNM"; // Example key
        monoAlphabetic = new MonoAlphabetic(key);
    }

    @Test
    public void testEncrypt() {
        String plaintext = "HELLO";
        String expectedCiphertext = "ITSSG"; // Expected result based on the key
        String actualCiphertext = monoAlphabetic.encrypt(plaintext);

        assertEquals(expectedCiphertext, actualCiphertext, "Encryption should match the expected ciphertext.");
    }

    @Test
    public void testDecrypt() {
        String ciphertext = "ITSSG";
        String expectedPlaintext = "HELLO"; // Expected result based on the key
        String actualPlaintext = monoAlphabetic.decrypt(ciphertext);

        assertEquals(expectedPlaintext, actualPlaintext, "Decryption should match the expected plaintext.");
    }

    @Test
    public void testEncryptAndDecrypt() {
        String plaintext = "HELLO";
        String ciphertext = monoAlphabetic.encrypt(plaintext);
        String decryptedText = monoAlphabetic.decrypt(ciphertext);

        assertEquals(plaintext, decryptedText, "Decrypting the ciphertext should return the original plaintext.");
    }

    @Test
    public void testEncryptWithSpecialCharacters() {
        String plaintext = "HELLO, WORLD!";
        String expectedCiphertext = "ITSSG, GQHSG!";
        String actualCiphertext = monoAlphabetic.encrypt(plaintext);
        
        assertEquals(expectedCiphertext, actualCiphertext, "Encryption should correctly handle special characters.");
    }

    @Test
    public void testDecryptWithSpecialCharacters() {
        String ciphertext = "ITSSG, GQHSG!";
        String expectedPlaintext = "HELLO, WORLD!";
        String actualPlaintext = monoAlphabetic.decrypt(ciphertext);
        
        assertEquals(expectedPlaintext, actualPlaintext, "Decryption should correctly handle special characters.");
    }
}
