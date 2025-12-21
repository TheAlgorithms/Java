package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for TripleDES (3DES) encryption algorithm.
 * Tests both three-key and two-key variants of 3DES.
 */
public class TripleDESTest {

    private TripleDES tripleDES;
    private TripleDES tripleDES2Key;

    // Three different 64-bit binary keys for testing
    private static final String KEY1 = "0000111000110010100100100011001011101010011011010000110101110011";
    private static final String KEY2 = "0101010101010101010101010101010101010101010101010101010101010101";
    private static final String KEY3 = "1111000011110000111100001111000011110000111100001111000011110000";

    @BeforeEach
    public void setUp() {
        // Initialize with three different keys
        tripleDES = new TripleDES(KEY1, KEY2, KEY3);
        
        // Initialize with two keys (key1 == key3)
        tripleDES2Key = new TripleDES(KEY1, KEY2);
    }

    @Test
    void testEncryptDecryptWithThreeKeys() {
        // given
        String plainText = "Hello, this is a test message for 3DES!";
        
        // when
        String cipherText = tripleDES.encrypt(plainText);
        String decryptedText = tripleDES.decrypt(cipherText);
        
        // then
        assertEquals(plainText, decryptedText);
        assertNotEquals(plainText, cipherText);
    }

    @Test
    void testEncryptDecryptWithTwoKeys() {
        // given
        String plainText = "Testing 2-key 3DES variant";
        
        // when
        String cipherText = tripleDES2Key.encrypt(plainText);
        String decryptedText = tripleDES2Key.decrypt(cipherText);
        
        // then
        assertEquals(plainText, decryptedText);
        assertNotEquals(plainText, cipherText);
    }

    @Test
    void testEmptyString() {
        // given
        String plainText = "";
        
        // when
        String cipherText = tripleDES.encrypt(plainText);
        String decryptedText = tripleDES.decrypt(cipherText);
        
        // then
        assertEquals(plainText, decryptedText);
    }

    @Test
    void testSpecialCharacters() {
        // given
        String plainText = "Special chars: !@#$%^&*()_+-=[]{}|;':\",./<>?";
        
        // when
        String cipherText = tripleDES.encrypt(plainText);
        String decryptedText = tripleDES.decrypt(cipherText);
        
        // then
        assertEquals(plainText, decryptedText);
    }

    @Test
    void testLongMessage() {
        // given
        String plainText = "This is a longer message to test the 3DES encryption algorithm. " +
                          "It should handle messages of various lengths correctly. " +
                          "The quick brown fox jumps over the lazy dog.";
        
        // when
        String cipherText = tripleDES.encrypt(plainText);
        String decryptedText = tripleDES.decrypt(cipherText);
        
        // then
        assertEquals(plainText, decryptedText);
    }

    @Test
    void testKeyGetters() {
        // then
        assertEquals(KEY1, tripleDES.getKey1());
        assertEquals(KEY2, tripleDES.getKey2());
        assertEquals(KEY3, tripleDES.getKey3());
        
        // For 2-key variant, key1 should equal key3
        assertEquals(KEY1, tripleDES2Key.getKey1());
        assertEquals(KEY2, tripleDES2Key.getKey2());
        assertEquals(KEY1, tripleDES2Key.getKey3());
    }

    @Test
    void testKeySetters() {
        // given
        String newKey1 = "1111111111111111111111111111111111111111111111111111111111111111";
        String newKey2 = "0000000000000000000000000000000000000000000000000000000000000000";
        String newKey3 = "1010101010101010101010101010101010101010101010101010101010101010";
        
        // when
        tripleDES.setKey1(newKey1);
        tripleDES.setKey2(newKey2);
        tripleDES.setKey3(newKey3);
        
        // then
        assertEquals(newKey1, tripleDES.getKey1());
        assertEquals(newKey2, tripleDES.getKey2());
        assertEquals(newKey3, tripleDES.getKey3());
    }

    @Test
    void testConsistencyAfterKeyChange() {
        // given
        String plainText = "Testing key change functionality";
        String originalCipher = tripleDES.encrypt(plainText);
        String originalDecrypted = tripleDES.decrypt(originalCipher);
        
        // when - change keys
        String newKey = "1100110011001100110011001100110011001100110011001100110011001100";
        tripleDES.setKey1(newKey);
        tripleDES.setKey2(newKey);
        tripleDES.setKey3(newKey);
        
        // then - encryption/decryption should still work with new keys
        String newCipher = tripleDES.encrypt(plainText);
        String newDecrypted = tripleDES.decrypt(newCipher);
        
        assertEquals(plainText, newDecrypted);
        assertEquals(plainText, originalDecrypted);
        // Cipher text should be different with different keys
        assertNotEquals(originalCipher, newCipher);
    }
}