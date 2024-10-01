package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class FibonacciJavaRecursionTest {
    @Test
    public void checkValueAtZero() {
        assertEquals(BigInteger.ZERO, FibonacciJavaRecursion.computeRecursively(0));
    }

    @Test
    public void checkValueAtOne() {
        assertEquals(BigInteger.ONE, FibonacciJavaRecursion.computeRecursively(1));
    }

    @Test
    public void checkValueAtTwo() {
        assertEquals(BigInteger.ONE, FibonacciJavaRecursion.computeRecursively(2));
    }

    @Test
    public void checkRecurrenceRelation() {
        for (int i = 0; i < 100; ++i) {
            assertEquals(FibonacciJavaRecursion.computeRecursively(i + 2), FibonacciJavaRecursion.computeRecursively(i + 1).add(FibonacciJavaRecursion.computeRecursively(i)));
        }
    }

    @Test
    public void checkNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> { FibonacciJavaRecursion.computeRecursively(-1); });
    }
}
