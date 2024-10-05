package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test case for Lowest Set Bit
 * @author Prayas Kumar (https://github.com/prayas7102)
 */

public class LowestSetBitTest {

    @Test
    void testLowestSetBitWithPositiveNumber() {
        // Test with a general positive number
        assertEquals(2, LowestSetBit.isolateLowestSetBit(18)); // 18 in binary: 10010, lowest bit is 2
    }

    @Test
    void testLowestSetBitWithZero() {
        // Test with zero (edge case, no set bits)
        assertEquals(0, LowestSetBit.isolateLowestSetBit(0)); // 0 has no set bits, result should be 0
    }

    @Test
    void testLowestSetBitWithOne() {
        // Test with number 1 (lowest set bit is 1 itself)
        assertEquals(1, LowestSetBit.isolateLowestSetBit(1)); // 1 in binary: 0001, lowest bit is 1
    }

    @Test
    void testLowestSetBitWithPowerOfTwo() {
        // Test with a power of two (only one set bit)
        assertEquals(16, LowestSetBit.isolateLowestSetBit(16)); // 16 in binary: 10000, lowest bit is 16
    }

    @Test
    void testLowestSetBitWithAllBitsSet() {
        // Test with a number with multiple set bits (like 7)
        assertEquals(1, LowestSetBit.isolateLowestSetBit(7)); // 7 in binary: 111, lowest bit is 1
    }

    @Test
    void testLowestSetBitWithNegativeNumber() {
        // Test with a negative number (-1 in two's complement has all bits set)
        assertEquals(1, LowestSetBit.isolateLowestSetBit(-1)); // -1 in two's complement is all 1s, lowest bit is 1
    }

    @Test
    void testLowestSetBitWithLargeNumber() {
        // Test with a large number
        assertEquals(64, LowestSetBit.isolateLowestSetBit(448)); // 448 in binary: 111000000, lowest bit is 64
    }

    @Test
    void testClearLowestSetBitFor18() {
        // n = 18 (binary: 10010), expected result = 16 (binary: 10000)
        assertEquals(16, LowestSetBit.clearLowestSetBit(18));
    }

    @Test
    void testClearLowestSetBitFor10() {
        // n = 10 (binary: 1010), expected result = 8 (binary: 1000)
        assertEquals(8, LowestSetBit.clearLowestSetBit(10));
    }

    @Test
    void testClearLowestSetBitFor7() {
        // n = 7 (binary: 0111), expected result = 6 (binary: 0110)
        assertEquals(6, LowestSetBit.clearLowestSetBit(7));
    }

    @Test
    void testClearLowestSetBitFor0() {
        // n = 0 (binary: 0000), no set bits to clear, expected result = 0
        assertEquals(0, LowestSetBit.clearLowestSetBit(0));
    }

    @Test
    void testClearLowestSetBitForNegativeNumber() {
        // Test negative number to see how it behaves with two's complement
        // n = -1 (binary: all 1s in two's complement), expected result = -2 (clearing lowest set bit)
        assertEquals(-2, LowestSetBit.clearLowestSetBit(-1));
    }
}
