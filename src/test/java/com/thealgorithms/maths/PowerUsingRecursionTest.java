package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Test cases for Power using Recursion.
 *
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */
class PowerUsingRecursionTest {

    @ParameterizedTest
    @CsvSource({"2.0, 5, 32.0", "2.5, 5, 97.65625", "3.0, 4, 81.0", "5.0, 0, 1.0", "-2.0, 3, -8.0", "-2.0, 4, 16.0", "2.0, -1, 0.5", "2.0, -3, 0.125", "-2.0, -1, -0.5", "-2.0, -2, 0.25", "0.0, 0, 1.0", "0.0, 5, 0.0", "1.0, -5, 1.0", "1.0, 100000, 1.0", "-1.0, 100000, 1.0", "-1.0, 100001, -1.0"})
    void testPower(double base, int exponent, double expected) {
        assertEquals(expected, PowerUsingRecursion.power(base, exponent), 1e-9);
    }

    @Test
    void testZeroBaseWithNegativeExponentThrowsException() {
        assertThrows(ArithmeticException.class, () -> PowerUsingRecursion.power(0.0, -1));
        assertThrows(ArithmeticException.class, () -> PowerUsingRecursion.power(0.0, -10));
    }

    @Test
    void testIntegerMinValueExponent() {
        assertEquals(1.0, PowerUsingRecursion.power(1.0, Integer.MIN_VALUE), 1e-9);
        assertEquals(1.0, PowerUsingRecursion.power(-1.0, Integer.MIN_VALUE), 1e-9);
    }
}
