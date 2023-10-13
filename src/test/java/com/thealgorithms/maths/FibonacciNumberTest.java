package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class FibonacciNumberTest {

    @Test
    public void returnsCorrectValues() {
        for (int n = 0; n <= FibonacciNumber.MAX_ARG; ++n) {
            final var actual = FibonacciNumber.nthFibonacci(n);
            final var expected = Fibonacci.calFib(n);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void throwsIllegalArgumentExceptionForNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> { FibonacciNumber.nthFibonacci(-1); });
    }

    @Test
    public void throwsIllegalArgumentExceptionForLargeInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            // You can choose a large value for n here, e.g., 1000
            FibonacciNumber.nthFibonacci(1000);
        });
    }
}
