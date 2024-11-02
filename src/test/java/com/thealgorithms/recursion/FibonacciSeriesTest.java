package com.thealgorithms.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FibonacciSeriesTest {

    @Test
    public void testFibonacci() {
        assertEquals(0, FibonacciSeries.fibonacci(0));
        assertEquals(1, FibonacciSeries.fibonacci(1));
        assertEquals(1, FibonacciSeries.fibonacci(2));
        assertEquals(2, FibonacciSeries.fibonacci(3));
        assertEquals(3, FibonacciSeries.fibonacci(4));
        assertEquals(5, FibonacciSeries.fibonacci(5));
        assertEquals(8, FibonacciSeries.fibonacci(6));
        assertEquals(13, FibonacciSeries.fibonacci(7));
        assertEquals(21, FibonacciSeries.fibonacci(8));
        assertEquals(34, FibonacciSeries.fibonacci(9));
        assertEquals(55, FibonacciSeries.fibonacci(10));
        assertEquals(89, FibonacciSeries.fibonacci(11));
        assertEquals(144, FibonacciSeries.fibonacci(12));
        assertEquals(233, FibonacciSeries.fibonacci(13));
        assertEquals(377, FibonacciSeries.fibonacci(14));
    }
}
