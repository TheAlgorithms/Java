package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DecimalToHexaDecimalTest {

    @Test
    public void testDecimalToHexaDecimal() {
        assertEquals("000000be", DecimalToHexaDecimal.decToHex(190));
        assertEquals("00000708", DecimalToHexaDecimal.decToHex(1800));
    }
}
