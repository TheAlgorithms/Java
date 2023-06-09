package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
