package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void shouldPadOddLengthPlaintext() {
        String plaintext = "cat";

        String encrypted = playfair.encrypt(plaintext);

        assertEquals(0, encrypted.length() % 2, "Should be even length");
    }
}
