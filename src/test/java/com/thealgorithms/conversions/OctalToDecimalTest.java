package com.thealgorithms.conversions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OctalToDecimalTest {

    @ParameterizedTest
    @CsvSource({"10, 8", "7, 7", "77, 63", "123, 83", "0, 0", "777, 511", "2671, 1465", "275, 189"})
    void testConvertOctalToDecimal(String inputOctal, int expectedDecimal) {
        Assertions.assertEquals(expectedDecimal, OctalToDecimal.convertOctalToDecimal(inputOctal));
    }

    @ParameterizedTest
    @CsvSource({"'', Input cannot be null or empty", "'8', Incorrect input: Expecting an octal number (digits 0-7)", "'19', Incorrect input: Expecting an octal number (digits 0-7)"})
    void testIncorrectInput(String inputOctal, String expectedMessage) {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> OctalToDecimal.convertOctalToDecimal(inputOctal));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }
}
