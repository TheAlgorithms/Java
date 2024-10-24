package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AnytoAnyTest {

    @Test
    void testValidConversions() {
        assertEquals(101, AnytoAny.convertBase(5, 10, 2), "Decimal 5 should convert to binary 101");
        assertEquals(2, AnytoAny.convertBase(2, 2, 10), "Binary 10 should convert to decimal 2");
        assertEquals(6, AnytoAny.convertBase(110, 2, 8), "Binary 110 should convert to octal 6");
        assertEquals(111, AnytoAny.convertBase(7, 10, 2), "Decimal 7 should convert to binary 111");
    }

    @Test
    void testDecimalToBinary() {
        assertEquals(1101, AnytoAny.convertBase(13, 10, 2), "Decimal 13 should convert to binary 1101");
        assertEquals(0, AnytoAny.convertBase(0, 10, 2), "Decimal 0 should convert to binary 0");
    }

    @Test
    void testBinaryToDecimal() {
        assertEquals(13, AnytoAny.convertBase(1101, 2, 10), "Binary 1101 should convert to decimal 13");
        assertEquals(0, AnytoAny.convertBase(0, 2, 10), "Binary 0 should convert to decimal 0");
    }

    @Test
    void testOctalToDecimal() {
        assertEquals(8, AnytoAny.convertBase(10, 8, 10), "Octal 10 should convert to decimal 8");
        assertEquals(65, AnytoAny.convertBase(101, 8, 10), "Octal 101 should convert to decimal 65");
    }

    @Test
    void testInvalidBases() {
        assertThrows(IllegalArgumentException.class, () -> AnytoAny.convertBase(5, 1, 10), "Source base less than 2 should throw IllegalArgumentException");

        assertThrows(IllegalArgumentException.class, () -> AnytoAny.convertBase(5, 10, 11), "Destination base greater than 10 should throw IllegalArgumentException");
    }

    @Test
    void testLargeNumberConversion() {
        assertEquals(1111101000, AnytoAny.convertBase(1000, 10, 2), "Decimal 1000 should convert to binary 1111101000");
        assertEquals(1750, AnytoAny.convertBase(1000, 10, 8), "Decimal 1000 should convert to octal 1750");
    }
}
