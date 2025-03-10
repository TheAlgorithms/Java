package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ReverseNumberTest {

    @ParameterizedTest
    @CsvSource({"0, 0", "1, 1", "10, 1", "123, 321", "7890, 987"})
    public void testReverseNumber(int input, int expected) {
        assertEquals(expected, ReverseNumber.reverseNumber(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -123, -7890})
    public void testReverseNumberThrowsExceptionForNegativeInput(int input) {
        assertThrows(IllegalArgumentException.class, () -> ReverseNumber.reverseNumber(input));
    }
}
