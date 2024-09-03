package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LongestValidParenthesesTest {

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of("", 0), Arguments.of("(", 0), Arguments.of(")", 0), Arguments.of("()", 2), Arguments.of("(())", 4), Arguments.of("()()", 4), Arguments.of(")(", 0), Arguments.of("(()", 2), Arguments.of("())(", 2), Arguments.of("(()())", 6), Arguments.of("(((())))", 8),
            Arguments.of("(()))(()", 4), Arguments.of("()()()(", 6), Arguments.of("(()())()(", 8), Arguments.of("((((((", 0), Arguments.of("))))))", 0), Arguments.of("(()())(", 6), Arguments.of("))()(", 2), Arguments.of("()((()))", 8), Arguments.of("((()((())))", 10));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testLongestValidParentheses(String input, int expected) {
        assertEquals(expected, LongestValidParentheses.getLongestValidParentheses(input));
    }
}
