package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ValidParenthesesTest {

    @Test
    void testOne() {
        assertTrue(ValidParentheses.isValid("()"));
    }

    @Test
    void testTwo() {
        assertTrue(ValidParentheses.isValid("()[]{}"));
    }

    @Test
    void testThree() {
        assertFalse(ValidParentheses.isValid("(]"));
    }
}
