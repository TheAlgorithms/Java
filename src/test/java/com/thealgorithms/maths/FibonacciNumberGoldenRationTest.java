package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class FibonacciNumberGoldenRationTest {

    @Test
    public void returnsCorrectValues() {
        for (int n = 0; n <= FibonacciNumber.MAX_ARG; ++n) {
            final var actual = BigInteger.valueOf(FibonacciNumber.nthFibonaccicompute(n));
            final var expected = Fibonacci.calFibcompute(n);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void throwsIllegalArgumentExceptionForNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> { FibonacciNumber.nthFibonaccicompute(-1); });
    }

    @Test
    public void throwsIllegalArgumentExceptionForLargeInput() {
        assertThrows(IllegalArgumentException.class, () -> { FibonacciNumber.nthFibonaccicompute(FibonacciNumber.MAX_ARG + 1); });
    }
}
