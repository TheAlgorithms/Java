package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AffineCipherTest {

    @Test
    public void testEncryptMessage() {
        String plaintext = "AFFINE CIPHER";
        char[] msg = plaintext.toCharArray();
        String expectedCiphertext = "UBBAHK CAPJKX"; // Expected ciphertext after encryption

        String actualCiphertext = AffineCipher.encryptMessage(msg);
        assertEquals(expectedCiphertext, actualCiphertext, "The encryption result should match the expected ciphertext.");
    }

    @Test
    public void testEncryptDecrypt() {
        String plaintext = "HELLO WORLD";
        char[] msg = plaintext.toCharArray();

        String ciphertext = AffineCipher.encryptMessage(msg);
        String decryptedText = AffineCipher.decryptCipher(ciphertext);

        assertEquals(plaintext, decryptedText, "Decrypted text should match the original plaintext.");
    }

    @Test
    public void testSpacesHandledInEncryption() {
        String plaintext = "HELLO WORLD";
        char[] msg = plaintext.toCharArray();
        String expectedCiphertext = "JKZZY EYXZT";

        String actualCiphertext = AffineCipher.encryptMessage(msg);
        assertEquals(expectedCiphertext, actualCiphertext, "The encryption should handle spaces correctly.");
    }
}
