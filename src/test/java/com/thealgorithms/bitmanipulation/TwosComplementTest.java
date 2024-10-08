package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test case for Highest Set Bit
 * @author Abhinay Verma(https://github.com/Monk-AbhinayVerma)
 */
public class TwosComplementTest {

    @Test
    public void testTwosComplement_AllZeroes() {
        // Test with a binary number consisting entirely of zeroes
        assertEquals("0000", TwosComplement.twosComplement("0000"));
        assertEquals("000", TwosComplement.twosComplement("000"));
        assertEquals("00", TwosComplement.twosComplement("00"));
        assertEquals("0", TwosComplement.twosComplement("0"));
    }

    @Test
    public void testTwosComplement_AllOnes() {
        // Test with a binary number consisting entirely of ones
        assertEquals("11111", TwosComplement.twosComplement("11111")); // Extra '1' due to carry
        assertEquals("1111", TwosComplement.twosComplement("1111"));
        assertEquals("111", TwosComplement.twosComplement("111"));
        assertEquals("11", TwosComplement.twosComplement("11"));
    }

    @Test
    public void testTwosComplement_MixedBits() {
        // Test with binary numbers with mixed bits
        assertEquals("1110", TwosComplement.twosComplement("0001")); // 1's complement: 1110, then add 1: 1111
        assertEquals("1000", TwosComplement.twosComplement("0111")); // 1's complement: 1000
        assertEquals("11001", TwosComplement.twosComplement("00111")); // 1's complement: 11000, add 1: 11001
        assertEquals("011", TwosComplement.twosComplement("101")); // 1's complement: 010, add 1: 011
    }

    @Test
    public void testTwosComplement_SingleBit() {
        // Test with single bit
        assertEquals("1", TwosComplement.twosComplement("0"));
        assertEquals("11", TwosComplement.twosComplement("1")); // 1's complement: 0, add 1: 1, carry generates extra
                                                                // '1'
    }

    @Test
    public void testTwosComplement_EmptyString() {
        // Test with an empty string
        assertEquals("", TwosComplement.twosComplement(""));
    }

    @Test
    public void testTwosComplement_WithLeadingZeroes() {
        // Test with leading zeroes in the input
        assertEquals("1111", TwosComplement.twosComplement("0001"));
        assertEquals("100", TwosComplement.twosComplement("011"));
        assertEquals("101", TwosComplement.twosComplement("010"));
    }
}
