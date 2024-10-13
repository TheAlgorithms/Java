package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BCDConverterTest {

    @Test
    public void testDecimalToBCD() {
        assertEquals("00010010", BCDConverter.decimalToBCD(12));
        assertEquals("001001000011", BCDConverter.decimalToBCD(243));
    }

    @Test
    public void testBCDToDecimal() {
        assertEquals(12, BCDConverter.bcdToDecimal("00010010"));
        assertEquals(243, BCDConverter.bcdToDecimal("001001000011"));
    }
}
