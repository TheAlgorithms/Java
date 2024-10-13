package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BcdConversionTest {

    @Test
    public void testBcdToBinary() {
        int binary = BcdConversion.bcdToBinary(0x1234);
        assertEquals(1234, binary); // BCD 0x1234 should convert to binary 1234
    }

    @Test
    public void testBinaryToBcd() {
        int bcd = BcdConversion.binaryToBcd(1234);
        assertEquals(0x1234, bcd); // Binary 1234 should convert to BCD 0x1234
    }

    @Test
    public void testBcdToBinaryZero() {
        int binary = BcdConversion.bcdToBinary(0x0);
        assertEquals(0, binary); // BCD 0x0 should convert to binary 0
    }

    @Test
    public void testBinaryToBcdZero() {
        int bcd = BcdConversion.binaryToBcd(0);
        assertEquals(0x0, bcd); // Binary 0 should convert to BCD 0x0
    }

    @Test
    public void testBcdToBinarySingleDigit() {
        int binary = BcdConversion.bcdToBinary(0x7);
        assertEquals(7, binary); // BCD 0x7 should convert to binary 7
    }

    @Test
    public void testBinaryToBcdSingleDigit() {
        int bcd = BcdConversion.binaryToBcd(7);
        assertEquals(0x7, bcd); // Binary 7 should convert to BCD 0x7
    }

    @Test
    public void testBcdToBinaryMaxValue() {
        int binary = BcdConversion.bcdToBinary(0x9999);
        assertEquals(9999, binary); // BCD 0x9999 should convert to binary 9999
    }

    @Test
    public void testBinaryToBcdMaxValue() {
        int bcd = BcdConversion.binaryToBcd(9999);
        assertEquals(0x9999, bcd); // Binary 9999 should convert to BCD 0x9999
    }

    @Test
    public void testBcdToBinaryInvalidHighDigit() {
        // Testing invalid BCD input where one of the digits is > 9
        assertThrows(IllegalArgumentException.class, () -> {
            BcdConversion.bcdToBinary(0x123A); // Invalid BCD, 'A' is not a valid digit
        });
    }

    @Test
    public void testBinaryToBcdInvalidValue() {
        // Testing conversion for numbers greater than 9999, which cannot be represented in BCD
        assertThrows(IllegalArgumentException.class, () -> {
            BcdConversion.binaryToBcd(10000); // 10000 is too large for BCD representation
        });
    }

    @Test
    public void testBcdToBinaryLeadingZeroes() {
        int binary = BcdConversion.bcdToBinary(0x0234);
        assertEquals(234, binary); // BCD 0x0234 should convert to binary 234, ignoring leading zero
    }

    @Test
    public void testBinaryToBcdLeadingZeroes() {
        int bcd = BcdConversion.binaryToBcd(234);
        assertEquals(0x0234, bcd); // Binary 234 should convert to BCD 0x0234
    }
}
