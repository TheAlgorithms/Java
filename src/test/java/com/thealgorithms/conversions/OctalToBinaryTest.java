package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class OctalToBinaryTest {
    @Test
    public void testConvertOctalToBinary() {
        assertEquals(101, OctalToBinary.convertOctalToBinary(5));
        assertEquals(1001, OctalToBinary.convertOctalToBinary(11));
        assertEquals(101010, OctalToBinary.convertOctalToBinary(52));
        assertEquals(110, OctalToBinary.convertOctalToBinary(6));
    }
}
