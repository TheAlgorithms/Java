package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DecimalToHexadecimalTest {
    @ParameterizedTest
    @CsvSource({"0, 00000000", "1, 00000001", "10, 0000000a", "15, 0000000f", "16, 00000010", "255, 000000ff", "190, 000000be", "1800, 00000708"})
    void testDecToHex(int decimal, String expectedHex) {
        assertEquals(expectedHex, DecimalToHexadecimal.decToHex(decimal));
    }
}
