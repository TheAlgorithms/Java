package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HexToOctTest {
    @Test
    public void testHexToDecimal() {
        assertEquals(255, HexToOct.hexToDecimal("FF"));
        assertEquals(16, HexToOct.hexToDecimal("10"));
        assertEquals(0, HexToOct.hexToDecimal("0"));
        assertEquals(4095, HexToOct.hexToDecimal("FFF"));
    }

    @Test
    public void testDecimalToOctal() {
        assertEquals(110, HexToOct.decimalToOctal(HexToOct.hexToDecimal("48")));
        assertEquals(255, HexToOct.decimalToOctal(HexToOct.hexToDecimal("AD")));
        assertEquals(377, HexToOct.decimalToOctal(255));
        assertEquals(20, HexToOct.decimalToOctal(16));
        assertEquals(0, HexToOct.decimalToOctal(0));
        assertEquals(7777, HexToOct.decimalToOctal(4095));
    }

    @Test
    public void testHexToOctal() {
        assertEquals(377, HexToOct.hexToOctal("FF"));
        assertEquals(20, HexToOct.hexToOctal("10"));
        assertEquals(0, HexToOct.hexToOctal("0"));
        assertEquals(7777, HexToOct.hexToOctal("FFF"));
    }
}
