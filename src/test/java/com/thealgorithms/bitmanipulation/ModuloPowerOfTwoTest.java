package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ModuloPowerOfTwoTest {

    @ParameterizedTest
    @CsvSource({
        "10, 3, 2",
        "15, 2, 3",
        "20, 4, 4",
        "7, 1, 1",
        "5, 1, 1",
        "36, 5, 4",
    })
    void
    testModuloPowerOfTwo(int x, int n, int expected) {
        assertEquals(expected, ModuloPowerOfTwo.moduloPowerOfTwo(x, n));
    }

    @ParameterizedTest
    @CsvSource({
        "10, 0",
        "15, -2",
        "20, -4",
        "7, -1",
        "5, -1",
    })
    void
    testNegativeExponent(int x, int n) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ModuloPowerOfTwo.moduloPowerOfTwo(x, n));
        assertEquals("The exponent must be positive", exception.getMessage());
    }
}
