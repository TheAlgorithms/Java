package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidParenthesesTest {
	
    @Test
    void testOne() {
        assertEquals(true, ValidParentheses.isValid("()"));
    }
  
    @Test
    void testTwo() {
        assertEquals(true, ValidParentheses.isValid("()[]{}"));
    }
  
  
    @Test
    void testThree() {
        assertEquals(false, ValidParentheses.isValid("(]"));
    }
	
}
