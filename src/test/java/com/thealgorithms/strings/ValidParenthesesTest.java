package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ValidParenthesesTest {

    @ParameterizedTest(name = "Input: \"{0}\" → Expected: {1}")
    @CsvSource({"'()', true", "'()[]{}', true", "'(]', false", "'{[]}', true", "'([{}])', true", "'([)]', false", "'', true", "'(', false", "')', false"})
    void testIsValid(String input, boolean expected) {
        assertEquals(expected, ValidParentheses.isValid(input));
    }
}
