package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FibonacciNumTest {

    @Test
    public void TestisisPerfectSquare() {
        assertEquals(true, FibonacciNumber.isPerfectSquare(1));
        assertEquals(false, FibonacciNumber.isPerfectSquare(2));
        assertEquals(false, FibonacciNumber.isPerfectSquare(21));
    }    
}
