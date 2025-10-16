package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

public class AESTest {
    @Test
    void testScheduleCore() {
        BigInteger input = new BigInteger("1a2b3c4d", 16);
        int rconCounter = 1;

        BigInteger expected = new BigInteger("f0ebe3a2", 16);

        BigInteger result = AES.scheduleCore(input, rconCounter);
        assertEquals(expected, result, "Should return " + expected);
    }
    
    @Test
    void testKeyExpansion() {
        BigInteger initialKey = new BigInteger("000102030405060708090a0b0c0d0e0f", 16);

        BigInteger[] roundKeys = AES.keyExpansion(initialKey);

        assertEquals(initialKey, roundKeys[0], "First round key should match initial key");

        for (int i = 1; i < roundKeys.length; i++) {
            assertNotEquals(BigInteger.ZERO, roundKeys[i], "Round key " + i + " should not be zero");
        }
    }

    @Test
    void testSplitBlockIntoCells() {
        StringBuilder binary = new StringBuilder();

        for (int i = 1; i <= 16; i++) {
            String byteStr = String.format("%8s", Integer.toBinaryString(i)).replace(" ", "0");
            binary.append(byteStr);
        }

        BigInteger block = new BigInteger(binary.toString(), 2);

        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        int[] result = AES.splitBlockIntoCells(block);

        assertArrayEquals(expected, result);
    }

    @Test
    void testMergeCellsIntoBlocks() {
        int[] cells = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        
        StringBuilder expectedBinary = new StringBuilder();

        for (int cell : cells) {
            expectedBinary.append(String.format("%8s", Integer.toBinaryString(cell)).replace(" ", "0"));
        }

        BigInteger expected = new BigInteger(expectedBinary.toString(), 2);

        BigInteger result = AES.mergeCellsIntoBlock(cells);

        assertEquals(expected, result, "Merged block from cells should match expected BigInteger");
    }

    @Test
    void testAddRoundKey() {
        BigInteger ciphertext = new BigInteger("a1b2c3d5e5f60718", 16);
        BigInteger key = new BigInteger("0f0e0d0c0b0a0909", 16);

        BigInteger expected = new BigInteger("12591166093633785361");

        BigInteger result = AES.addRoundKey(ciphertext, key);

        assertEquals(expected, result, "Should return XOR of ciphertext and key");
    }

    @Test
    void testSubBytes() {
        BigInteger input = new BigInteger("000102030405060708090a0b0c0d0e0f", 16);

        BigInteger expected = new BigInteger("132239839819997069106320266673331350390");

        BigInteger result = AES.subBytes(input);

        assertEquals(expected, result, "Should match correct S-Box substituted block");
    }

    @Test
    void testSubBytesDec() {
        BigInteger originalBlock = new BigInteger("3243f6a8885a308d313198a2e0370734", 16);

        BigInteger afterSubBytes = AES.subBytes(originalBlock);
        BigInteger afterInverse = AES.subBytesDec(afterSubBytes);

        assertEquals(originalBlock, afterInverse, "subBytesDec should reverse subBytes");
    }

    @Test
    void testShiftRows() {
        BigInteger input = new BigInteger("00010203101112132021222330313233", 16);

        BigInteger expected = new BigInteger("00112233102132032031021330011223", 16);

        BigInteger result = AES.shiftRows(input);

        assertEquals(expected, result, "Should shift rows correctly");
    }

    @Test
    void testShiftRowsDec() {
        BigInteger originalBlock = new BigInteger("00010203101112132021222330313233", 16);

        BigInteger shifted = AES.shiftRows(originalBlock);
        BigInteger unshifted = AES.shiftRowsDec(shifted);

        assertEquals(originalBlock, unshifted, "shiftRowsDec should reverse shiftRows");
    }

    @Test
    void testMixColumns() {
        BigInteger input = new BigInteger("d4bf5d30e0b452aeb84111f11e2798e5", 16);

        BigInteger expected = new BigInteger("046681e5e0cb199a48f8d37a2806264c", 16);

        BigInteger result = AES.mixColumns(input);

        assertEquals(expected, result, "MixColumns output did not match expected value");
    }

    @Test
    void testMixColumnsDec() {
        BigInteger input = new BigInteger("000102030405060708090a0b0c0d0e0f", 16);

        BigInteger mixed = AES.mixColumns(input);
        BigInteger unmixed = AES.mixColumnsDec(mixed);

        assertEquals(input, unmixed, "mixColumnsDec should reverse mixColumns");
    }

    @Test
    void testEncrypt() {
        BigInteger plainText = new BigInteger("00112233445566778899aabbccddeeff", 16);
        BigInteger key = new BigInteger("000102030405060708090a0b0c0d0e0f", 16);

        BigInteger expectedCipherText = new BigInteger("69c4e0d86a7b0430d8cdb78070b4c55a", 16);

        BigInteger actualCipherText = AES.encrypt(plainText, key);

        assertEquals(expectedCipherText, actualCipherText, "Encrypt output should match known ciphertext");
    }

    @Test
    void testDecrypt() {
        BigInteger plaintext = new BigInteger("00112233445566778899aabbccddeeff", 16);
        BigInteger key = new BigInteger("000102030405060708090a0b0c0d0e0f", 16);

        BigInteger ciphertext = AES.encrypt(plaintext, key);
        BigInteger decrypted = AES.decrypt(ciphertext, key);

        assertEquals(plaintext, decrypted, "Decrypt should reverse Encrypt");
    }
}
