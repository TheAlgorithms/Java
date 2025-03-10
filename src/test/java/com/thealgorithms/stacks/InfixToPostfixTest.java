package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class InfixToPostfixTest {

    @ParameterizedTest
    @MethodSource("provideValidExpressions")
    void testValidExpressions(String infix, String expectedPostfix) throws Exception {
        assertEquals(expectedPostfix, InfixToPostfix.infix2PostFix(infix));
    }

    private static Stream<Arguments> provideValidExpressions() {
        return Stream.of(Arguments.of("3+2", "32+"), Arguments.of("1+(2+3)", "123++"), Arguments.of("(3+4)*5-6", "34+5*6-"));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidExpressions")
    void testInvalidExpressions(String infix, String expectedMessage) {
        Exception exception = assertThrows(Exception.class, () -> InfixToPostfix.infix2PostFix(infix));
        assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> provideInvalidExpressions() {
        return Stream.of(Arguments.of("((a+b)*c-d", "invalid expression"));
    }
}
