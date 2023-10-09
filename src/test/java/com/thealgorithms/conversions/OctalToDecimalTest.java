package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class OctalToDecimalTest {

    @Test
    public void testOctalToDecimal() {
        assertEquals(1465, OctalToDecimal.convertOctalToDecimal("2671"));
        assertEquals(189, OctalToDecimal.convertOctalToDecimal("275"));
    }
}
