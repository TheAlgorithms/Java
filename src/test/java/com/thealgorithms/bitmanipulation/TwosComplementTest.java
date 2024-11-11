package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test case for Highest Set Bit
 * @author Abhinay Verma(https://github.com/Monk-AbhinayVerma)
 */
public class TwosComplementTest {

    @Test
    public void testTwosComplementAllZeroes() {
        assertEquals("10000", TwosComplement.twosComplement("0000"));
        assertEquals("1000", TwosComplement.twosComplement("000"));
        assertEquals("100", TwosComplement.twosComplement("00"));
        assertEquals("10", TwosComplement.twosComplement("0"));
    }

    @Test
    public void testTwosComplementAllOnes() {
        assertEquals("00001", TwosComplement.twosComplement("11111"));
        assertEquals("0001", TwosComplement.twosComplement("1111"));
        assertEquals("001", TwosComplement.twosComplement("111"));
        assertEquals("01", TwosComplement.twosComplement("11"));
    }

    @Test
    public void testTwosComplementMixedBits() {
        assertEquals("1111", TwosComplement.twosComplement("0001")); // 1 -> 1111
        assertEquals("1001", TwosComplement.twosComplement("0111")); // 0111 -> 1001
        assertEquals("11001", TwosComplement.twosComplement("00111")); // 00111 -> 11001
        assertEquals("011", TwosComplement.twosComplement("101")); // 101 -> 011
    }

    @Test
    public void testTwosComplementSingleBit() {
        assertEquals("10", TwosComplement.twosComplement("0")); // 0 -> 10
        assertEquals("1", TwosComplement.twosComplement("1")); // 1 -> 1
    }

    @Test
    public void testTwosComplementWithLeadingZeroes() {
        assertEquals("1111", TwosComplement.twosComplement("0001")); // 0001 -> 1111
        assertEquals("101", TwosComplement.twosComplement("011")); // 011 -> 101
        assertEquals("110", TwosComplement.twosComplement("010")); // 010 -> 110
    }

    @Test
    public void testInvalidBinaryInput() {
        // Test for invalid input that contains non-binary characters.
        assertThrows(IllegalArgumentException.class, () -> TwosComplement.twosComplement("102"));
        assertThrows(IllegalArgumentException.class, () -> TwosComplement.twosComplement("abc"));
        assertThrows(IllegalArgumentException.class, () -> TwosComplement.twosComplement("10a01"));
    }

    @Test
    public void testEmptyInput() {
        // Edge case: Empty input should result in an IllegalArgumentException.
        assertThrows(IllegalArgumentException.class, () -> TwosComplement.twosComplement(""));
    }
}
