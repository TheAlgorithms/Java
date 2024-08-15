package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DecimalToOctalTest {
    @ParameterizedTest
    @CsvSource({"0, 0", "7, 7", "8, 10", "10, 12", "64, 100", "83, 123", "7026, 15562"})
    void testConvertToOctal(int decimal, int expectedOctal) {
        assertEquals(expectedOctal, DecimalToOctal.convertToOctal(decimal));
    }

    @Test
    void testConvertToOctalNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> DecimalToOctal.convertToOctal(-10));
    }
}
