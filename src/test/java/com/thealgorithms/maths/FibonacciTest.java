package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class FibonacciTest {
    @Test
    public void checkValueAtZero() {
        assertEquals(BigInteger.ZERO, Fibonacci.calFibcompute(0));
    }

    @Test
    public void checkValueAtOne() {
        assertEquals(BigInteger.ONE, Fibonacci.calFibcompute(1));
    }

    @Test
    public void checkValueAtTwo() {
        assertEquals(BigInteger.ONE, Fibonacci.calFibcompute(2));
    }

    @Test
    public void checkRecurrenceRelation() {
        for (int i = 0; i < 100; ++i) {
            assertEquals(Fibonacci.calFibcompute(i + 2), Fibonacci.calFibcompute(i + 1).add(Fibonacci.calFibcompute(i)));
        }
    }

    @Test
    public void checkNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> { Fibonacci.calFibcompute(-1); });
    }
}
