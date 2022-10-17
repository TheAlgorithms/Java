package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BlowfishTest {

    Blowfish blowfish = new Blowfish();

    @Test
    void testEncrypt() {
        //given
        String plainText = "123456abcd132536";
        String key = "aabb09182736ccdd";
        String expectedOutput = "d748ec383d3405f7";

        //when
        String cipherText = blowfish.encrypt(plainText, key);

        //then
        assertEquals(expectedOutput, cipherText);
    }

    @Test
    void testDecrypt() {
        //given
        String cipherText = "d748ec383d3405f7";
        String key = "aabb09182736ccdd";
        String expectedOutput = "123456abcd132536";

        //when
        String plainText = blowfish.decrypt(cipherText, key);

        //then
        assertEquals(expectedOutput, plainText);
    }
}
