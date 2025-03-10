package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SimpleSubCipherTest {

    SimpleSubCipher simpleSubCipher = new SimpleSubCipher();

    @Test
    void simpleSubCipherEncryptTest() {
        // given
        String text = "defend the east wall of the castle";
        String cipherSmall = "phqgiumeaylnofdxjkrcvstzwb";

        // when
        String cipherText = simpleSubCipher.encode(text, cipherSmall);

        // then
        assertEquals("giuifg cei iprc tpnn du cei qprcni", cipherText);
    }

    @Test
    void simpleSubCipherDecryptTest() {
        // given
        String encryptedText = "giuifg cei iprc tpnn du cei qprcni";
        String cipherSmall = "phqgiumeaylnofdxjkrcvstzwb";

        // when
        String decryptedText = simpleSubCipher.decode(encryptedText, cipherSmall);

        // then
        assertEquals("defend the east wall of the castle", decryptedText);
    }
}
