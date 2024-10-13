package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the BcdConversion class.
 */
public class BcdConversionTest {

    /**
     * Test the bcdToBinary method with a BCD number.
     */
    @Test
    public void testBcdToBinary() {
        int binary = BcdConversion.bcdToBinary(0x1234);
        assertEquals(1234, binary); // BCD 0x1234 should convert to binary 1234
    }

    /**
     * Test the binaryToBcd method with a binary number.
     */
    @Test
    public void testBinaryToBcd() {
        int bcd = BcdConversion.binaryToBcd(1234);
        assertEquals(0x1234, bcd); // Binary 1234 should convert to BCD 0x1234
    }

    /**
     * Test the bcdToBinary method with zero.
     */
    @Test
    public void testBcdToBinaryZero() {
        int binary = BcdConversion.bcdToBinary(0x0);
        assertEquals(0, binary); // BCD 0x0 should convert to binary 0
    }

    /**
     * Test the binaryToBcd method with zero.
     */
    @Test
    public void testBinaryToBcdZero() {
        int bcd = BcdConversion.binaryToBcd(0);
        assertEquals(0x0, bcd); // Binary 0 should convert to BCD 0x0
    }

    /**
     * Test the bcdToBinary method with a single digit BCD number.
     */
    @Test
    public void testBcdToBinarySingleDigit() {
        int binary = BcdConversion.bcdToBinary(0x7);
        assertEquals(7, binary); // BCD 0x7 should convert to binary 7
    }

    /**
     * Test the binaryToBcd method with a single digit binary number.
     */
    @Test
    public void testBinaryToBcdSingleDigit() {
        int bcd = BcdConversion.binaryToBcd(7);
        assertEquals(0x7, bcd); // Binary 7 should convert to BCD 0x7
    }
}
