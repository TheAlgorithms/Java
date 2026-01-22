package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class BasicCalculatorUsingStackTest {

    @Test
    void testSimpleAddition() {
        assertEquals(5, BasicCalculatorUsingStack.evaluate("2 + 3"));
    }

    @Test
    void testSimpleSubtraction() {
        assertEquals(1, BasicCalculatorUsingStack.evaluate("3 - 2"));
    }

    @Test
    void testWithParentheses() {
        assertEquals(23, BasicCalculatorUsingStack.evaluate("(1 + (4 + 5 + 2) - 3) + (6 + 8)"));
    }

    @Test
    void testUnaryMinus() {
        assertEquals(-2, BasicCalculatorUsingStack.evaluate("-2"));
        assertEquals(1, BasicCalculatorUsingStack.evaluate("1 - (-2)"));
    }

    @Test
    void testSpacesInExpression() {
        assertEquals(3, BasicCalculatorUsingStack.evaluate(" 2 + 1 "));
    }

    @Test
    void testInvalidExpression() {
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> BasicCalculatorUsingStack.evaluate(""));
        assertEquals("Expression must not be null or empty.", exception.getMessage());
    }
}
