package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class XORCipherTest {

    @Test
    void xorEncryptTest() {
        // given
        String plaintext = "My t&xt th@t will be ençrypted...";
        String key = "My ç&cret key!";

        // when
        String cipherText = XORCipher.encrypt(plaintext, key);

        // then
        assertEquals("000000b7815e1752111c601f450e48211500a1c206061ca6d35212150d4429570eed", cipherText);
    }

    @Test
    void xorDecryptTest() {
        // given
        String cipherText = "000000b7815e1752111c601f450e48211500a1c206061ca6d35212150d4429570eed";
        String key = "My ç&cret key!";

        // when
        String plainText = XORCipher.decrypt(cipherText, key);

        // then
        assertEquals("My t&xt th@t will be ençrypted...", plainText);
    }
}
