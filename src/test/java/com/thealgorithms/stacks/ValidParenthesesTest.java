package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for {@link ValidParentheses}.
 */
public class ValidParenthesesTest {

    @ParameterizedTest
    @MethodSource("provideValidTestCases")
    void testIsValidValidCases(String input, Boolean expected) {
        assertEquals(expected, ValidParentheses.isValid(input));
    }

    static Stream<Arguments> provideValidTestCases() {
        return Stream.of(
                Arguments.of("()", Boolean.TRUE),
                Arguments.of("()[]{}", Boolean.TRUE),
                Arguments.of("{[]}", Boolean.TRUE),
                Arguments.of("", Boolean.TRUE) // empty string is valid
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidTestCases")
    void testIsValidInvalidCases(String input, Boolean expected) {
        assertEquals(expected, ValidParentheses.isValid(input));
    }

    static Stream<Arguments> provideInvalidTestCases() {
        return Stream.of(
                Arguments.of("(", Boolean.FALSE),
                Arguments.of(")", Boolean.FALSE),
                Arguments.of("([)]", Boolean.FALSE),
                Arguments.of("{[}]", Boolean.FALSE),
                Arguments.of("((()", Boolean.FALSE)
        );
    }
}
