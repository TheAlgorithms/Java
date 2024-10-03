package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class InfixToPrefixTest {

    @ParameterizedTest
    @MethodSource("provideValidExpressions")
    void testValidExpressions(String infix, String expectedPrefix) throws Exception {
        assertEquals(expectedPrefix, InfixToPrefix.infix2Prefix(infix));
    }

    @Test
    void testEmptyString() {
        // Assuming that an empty string returns an empty prefix or throws an exception
        assertEquals("", InfixToPrefix.infix2Prefix(""));
    }

    @Test
    void testNullValue() {
        // Assuming that a null input throws a NullPointerException
        assertThrows(NullPointerException.class, () -> InfixToPrefix.infix2Prefix(null));
    }

    private static Stream<Arguments> provideValidExpressions() {
        return Stream.of(Arguments.of("3+2", "+32"), // Simple addition
            Arguments.of("1+(2+3)", "+1+23"), // Parentheses
            Arguments.of("(3+4)*5-6", "-*+3456"), // Nested operations
            Arguments.of("a+b*c", "+a*bc"), // Multiplication precedence
            Arguments.of("a+b*c/d", "+a/*bcd"), // Division precedence
            Arguments.of("a+b*c-d", "-+a*bcd"), // Subtraction precedence
            Arguments.of("a+b*c/d-e", "-+a/*bcde"), // Mixed precedence
            Arguments.of("a+b*(c-d)", "+a*b-cd"), // Parentheses precedence
            Arguments.of("a+b*(c-d)/e", "+a/*b-cde") // Mixed precedence with parentheses
        );
    }
}
