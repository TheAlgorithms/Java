package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class InfixToPrefixTest {

    @ParameterizedTest
    @MethodSource("provideValidExpressions")
    void testValidExpressions(String infix, String expectedPrefix) throws Exception {
        assertEquals(expectedPrefix, InfixToPrefix.infix2Prefix(infix));
    }

    private static Stream<Arguments> provideValidExpressions() {
        return Stream.of(Arguments.of("3+2", "+32"), // Simple addition
            Arguments.of("1+(2+3)", "+1+23"), // Parentheses
            Arguments.of("(3+4)*5-6", "-*+3456"), // Nested operations
            Arguments.of("a+b*c", "+a*bc") // Multiplication precedence
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidExpressions")
    void testInvalidExpressions(String infix, String expectedMessage) {
        Exception exception = assertThrows(Exception.class, () -> InfixToPrefix.infix2Prefix(infix));
        assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> provideInvalidExpressions() {
        return Stream.of(Arguments.of("((a+b)*c-d", "invalid expression"), // Missing closing bracket
            Arguments.of("a++b", "invalid expression") // Invalid operator
        );
    }
}
