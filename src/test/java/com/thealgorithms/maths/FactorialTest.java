package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FactorialTest {
    private static final String EXCEPTION_MESSAGE = "Input number cannot be negative";

    @Test
    public void testWhenInvalidInoutProvidedShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Factorial.factorial(-1));
        assertEquals(exception.getMessage(), EXCEPTION_MESSAGE);
    }

    @Test
    public void testCorrectFactorialCalculation() {
        assertEquals(1, Factorial.factorial(0));
        assertEquals(1, Factorial.factorial(1));
        assertEquals(120, Factorial.factorial(5));
        assertEquals(3628800, Factorial.factorial(10));
    }
}
