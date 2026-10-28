package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void caesarEncryptWithNegativeShiftTest() {
        // a shift of -1 must wrap 'A' backwards to 'Z', like a shift of +25 would
        assertEquals("Z", caesar.encode("A", -1));
        assertEquals("z", caesar.encode("a", -1));
        assertEquals("EBIIL", caesar.encode("HELLO", -3));
    }

    @Test
    void caesarDecryptWithNegativeShiftTest() {
        assertEquals("A", caesar.decode("Z", -1));
        assertEquals("HELLO", caesar.decode("EBIIL", -3));
    }

    @Test
    void caesarNegativeShiftRoundTripTest() {
        // encode followed by decode with the same shift must return the original text
        for (int shift : new int[] {-1, -5, -25, -26, -27, -52}) {
            String message = "The quick brown Fox";
            assertEquals(message, caesar.decode(caesar.encode(message, shift), shift));
        }
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
