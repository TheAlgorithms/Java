package com.thealgorithms.ciphers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RSATest {

    RSA rsa = new RSA(1024);

    @Test
    void testRSA() {
        // given
        String textToEncrypt = "Such secure";

        // when
        String cipherText = rsa.encrypt(textToEncrypt);
        String decryptedText = rsa.decrypt(cipherText);

        // then
        assertEquals("Such secure", decryptedText);
    }

}
