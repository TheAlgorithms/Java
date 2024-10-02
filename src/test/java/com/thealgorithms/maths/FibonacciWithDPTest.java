package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FibonacciWithDPTest {
    @Test
    public void checkValueAtZero() {
        assertEquals(BigInteger.ZERO, FibonacciWithDP.computeRecursively(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void checkValueAtOneAndTwo(int number) {
        assertEquals(BigInteger.ONE, FibonacciWithDP.computeRecursively(number));
    }

    @Test
    public void checkRecurrenceRelation() {
        for (int i = 0; i < 100; ++i) {
            assertEquals(FibonacciWithDP.computeRecursively(i + 2), FibonacciWithDP.computeRecursively(i + 1).add(FibonacciWithDP.computeRecursively(i)));
        }
    }

    @Test
    public void checkNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> { FibonacciWithDP.computeRecursively(-1); });
    }
}
