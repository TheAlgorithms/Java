package com.thealgorithms.matrix.matrixexponentiation;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
class FibonacciTest {
    private static BigDecimal[][] matrix(String a, String b, String c, String d) {
        return new BigDecimal[][] {{new BigDecimal(a), new BigDecimal(b)}, {new BigDecimal(c), new BigDecimal(d)}};
    }
    @Test
    void testFibZeroReturnsIdentityMatrix() {
        assertArrayEquals(matrix("1", "0", "0", "1"), Fibonacci.fib(0));
    }
    @Test
    void testFibOne() {
        assertArrayEquals(matrix("1", "1", "1", "0"), Fibonacci.fib(1));
    }
    @Test
    void testFibEvenInput() {
        assertArrayEquals(matrix("2", "1", "1", "1"), Fibonacci.fib(2));
    }
    @Test
    void testFibOddInput() {
        assertArrayEquals(matrix("3", "2", "2", "1"), Fibonacci.fib(3));
    }
    @Test
    void testFibTen() {
        assertArrayEquals(matrix("89", "55", "55", "34"), Fibonacci.fib(10));
    }
    @Test
    void testFibLargeInput() {
        assertArrayEquals(matrix("20365011074", "12586269025", "12586269025", "7778742049"), Fibonacci.fib(50));
    }
}
