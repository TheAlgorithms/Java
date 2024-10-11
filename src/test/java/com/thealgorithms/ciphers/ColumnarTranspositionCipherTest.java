package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ColumnarTranspositionCipherTest {
    private String keyword;
    private String plaintext;

    @BeforeEach
    public void setUp() {
        keyword = "keyword";
        plaintext = "This is a test message for Columnar Transposition Cipher";
    }

    @Test
    public void testEncryption() {
        String encryptedText = ColumnarTranspositionCipher.encrypt(plaintext, keyword);
        assertNotNull(encryptedText, "The encrypted text should not be null.");
        assertFalse(encryptedText.isEmpty(), "The encrypted text should not be empty.");
        // Check if the encrypted text is different from the plaintext
        assertNotEquals(plaintext, encryptedText, "The encrypted text should be different from the plaintext.");
    }

    @Test
    public void testDecryption() {
        String encryptedText = ColumnarTranspositionCipher.encrypt(plaintext, keyword);
        String decryptedText = ColumnarTranspositionCipher.decrypt();

        assertEquals(plaintext.replaceAll(" ", ""), decryptedText.replaceAll(" ", ""), "The decrypted text should match the original plaintext, ignoring spaces.");
        assertEquals(encryptedText, ColumnarTranspositionCipher.encrypt(plaintext, keyword), "The encrypted text should be the same when encrypted again.");
    }

    @Test
    public void testLongPlainText() {
        String longText = "This is a significantly longer piece of text to test the encryption and decryption capabilities of the Columnar Transposition Cipher. It should handle long strings gracefully.";
        String encryptedText = ColumnarTranspositionCipher.encrypt(longText, keyword);
        String decryptedText = ColumnarTranspositionCipher.decrypt();
        assertEquals(longText.replaceAll(" ", ""), decryptedText.replaceAll(" ", ""), "The decrypted text should match the original long plaintext, ignoring spaces.");
        assertEquals(encryptedText, ColumnarTranspositionCipher.encrypt(longText, keyword), "The encrypted text should be the same when encrypted again.");
    }
}
