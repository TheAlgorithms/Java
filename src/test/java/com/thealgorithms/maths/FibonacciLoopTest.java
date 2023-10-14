package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class FibonacciLoopTest {
    @Test
    public void checkValueAtZero() {
        assertEquals(BigInteger.ZERO, FibonacciLoop.compute(0));
    }

    @Test
    public void checkValueAtOne() {
        assertEquals(BigInteger.ONE, FibonacciLoop.compute(1));
    }

    @Test
    public void checkValueAtTwo() {
        assertEquals(BigInteger.ONE, FibonacciLoop.compute(2));
    }

    @Test
    public void checkRecurrenceRelation() {
        for (int i = 0; i < 100; ++i) {
            assertEquals(FibonacciLoop.compute(i + 2), FibonacciLoop.compute(i + 1).add(FibonacciLoop.compute(i)));
        }
    }

    @Test
    public void checkNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> { FibonacciLoop.compute(-1); });
    }
}
