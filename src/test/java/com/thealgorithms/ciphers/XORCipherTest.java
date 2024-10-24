package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class XORCipherTest {

    @Test
    void xorEncryptDecryptTest() {
        String plaintext = "My t&xt th@t will be ençrypted...";
        String key = "My ç&cret key!";

        String cipherText = XORCipher.encrypt(plaintext, key);
        String decryptedText = XORCipher.decrypt(cipherText, key);

        assertEquals("My t&xt th@t will be ençrypted...", decryptedText);
    }

    @Test
    void testEmptyPlaintext() {
        String plaintext = "";
        String key = "anykey";

        String cipherText = XORCipher.encrypt(plaintext, key);
        String decryptedText = XORCipher.decrypt(cipherText, key);

        assertEquals("", cipherText);
        assertEquals("", decryptedText);
    }

    @Test
    void testEmptyKey() {
        String plaintext = "Hello World!";
        String key = "";

        assertThrows(IllegalArgumentException.class, () -> XORCipher.encrypt(plaintext, key));
        assertThrows(IllegalArgumentException.class, () -> XORCipher.decrypt(plaintext, key));
    }

    @Test
    void testShortKey() {
        String plaintext = "Short message";
        String key = "k";

        String cipherText = XORCipher.encrypt(plaintext, key);
        String decryptedText = XORCipher.decrypt(cipherText, key);

        assertEquals(plaintext, decryptedText);
    }

    @Test
    void testNonASCIICharacters() {
        String plaintext = "こんにちは世界"; // "Hello World" in Japanese (Konichiwa Sekai)
        String key = "key";

        String cipherText = XORCipher.encrypt(plaintext, key);
        String decryptedText = XORCipher.decrypt(cipherText, key);

        assertEquals(plaintext, decryptedText);
    }

    @Test
    void testSameKeyAndPlaintext() {
        String plaintext = "samekey";
        String key = "samekey";

        String cipherText = XORCipher.encrypt(plaintext, key);
        String decryptedText = XORCipher.decrypt(cipherText, key);

        assertEquals(plaintext, decryptedText);
    }

    @Test
    void testLongPlaintextShortKey() {
        String plaintext = "This is a long plaintext message.";
        String key = "key";

        String cipherText = XORCipher.encrypt(plaintext, key);
        String decryptedText = XORCipher.decrypt(cipherText, key);

        assertEquals(plaintext, decryptedText);
    }
}
