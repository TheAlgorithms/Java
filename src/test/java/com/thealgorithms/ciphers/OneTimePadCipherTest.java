package com.thealgorithms.ciphers;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;
import org.junit.Test;

public class OneTimePadCipherTest {

    @Test
    public void encryptDecryptWorks() {
        String original = "OTP Test ";
        byte[] plaintext = original.getBytes(StandardCharsets.UTF_8);
        byte[] key = OneTimePadCipher.generateKey(plaintext.length);

        byte[] encrypted = OneTimePadCipher.encrypt(plaintext, key);
        byte[] decrypted = OneTimePadCipher.decrypt(encrypted, key);

        assertEquals(original, new String(decrypted, StandardCharsets.UTF_8));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIfDifferentLength() {
        byte[] plaintext = "Hi".getBytes(StandardCharsets.UTF_8);
        byte[] key = new byte[] { 1, 2, 3 }; // wrong length

        OneTimePadCipher.encrypt(plaintext, key);
    }
}
