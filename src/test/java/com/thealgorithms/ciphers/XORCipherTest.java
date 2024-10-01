package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class XORCipherTest {

    @Test
    void shouldEncryptAndDecryptTest() {
        // given
        String plaintext = "My t&xt th@t will be ençrypted...";
        String key = "My ç&cret key!";
        // when
        String cipherText = XORCipher.encrypt(plaintext, key);
        // then
        assertEquals(XORCipher.decrypt(cipherText, key), plaintext);
    }
}
