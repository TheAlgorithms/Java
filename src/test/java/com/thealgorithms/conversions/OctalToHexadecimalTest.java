package com.thealgorithms.conversions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OctalToHexadecimalTest {

    @ParameterizedTest
    @CsvSource({"0, 0", "7, 7", "10, 8", "17, F", "20, 10", "777, 1FF", "1234, 29C", "752, 1EA", "536, 15E"})
    void testCorrectInputs(String inputOctal, String expectedHex) {
        int decimal = OctalToHexadecimal.octalToDecimal(inputOctal);
        String hex = OctalToHexadecimal.decimalToHexadecimal(decimal);
        Assertions.assertEquals(expectedHex, hex);
    }

    @ParameterizedTest
    @CsvSource({"'', Input cannot be null or empty", "'8', Incorrect octal digit: 8", "'19', Incorrect octal digit: 9"})
    void testIncorrectInputs(String inputOctal, String expectedMessage) {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> OctalToHexadecimal.octalToDecimal(inputOctal));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }
}
