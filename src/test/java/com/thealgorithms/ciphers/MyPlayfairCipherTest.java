package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyPlayfairCipherTest {
    private PlayfairCipher playfair;
    private final String keyword = "KEYWORD";

    @BeforeEach
    public void setup() {
        playfair = new PlayfairCipher(keyword);
    }

    @Test
    void shouldEncryptAndDecryptDuringSameRowDigraph() {
        String plaintext = keyword.substring(0, 2);

        String encrypted = playfair.encrypt(plaintext);
        String decrypted = playfair.decrypt(encrypted);

        assertEquals(plaintext, decrypted, "Should not decrypt to the same letters");
    }

    @Test
    void shouldPadOddLengthplaintext() {
        String plaintext = "cat";

        String encrypted = playfair.encrypt(plaintext);

        assertTrue(encrypted.length() % 2 == 0, "Should be even length");
    }
}
