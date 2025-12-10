package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DecimalToHexadecimalTest {
    @ParameterizedTest
    @CsvSource({"0, 0", "1, 1", "10, a", "15, f", "16, 10", "255, ff", "190, be", "1800, 708"})
    void testDecToHex(int decimal, String expectedHex) {
        assertEquals(expectedHex, DecimalToHexadecimal.decToHex(decimal));
    }
}
