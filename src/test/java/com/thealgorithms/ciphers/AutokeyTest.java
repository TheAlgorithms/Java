package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AutokeyCipherTest {

    Autokey autokeyCipher = new Autokey();

    @Test
    void autokeyEncryptTest() {
        // given
        String plaintext = "MEET AT DAWN";
        String keyword = "QUEEN";

        // when
        String cipherText = autokeyCipher.encrypt(plaintext, keyword);

        // then
        assertEquals("CYIXNFHEPN", cipherText);
    }

    @Test
    void autokeyDecryptTest() {
        // given
        String ciphertext = "CYIX NF HEPN";
        String keyword = "QUEEN";

        // when
        String plainText = autokeyCipher.decrypt(ciphertext, keyword);

        // then
        assertEquals("MEETATDAWN", plainText);
    }
}
