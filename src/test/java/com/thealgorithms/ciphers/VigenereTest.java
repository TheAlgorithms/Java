package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class VigenereTest {

    Vigenere vigenere = new Vigenere();

    @Test
    void testVigenereEncryptDecrypt() {
        String text = "Hello World!";
        String key = "suchsecret";

        String encryptedText = vigenere.encrypt(text, key);
        String decryptedText = vigenere.decrypt(encryptedText, key);

        assertEquals("Zynsg Aqipw!", encryptedText);
        assertEquals("Hello World!", decryptedText);
    }

    @Test
    void testWithEmptyMessage() {
        String text = "";
        String key = "anykey";

        String encryptedText = vigenere.encrypt(text, key);
        String decryptedText = vigenere.decrypt(encryptedText, key);

        assertEquals("", encryptedText);
        assertEquals("", decryptedText);
    }

    @Test
    void testWithEmptyKey() {
        String text = "This should remain the same";
        String key = "";

        assertThrows(IllegalArgumentException.class, () -> vigenere.encrypt(text, key));
        assertThrows(IllegalArgumentException.class, () -> vigenere.decrypt(text, key));
    }

    @Test
    void testWithNumbersInMessage() {
        String text = "Vigenere123!";
        String key = "cipher";

        String encryptedText = vigenere.encrypt(text, key);
        String decryptedText = vigenere.decrypt(encryptedText, key);

        assertEquals("Xqvlrvtm123!", encryptedText);
        assertEquals(text, decryptedText);
    }

    @Test
    void testLongerKeyThanMessage() {
        String text = "Short";
        String key = "VeryLongSecretKey";

        String encryptedText = vigenere.encrypt(text, key);
        String decryptedText = vigenere.decrypt(encryptedText, key);

        assertEquals("Nlfpe", encryptedText);
        assertEquals(text, decryptedText);
    }

    @Test
    void testUppercaseMessageAndKey() {
        String text = "HELLO";
        String key = "SECRET";

        String encryptedText = vigenere.encrypt(text, key);
        String decryptedText = vigenere.decrypt(encryptedText, key);

        assertEquals("ZINCS", encryptedText);
        assertEquals(text, decryptedText);
    }
}
