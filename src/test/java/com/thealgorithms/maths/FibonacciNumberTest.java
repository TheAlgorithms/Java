package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FibonacciNumberTest {
    
    /**
     * Test of isPerfectSquare method, of class FibonacciNumber.
     */
    @Test
    public void testtrueIsPerfectSquare() {
        assertEquals(true, FibonacciNumber.isPerfectSquare(1));
    }       
    public void testfalseIsPerfectSquare() {
        assertEquals(false, FibonacciNumber.isPerfectSquare(2));
        assertEquals(false, FibonacciNumber.isPerfectSquare(21));
        }
}
