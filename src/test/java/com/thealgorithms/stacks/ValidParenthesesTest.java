package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for {@link ValidParentheses}.
 */
public class ValidParenthesesTest {

    @ParameterizedTest
    @CsvSource({"'()', true", "'()[]{}', true", "'{[]}', true", "'', true"})
    void testValidParentheses(String input, boolean expected) {
        assertEquals(expected, ValidParentheses.isValid(input));
    }

    @ParameterizedTest
    @CsvSource({"'(', false", "')', false", "'([)]', false", "'{[}]', false", "'((()', false"})
    void testInvalidParentheses(String input, boolean expected) {
        assertEquals(expected, ValidParentheses.isValid(input));
    }

    @ParameterizedTest
    @CsvSource({"null"})
    void testNullInput(String input) {
        assertThrows(NullPointerException.class, () -> ValidParentheses.isValid(null));
    }
}
