package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.crypto.SecretKey;
import org.junit.jupiter.api.Test;

public class AESEncryptionTest {

    @Test
    public void testGetSecretEncryptionKey() throws Exception {
        SecretKey key = AESEncryption.getSecretEncryptionKey();
        assertNotNull(key, "Secret key should not be null");
        assertEquals(128, key.getEncoded().length * 8, "Key size should be 128 bits");
    }

    @Test
    public void testEncryptText() throws Exception {
        String plainText = "Hello World";
        SecretKey secKey = AESEncryption.getSecretEncryptionKey();
        byte[] cipherText = AESEncryption.encryptText(plainText, secKey);

        assertNotNull(cipherText, "Ciphertext should not be null");
        assertTrue(cipherText.length > 0, "Ciphertext should not be empty");
    }

    @Test
    public void testDecryptText() throws Exception {
        String plainText = "Hello World";
        SecretKey secKey = AESEncryption.getSecretEncryptionKey();
        byte[] cipherText = AESEncryption.encryptText(plainText, secKey);

        // Decrypt the ciphertext
        String decryptedText = AESEncryption.decryptText(cipherText, secKey);

        assertNotNull(decryptedText, "Decrypted text should not be null");
        assertEquals(plainText, decryptedText, "Decrypted text should match the original plain text");
    }

    @Test
    public void testEncryptDecrypt() throws Exception {
        String plainText = "Hello AES!";
        SecretKey secKey = AESEncryption.getSecretEncryptionKey();

        // Encrypt the plaintext
        byte[] cipherText = AESEncryption.encryptText(plainText, secKey);

        // Decrypt the ciphertext
        String decryptedText = AESEncryption.decryptText(cipherText, secKey);

        assertEquals(plainText, decryptedText, "Decrypted text should match the original plain text");
    }

    @Test
    public void testBytesToHex() {
        byte[] bytes = new byte[] {0, 1, 15, 16, (byte) 255}; // Test with diverse byte values
        String hex = AESEncryption.bytesToHex(bytes);
        assertEquals("00010F10FF", hex, "Hex representation should match the expected value");
    }
}
