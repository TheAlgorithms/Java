package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OctalToBinaryTest {
    @Test
    public void testConvertOctalToBinary() {
        assertEquals(101, OctalToBinary.convertOctalToBinary(5));
        assertEquals(1001, OctalToBinary.convertOctalToBinary(11));
        assertEquals(101010, OctalToBinary.convertOctalToBinary(52));
        assertEquals(110, OctalToBinary.convertOctalToBinary(6));
    }

    @Test
    public void testConvertOctalToBinarySingleDigit() {
        assertEquals(0, OctalToBinary.convertOctalToBinary(0));
        assertEquals(1, OctalToBinary.convertOctalToBinary(1));
        assertEquals(111, OctalToBinary.convertOctalToBinary(7));
    }

    @Test
    public void testConvertOctalToBinaryMultipleDigits() {
        assertEquals(100110111, OctalToBinary.convertOctalToBinary(467));
        assertEquals(111101, OctalToBinary.convertOctalToBinary(75));
        assertEquals(111100101, OctalToBinary.convertOctalToBinary(745));
    }

    @Test
    public void testConvertOctalToBinaryWithZeroPadding() {
        assertEquals(100001010, OctalToBinary.convertOctalToBinary(412));
        assertEquals(101101110, OctalToBinary.convertOctalToBinary(556));
    }
}
