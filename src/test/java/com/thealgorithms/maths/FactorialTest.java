package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Factorial.
 */
class FactorialTest {

    private static final String EXCEPTION_MESSAGE = "Input number cannot be negative";

    @Test
    @DisplayName("Factorial should throw exception for negative input")
    void testWhenInvalidInputProvidedShouldThrowException() {
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> Factorial.factorial(-1));

        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    @DisplayName("Factorial should return correct values for small numbers")
    void testCorrectFactorialCalculation() {
        assertEquals(1, Factorial.factorial(0));
        assertEquals(1, Factorial.factorial(1));
        assertEquals(120, Factorial.factorial(5));
        assertEquals(3_628_800, Factorial.factorial(10));
    }

    @Test
    @DisplayName("Factorial should correctly calculate large values")
    void testLargeFactorial() {
        assertEquals(
            new BigInteger("2432902008176640000"),
            Factorial.factorial(20)
        );
    }
}
