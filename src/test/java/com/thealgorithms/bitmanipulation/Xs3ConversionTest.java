package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Xs3Conversion class.
 */
public class Xs3ConversionTest {

    /**
     * Test the xs3ToBinary method with an XS-3 number.
     */
    @Test
    public void testXs3ToBinary() {
        int binary = Xs3Conversion.xs3ToBinary(0x4567);
        assertEquals(1234, binary); // XS-3 0x4567 should convert to binary 1234
    }

    /**
     * Test the binaryToXs3 method with a binary number.
     */
    @Test
    public void testBinaryToXs3() {
        int xs3 = Xs3Conversion.binaryToXs3(1234);
        assertEquals(0x4567, xs3); // Binary 1234 should convert to XS-3 0x4567
    }

    /**
     * Test the xs3ToBinary method with zero.
     */
    @Test
    public void testXs3ToBinaryZero() {
        int binary = Xs3Conversion.xs3ToBinary(0x0);
        assertEquals(0, binary); // XS-3 0x0 should convert to binary 0
    }

    /**
     * Test the binaryToXs3 method with zero.
     */
    @Test
    public void testBinaryToXs3Zero() {
        int xs3 = Xs3Conversion.binaryToXs3(0);
        assertEquals(0x0, xs3); // Binary 0 should convert to XS-3 0x0
    }

    /**
     * Test the xs3ToBinary method with a single digit XS-3 number.
     */
    @Test
    public void testXs3ToBinarySingleDigit() {
        int binary = Xs3Conversion.xs3ToBinary(0x5);
        assertEquals(2, binary); // XS-3 0x5 should convert to binary 2
    }

    /**
     * Test the binaryToXs3 method with a single digit binary number.
     */
    @Test
    public void testBinaryToXs3SingleDigit() {
        int xs3 = Xs3Conversion.binaryToXs3(2);
        assertEquals(0x5, xs3); // Binary 2 should convert to XS-3 0x5
    }
}
