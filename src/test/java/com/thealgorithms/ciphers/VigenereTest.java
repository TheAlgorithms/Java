package com.thealgorithms.ciphers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VigenereTest {

    Vigenere vigenere = new Vigenere();

    @Test
    void vigenereEncryptTest() {
        // given
        String text = "Hello World!";
        String key = "suchsecret";

        // when
        String cipherText = vigenere.encrypt(text, key);

        // then
        assertEquals("Zynsg Yfvev!", cipherText);
    }

    @Test
    void vigenereDecryptTest() {
        // given
        String encryptedText = "Zynsg Yfvev!";
        String key = "suchsecret";

        // when
        String decryptedText = vigenere.decrypt(encryptedText, key);

        // then
        assertEquals("Hello World!", decryptedText);
    }

}
