package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrefixToInfixTest {

    @ParameterizedTest
    @MethodSource("provideValidPrefixToInfixTestCases")
    void testValidPrefixToInfixConversion(String prefix, String expectedInfix) {
        assertEquals(expectedInfix, PrefixToInfix.getPrefixToInfix(prefix));
    }

    static Stream<Arguments> provideValidPrefixToInfixTestCases() {
        return Stream.of(Arguments.of("A", "A"), // Single operand
            Arguments.of("+AB", "(A+B)"), // Addition
            Arguments.of("*+ABC", "((A+B)*C)"), // Addition and multiplication
            Arguments.of("-+A*BCD", "((A+(B*C))-D)"), // Mixed operators
            Arguments.of("/-A*BC+DE", "((A-(B*C))/(D+E))"), // Mixed operators
            Arguments.of("^+AB*CD", "((A+B)^(C*D))") // Mixed operators
        );
    }

    @Test
    void testEmptyPrefixExpression() {
        assertEquals("", PrefixToInfix.getPrefixToInfix(""));
    }

    @Test
    void testNullPrefixExpression() {
        assertThrows(NullPointerException.class, () -> PrefixToInfix.getPrefixToInfix(null));
    }

    @Test
    void testMalformedPrefixExpression() {
        assertThrows(ArithmeticException.class, () -> PrefixToInfix.getPrefixToInfix("+ABC"));
    }
}
