package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidParenthesesTest {
	
    @Test
    void test_one() {
        assertEquals(true, ValidParentheses.isValid("()"));
    }
  
    @Test
    void test_two() {
        assertEquals(true, ValidParentheses.isValid("()[]{}"));
    }
  
  
    @Test
    void test_three() {
        assertEquals(false, ValidParentheses.isValid("(]"));
    }
	
}
