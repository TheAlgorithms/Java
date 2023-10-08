package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PolybiusTest {

    @Test
    void testEncrypt() {
        // Given
        String plaintext = "HELLOWORLD";

        // When
        String actual = Polybius.encrypt(plaintext);

        // Then
        assertEquals("12042121244124322103", actual);
    }

    @Test
    void testDecrypt() {
        // Given
        String ciphertext = "12042121244124322103";

        // When
        String actual = Polybius.decrypt(ciphertext);

        // Then
        assertEquals("HELLOWORLD", actual);
    }

    @Test
    void testIsTextTheSameAfterEncryptionAndDecryption() {
        // Given
        String plaintext = "HELLOWORLD";

        // When
        String encryptedText = Polybius.encrypt(plaintext);
        String actual = Polybius.decrypt(encryptedText);

        // Then
        assertEquals(plaintext, actual);
    }
}
