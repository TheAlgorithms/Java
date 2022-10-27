package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Valid_Parentheses_Test {
	
    @Test
    void test_one() {
        assertEquals(true, Valid_Parentheses.isValid("()"));
    }
  
      @Test
    void test_two() {
        assertEquals(true, Valid_Parentheses.isValid("()[]{}"));
    }
  
  
      @Test
    void test_three() {
        assertEquals(false, Valid_Parentheses.isValid("(]"));
    }
	
}
