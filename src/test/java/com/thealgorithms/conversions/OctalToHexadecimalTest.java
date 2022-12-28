package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class OctalToHexadecimalTest {

    @Test
    public void testOctalToHexadecimal() {
        assertEquals("1EA", OctalToHexadecimal.decimalToHex(OctalToHexadecimal.octToDec("752")));
        assertEquals("15E", OctalToHexadecimal.decimalToHex(OctalToHexadecimal.octToDec("536")));
    }
}
