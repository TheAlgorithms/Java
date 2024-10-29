package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class HexaDecimalToDecimalTest {

    @ParameterizedTest
    @CsvSource({
        "A1, 161", // Simple case with two characters
        "1AC, 428", // Mixed-case input
        "0, 0", // Single zero
        "F, 15", // Single digit
        "10, 16", // Power of 16
        "FFFF, 65535", // Max 4-character hex
        "7FFFFFFF, 2147483647" // Max positive int value
    })
    public void
    testValidHexaToDecimal(String hexInput, int expectedDecimal) {
        assertEquals(expectedDecimal, HexaDecimalToDecimal.getHexaToDec(hexInput));
    }

    @ParameterizedTest
    @CsvSource({
        "G", // Invalid character
        "1Z", // Mixed invalid input
        "123G", // Valid prefix with invalid character
        "#$%" // Non-hexadecimal symbols
    })
    public void
    testInvalidHexaToDecimal(String invalidHex) {
        assertThrows(IllegalArgumentException.class, () -> HexaDecimalToDecimal.getHexaToDec(invalidHex));
    }
}
