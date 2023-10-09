package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HexaDecimalToBinaryTest {

    @Test
    public void testHexaDecimalToBinary() {
        HexaDecimalToBinary hexaDecimalToBinary = new HexaDecimalToBinary();
        assertEquals("1111111111111111111111111111111", hexaDecimalToBinary.convert("7fffffff"));
        assertEquals("101010111100110111101111", hexaDecimalToBinary.convert("abcdef"));
    }
}
