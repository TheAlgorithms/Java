package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayfairTest {
    private PlayfairCipher playfair;
    private static final String KEYWORD = "KEYWORD";

    @BeforeEach
    public void setup() {
        playfair = new PlayfairCipher(KEYWORD);
    }

    @Test
    void shouldEncryptAndDecryptDuringSameRowDigraph() {
        String plaintext = KEYWORD.substring(0, 2);

        String encrypted = playfair.encrypt(plaintext);
        String decrypted = playfair.decrypt(encrypted);

        assertEquals(plaintext, decrypted, "Should not decrypt to the same letters");
    }

    @Test
    void shouldPadOddLengthPlaintext() {
        String plaintext = "cat";

        String encrypted = playfair.encrypt(plaintext);

        assertEquals(0, encrypted.length() % 2, "Should be even length");
    }

    @Test
    public void testEncryption() {
        String plaintext = "HELLO";
        String encryptedText = playfair.encrypt(plaintext);
        assertEquals("GYIZSC", encryptedText);
    }

    @Test
    public void testDecryption() {
        String encryptedText = "UDRIYP";
        String decryptedText = playfair.decrypt(encryptedText);
        assertEquals("NEBFVH", decryptedText);
    }

    @Test
    public void testEncryptionAndDecryption() {
        String plaintext = "PLAYFAIR";
        String encryptedText = playfair.encrypt(plaintext);
        String decryptedText = playfair.decrypt(encryptedText);

        assertEquals(plaintext, decryptedText);
    }
}
