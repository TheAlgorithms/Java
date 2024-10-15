package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BcdConversionTest {

    @Test
    public void testBcdToDecimal() {
        int decimal = BcdConversion.bcdToDecimal(0x1234);
        assertEquals(1234, decimal); // BCD 0x1234 should convert to decimal 1234
    }

    @Test
    public void testDecimalToBcd() {
        int bcd = BcdConversion.decimalToBcd(1234);
        assertEquals(0x1234, bcd); // Decimal 1234 should convert to BCD 0x1234
    }

    @Test
    public void testBcdToDecimalZero() {
        int decimal = BcdConversion.bcdToDecimal(0x0);
        assertEquals(0, decimal); // BCD 0x0 should convert to decimal 0
    }

    @Test
    public void testDecimalToBcdZero() {
        int bcd = BcdConversion.decimalToBcd(0);
        assertEquals(0x0, bcd); // Decimal 0 should convert to BCD 0x0
    }

    @Test
    public void testBcdToDecimalSingleDigit() {
        int decimal = BcdConversion.bcdToDecimal(0x7);
        assertEquals(7, decimal); // BCD 0x7 should convert to decimal 7
    }

    @Test
    public void testDecimalToBcdSingleDigit() {
        int bcd = BcdConversion.decimalToBcd(7);
        assertEquals(0x7, bcd); // Decimal 7 should convert to BCD 0x7
    }

    @Test
    public void testBcdToDecimalMaxValue() {
        int decimal = BcdConversion.bcdToDecimal(0x9999);
        assertEquals(9999, decimal); // BCD 0x9999 should convert to decimal 9999
    }

    @Test
    public void testDecimalToBcdMaxValue() {
        int bcd = BcdConversion.decimalToBcd(9999);
        assertEquals(0x9999, bcd); // Decimal 9999 should convert to BCD 0x9999
    }

    @Test
    public void testBcdToDecimalInvalidHighDigit() {
        // Testing invalid BCD input where one of the digits is > 9
        assertThrows(IllegalArgumentException.class, () -> {
            BcdConversion.bcdToDecimal(0x123A); // Invalid BCD, 'A' is not a valid digit
        });
    }

    @Test
    public void testDecimalToBcdInvalidValue() {
        // Testing conversion for numbers greater than 9999, which cannot be represented in BCD
        assertThrows(IllegalArgumentException.class, () -> {
            BcdConversion.decimalToBcd(10000); // 10000 is too large for BCD representation
        });
    }

    @Test
    public void testBcdToDecimalLeadingZeroes() {
        int decimal = BcdConversion.bcdToDecimal(0x0234);
        assertEquals(234, decimal); // BCD 0x0234 should convert to decimal 234, ignoring leading zero
    }

    @Test
    public void testDecimalToBcdLeadingZeroes() {
        int bcd = BcdConversion.decimalToBcd(234);
        assertEquals(0x0234, bcd); // Decimal 234 should convert to BCD 0x0234
    }
}
