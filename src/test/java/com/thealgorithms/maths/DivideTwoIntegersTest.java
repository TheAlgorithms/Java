package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DivideTwoIntegersTest {
	
    @Test
    void testOne() {
        assertEquals(3, DivideTwoIntegers.divide(10,3));
    }
  
      @Test
    void testTwo() {
        assertEquals(-2, DivideTwoIntegers.divide(7,-3));
    }
  
  
      @Test
    void testThree() {
        assertEquals(10, DivideTwoIntegers.divide(105,10));
    }
	
}
