package com.thealgorithms.ciphers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VigenereTest {

    VigenereEncryptor vigenereE = new VigenereEncryptor();
    VigenereDecryptor vigenereD = new VigenereDecryptor();

    @Test
    void vigenereEncryptTest() {
        // given
        String text = "Hello World!";
        String key = "suchsecret";

        // when
        String cipherText = vigenereE.process(text, key);

        // then
        assertEquals("Zynsg Yfvev!", cipherText);
    }

    @Test
    void vigenereDecryptTest() {
        // given
        String encryptedText = "Zynsg Yfvev!";
        String key = "suchsecret";

        // when
        String decryptedText = vigenereD.process(encryptedText, key);

        // then
        assertEquals("Hello World!", decryptedText);
    }

}
