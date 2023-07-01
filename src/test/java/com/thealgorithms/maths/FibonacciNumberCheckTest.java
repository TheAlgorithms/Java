package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Fibonacci Sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144...
 *
 * @author Albina Gimaletdinova on 01/07/2023
 */
public class FibonacciNumberCheckTest {
    @Test
    public void testNumberIsFibonacciNumber() {
        Assertions.assertTrue(FibonacciNumberCheck.isFibonacciNumber(1));
        Assertions.assertTrue(FibonacciNumberCheck.isFibonacciNumber(2));
        Assertions.assertTrue(FibonacciNumberCheck.isFibonacciNumber(21));
        Assertions.assertTrue(FibonacciNumberCheck.isFibonacciNumber(6765)); // 20th number
        Assertions.assertTrue(FibonacciNumberCheck.isFibonacciNumber(832040)); // 30th number
        Assertions.assertTrue(FibonacciNumberCheck.isFibonacciNumber(102334155)); // 40th number
        Assertions.assertTrue(FibonacciNumberCheck.isFibonacciNumber(701408733)); // 45th number
    }

    @Test
    public void testNumberIsNotFibonacciNumber() {
        Assertions.assertFalse(FibonacciNumberCheck.isFibonacciNumber(9));
        Assertions.assertFalse(FibonacciNumberCheck.isFibonacciNumber(10));
        Assertions.assertFalse(FibonacciNumberCheck.isFibonacciNumber(145));
        Assertions.assertFalse(FibonacciNumberCheck.isFibonacciNumber(701408734));
    }
}
