package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CaesarTest {

    Caesar caesar = new Caesar();

    @Test
    void caesarEncryptTest() {
        // given
        String textToEncrypt = "Encrypt this text";

        // when
        String cipherText = caesar.encode(textToEncrypt, 5);

        // then
        assertEquals("Jshwduy ymnx yjcy", cipherText);
    }

    @Test
    void caesarDecryptTest() {
        // given
        String encryptedText = "Jshwduy ymnx yjcy";

        // when
        String cipherText = caesar.decode(encryptedText, 5);

        // then
        assertEquals("Encrypt this text", cipherText);
    }

    @Test
    void caesarBruteForce() {
        // given
        String encryptedText = "Jshwduy ymnx yjcy";

        // when
        String[] allPossibleAnswers = caesar.bruteforce(encryptedText);

        assertEquals(27, allPossibleAnswers.length);
        assertEquals("Encrypt this text", allPossibleAnswers[5]);
    }
}
