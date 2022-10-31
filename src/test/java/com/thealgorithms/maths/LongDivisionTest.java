package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LongDivisionTest {
	
    @Test
    void testOne() {
        assertEquals(3, LongDivision.divide(10,3));
    }
  
      @Test
    void testTwo() {
        assertEquals(-2, LongDivision.divide(7,-3));
    }
  
  
      @Test
    void testThree() {
        assertEquals(10, LongDivision.divide(105,10));
    }
	
}
