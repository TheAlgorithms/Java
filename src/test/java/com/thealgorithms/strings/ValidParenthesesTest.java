package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ValidParenthesesTest {

    @ParameterizedTest(name = "Input: \"{0}\" → Expected: {1}")
    @CsvSource({"'()', true", "'()[]{}', true", "'(]', false", "'{[]}', true", "'([{}])', true", "'([)]', false", "'', true", "'(', false", "')', false", "'{{{{}}}}', true", "'[({})]', true", "'[(])', false", "'[', false", "']', false", "'()()()()', true", "'(()', false", "'())', false",
        "'{[()()]()}', true"})
    void
    testIsValid(String input, boolean expected) {
        assertEquals(expected, ValidParentheses.isValid(input));
    }

    @Test
    void testNullInputThrows() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> ValidParentheses.isValid(null));
        assertEquals("Input string cannot be null", ex.getMessage());
    }

    @ParameterizedTest(name = "Input: \"{0}\" → throws IllegalArgumentException")
    @CsvSource({"'a'", "'()a'", "'[123]'", "'{hello}'", "'( )'", "'\t'", "'\n'", "'@#$%'"})
    void testInvalidCharactersThrow(String input) {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> ValidParentheses.isValid(input));
        assertTrue(ex.getMessage().startsWith("Unexpected character"));
    }
}
