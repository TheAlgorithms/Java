package com.thealgorithms.stacks;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ValidParenthesesTest {

    @ParameterizedTest
    @MethodSource("provideValidTestCases")
    void testIsValid_ValidCases(String input, boolean expected) {
        assertEquals(expected, ValidParentheses.isValid(input));
    }

    static Stream<Arguments> provideValidTestCases() {
        return Stream.of(
                Arguments.of("()", true),
                Arguments.of("()[]{}", true),
                Arguments.of("{[]}", true),
                Arguments.of("", true)  // empty string is valid
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidTestCases")
    void testIsValid_InvalidCases(String input) {
        assertEquals(false, ValidParentheses.isValid(input));
    }

    static Stream<Arguments> provideInvalidTestCases() {
        return Stream.of(
                Arguments.of("("),
                Arguments.of(")"),
                Arguments.of("([)]"),
                Arguments.of("{[}]"),
                Arguments.of("((()")
        );
    }
}
