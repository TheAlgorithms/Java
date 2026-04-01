package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class FactorialTest {
    private static final String EXCEPTION_MESSAGE = "Input number cannot be negative";

    @Test
    public void testWhenInvalidInoutProvidedShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Factorial.factorial(-1));
        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void testCorrectFactorialCalculation() {
        assertEquals(BigInteger.ONE, Factorial.factorial(0));
        assertEquals(BigInteger.ONE, Factorial.factorial(1));
        assertEquals(BigInteger.valueOf(120), Factorial.factorial(5));
        assertEquals(BigInteger.valueOf(3628800), Factorial.factorial(10));
    }
}
