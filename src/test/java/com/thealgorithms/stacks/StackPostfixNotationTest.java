package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class StackPostfixNotationTest {

    @ParameterizedTest
    @MethodSource("provideValidTestCases")
    void testEvaluate(String expression, int expected) {
        assertEquals(expected, StackPostfixNotation.postfixEvaluate(expression));
    }

    static Stream<Arguments> provideValidTestCases() {
        return Stream.of(Arguments.of("1 1 +", 2), Arguments.of("2 3 *", 6), Arguments.of("6 2 /", 3), Arguments.of("-5 -2 -", -3), Arguments.of("5 2 + 3 *", 21), Arguments.of("-5", -5));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidTestCases")
    void testEvaluateThrowsException(String expression) {
        assertThrows(IllegalArgumentException.class, () -> StackPostfixNotation.postfixEvaluate(expression));
    }

    static Stream<Arguments> provideInvalidTestCases() {
        return Stream.of(Arguments.of(""), Arguments.of("3 3 3"), Arguments.of("3 3 !"), Arguments.of("+"), Arguments.of("2 +"));
    }
}
