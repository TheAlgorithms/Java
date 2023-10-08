package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SwapTwoNumbersTest {

    @Test
    void testSwapNumbers() {
        int a = 5;
        int b = 10;
        
        SwapTwoNumbers.swapNumbers(a, b);
        
        assertEquals(10, a);
        assertEquals(5, b);
        
        // Test with negative numbers
        int x = -15;
        int y = 30;
        
        SwapTwoNumbers.swapNumbers(x, y);
        
        assertEquals(30, x);
        assertEquals(-15, y);
        
        // Test with zeros
        int p = 0;
        int q = 0;
        
        SwapTwoNumbers.swapNumbers(p, q);
        
        assertEquals(0, p);
        assertEquals(0, q);
    }
}
