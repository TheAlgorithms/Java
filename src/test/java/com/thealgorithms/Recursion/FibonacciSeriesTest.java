package com.thealgorithms.Recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FibonacciSeriesTest {

    @Test
    public void testFibonacci() {
        assertEquals(0, FibonacciSeries.fib(0));
        assertEquals(1, FibonacciSeries.fib(1));
        assertEquals(1, FibonacciSeries.fib(2));
        assertEquals(2, FibonacciSeries.fib(3));
        assertEquals(3, FibonacciSeries.fib(4));
        assertEquals(5, FibonacciSeries.fib(5));
        assertEquals(8, FibonacciSeries.fib(6));
        assertEquals(13, FibonacciSeries.fib(7));
        assertEquals(21, FibonacciSeries.fib(8));
        assertEquals(34, FibonacciSeries.fib(9));
        assertEquals(55, FibonacciSeries.fib(10));
        assertEquals(89, FibonacciSeries.fib(11));
        assertEquals(144, FibonacciSeries.fib(12));
        assertEquals(233, FibonacciSeries.fib(13));
        assertEquals(377, FibonacciSeries.fib(14));
    }
}
