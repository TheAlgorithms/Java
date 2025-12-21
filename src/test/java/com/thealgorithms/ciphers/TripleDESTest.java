package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TripleDESTest {

    TripleDES tripleDES;

    @BeforeEach
    public void setUp() {
        // Three 64-bit binary keys for 3DES
        String key1 = "0000111000110010100100100011001011101010011011010000110101110011";
        String key2 = "1111000011001100101010100101010100001111001111000101010010101001";
        String key3 = "0011001101010101100110011101110111100000111100001010000001010000";
        tripleDES = new TripleDES(key1, key2, key3);
    }

    @Test
    void testEncryptDecrypt() {
        // given
        String plainText = "Hello, 3DES!";

        // when
        String cipherText = tripleDES.encrypt(plainText);
        String decryptedText = tripleDES.decrypt(cipherText);

        // then
        assertEquals(plainText, decryptedText);
    }

    @Test
    void testLongTextEncryptDecrypt() {
        // given
        String plainText = "Triple Data Encryption Standard (3DES) is a symmetric-key block cipher that applies the DES algorithm three times to each data block.";

        // when
        String cipherText = tripleDES.encrypt(plainText);
        String decryptedText = tripleDES.decrypt(cipherText);

        // then
        assertEquals(plainText, decryptedText);
    }

    @Test
    void testByteEncryptDecrypt() {
        // given
        byte[] data = "Test byte array encryption".getBytes();

        // when
        String cipherText = tripleDES.encrypt(data);
        byte[] decryptedData = tripleDES.decryptToBytes(cipherText);

        // then
        assertArrayEquals(data, decryptedData);
    }
}