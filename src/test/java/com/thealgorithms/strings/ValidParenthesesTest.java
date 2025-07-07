package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ValidParenthesesTest {

    @ParameterizedTest(name = "Input: \"{0}\" → Expected: {1}")
    @MethodSource("parenthesesProvider")
    void testIsValid(String input, boolean expected) {
        assertEquals(expected, ValidParentheses.isValid(input));
    }

    static Stream<Arguments> parenthesesProvider() {
        return Stream.of(Arguments.of("()", true), Arguments.of("()[]{}", true), Arguments.of("(]", false), Arguments.of("{[]}", true), Arguments.of("([{}])", true), Arguments.of("([)]", false), Arguments.of("", true), Arguments.of("(", false), Arguments.of(")", false));
    }
}
