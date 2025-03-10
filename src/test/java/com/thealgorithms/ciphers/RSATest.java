package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class RSATest {

    private final RSA rsa = new RSA(1024);

    @Test
    void testEncryptDecryptString() {
        String originalMessage = "Such secure";
        String encryptedMessage = rsa.encrypt(originalMessage);
        String decryptedMessage = rsa.decrypt(encryptedMessage);
        assertEquals(originalMessage, decryptedMessage);
    }

    @Test
    void testEncryptDecryptBigInteger() {
        BigInteger originalMessage = new BigInteger("12345678901234567890");
        BigInteger encryptedMessage = rsa.encrypt(originalMessage);
        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
        assertEquals(originalMessage, decryptedMessage);
    }

    @Test
    void testEmptyMessage() {
        String originalMessage = "";
        assertThrows(IllegalArgumentException.class, () -> rsa.encrypt(originalMessage));
        assertThrows(IllegalArgumentException.class, () -> rsa.decrypt(originalMessage));
    }

    @Test
    void testDifferentKeySizes() {
        // Testing with 512-bit RSA keys
        RSA smallRSA = new RSA(512);
        String originalMessage = "Test with smaller key";

        String encryptedMessage = smallRSA.encrypt(originalMessage);
        String decryptedMessage = smallRSA.decrypt(encryptedMessage);

        assertEquals(originalMessage, decryptedMessage);

        // Testing with 2048-bit RSA keys
        RSA largeRSA = new RSA(2048);
        String largeOriginalMessage = "Test with larger key";

        String largeEncryptedMessage = largeRSA.encrypt(largeOriginalMessage);
        String largeDecryptedMessage = largeRSA.decrypt(largeEncryptedMessage);

        assertEquals(largeOriginalMessage, largeDecryptedMessage);
    }

    @Test
    void testSpecialCharacters() {
        String originalMessage = "Hello, RSA! @2024#";
        String encryptedMessage = rsa.encrypt(originalMessage);
        String decryptedMessage = rsa.decrypt(encryptedMessage);
        assertEquals(originalMessage, decryptedMessage);
    }
}
