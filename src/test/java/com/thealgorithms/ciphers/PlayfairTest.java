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

    @Test
    public void testLowercaseKeyBehavesLikeUppercase() {
        PlayfairCipher lowercase = new PlayfairCipher("monarchy");
        PlayfairCipher uppercase = new PlayfairCipher("MONARCHY");

        assertEquals(uppercase.encrypt("HELLO"), lowercase.encrypt("HELLO"));
        assertEquals("CFSUPM", lowercase.encrypt("HELLO"));
    }

    @Test
    public void testKeyContainingJStillCoversZ() {
        PlayfairCipher playfairCipher = new PlayfairCipher("JUMP");

        String decryptedText = playfairCipher.decrypt(playfairCipher.encrypt("ZOO"));

        assertEquals("ZOXO", decryptedText);
    }

    @Test
    public void testKeyWithNonLettersIsStripped() {
        PlayfairCipher messy = new PlayfairCipher("Mon@rchy2026");
        PlayfairCipher clean = new PlayfairCipher("MONRCHY");

        assertEquals(clean.encrypt("HELLO"), messy.encrypt("HELLO"));
    }
}
