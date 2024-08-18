package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DecimalToAnyBaseTest {

    @ParameterizedTest
    @CsvSource({"0, 2, 0", "0, 16, 0", "0, 36, 0", "10, 2, 1010", "255, 16, FF", "100, 8, 144", "42, 2, 101010", "1234, 16, 4D2", "1234, 36, YA"})
    void testConvertToAnyBase(int decimal, int base, String expected) {
        assertEquals(expected, DecimalToAnyBase.convertToAnyBase(decimal, base));
    }

    @Test
    void testBaseOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> DecimalToAnyBase.convertToAnyBase(10, 1));
        assertThrows(IllegalArgumentException.class, () -> DecimalToAnyBase.convertToAnyBase(10, 37));
    }
}
