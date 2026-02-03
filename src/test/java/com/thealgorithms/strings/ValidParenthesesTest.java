package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.stacks.ValidParentheses;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for ValidParentheses (consolidated implementation in stacks package).
 */
public class ValidParenthesesTest {

    @ParameterizedTest(name = "Input: \"{0}\" → Expected: {1}")
    @CsvSource({
        "'()', true",
        "'()[]{}', true",
        "'(]', false",
        "'{[]}', true",
        "'([{}])', true",
        "'([)]', false",
        "'', true",
        "'(', false",
        "')', false",
        "'{{{{}}}}', true",
        "'[({})]', true",
        "'[(])', false",
        "'[', false",
        "']', false",
        "'()()()()', true",
        "'(()', false",
        "'())', false",
        "'{[()()]()}', true"
    })
    void testIsValid(String input, boolean expected) {
        assertEquals(expected, ValidParentheses.isValid(input));
    }

    @Test
    void testNullInputReturnsFalse() {
        assertFalse(ValidParentheses.isValid(null));
    }

    @ParameterizedTest(name = "Input: \"{0}\" → invalid characters return false")
    @CsvSource({"'a'", "'()a'", "'[123]'", "'{hello}'", "'( )'", "'\t'", "'\n'", "'@#$%'"})
    void testInvalidCharactersReturnFalse(String input) {
        assertFalse(ValidParentheses.isValid(input));
    }
}
