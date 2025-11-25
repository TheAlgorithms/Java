package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidParenthesesTest {

    @Test
    void testValidParentheses() {
        assertTrue(ValidParentheses.isValid("()"));
        assertTrue(ValidParentheses.isValid("()[]{}"));
        assertTrue(ValidParentheses.isValid("{[]}"));
        assertTrue(ValidParentheses.isValid(""));
    }

    @Test
    void testInvalidParentheses() {
        assertFalse(ValidParentheses.isValid("(]"));
        assertFalse(ValidParentheses.isValid("([)]"));
        assertFalse(ValidParentheses.isValid("{{{"));
        assertFalse(ValidParentheses.isValid("}"));
        assertFalse(ValidParentheses.isValid("("));
    }

    @Test
    void testNullAndOddLength() {
        assertFalse(ValidParentheses.isValid(null));
        assertFalse(ValidParentheses.isValid("(()"));
    }
}