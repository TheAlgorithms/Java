package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlayfairTest {

    @Test
    public void testEncryption() {
        PlayfairCipher playfairCipher = new PlayfairCipher("KEYWORD");

        String plaintext = "HELLO";
        String encryptedText = playfairCipher.encrypt(plaintext);
        assertEquals("GYIZSC", encryptedText);
    }

    @Test
    public void testDecryption() {
        PlayfairCipher playfairCipher = new PlayfairCipher("KEYWORD");

        String encryptedText = "UDRIYP";
        String decryptedText = playfairCipher.decrypt(encryptedText);
        assertEquals("NEBFVH", decryptedText);
    }

    @Test
    public void testEncryptionAndDecryption() {
        PlayfairCipher playfairCipher = new PlayfairCipher("KEYWORD");

        String plaintext = "PLAYFAIR";
        String encryptedText = playfairCipher.encrypt(plaintext);
        String decryptedText = playfairCipher.decrypt(encryptedText);

        assertEquals(plaintext, decryptedText);
    }
}
