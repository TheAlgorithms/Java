package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PostfixToInfixTest {

    @ParameterizedTest
    @MethodSource("provideValidPostfixToInfixTestCases")
    void testValidPostfixToInfixConversion(String postfix, String expectedInfix) {
        assertEquals(expectedInfix, PostfixToInfix.getPostfixToInfix(postfix));
    }

    static Stream<Arguments> provideValidPostfixToInfixTestCases() {
        return Stream.of(Arguments.of("A", "A"), Arguments.of("ABC+/", "(A/(B+C))"), Arguments.of("AB+CD+*", "((A+B)*(C+D))"), Arguments.of("AB+C+D+", "(((A+B)+C)+D)"), Arguments.of("ABCDE^*/-", "(A-(B/(C*(D^E))))"), Arguments.of("AB+CD^/E*FGH+-^", "((((A+B)/(C^D))*E)^(F-(G+H)))"));
    }

    @Test
    void testEmptyPostfixExpression() {
        assertEquals("", PostfixToInfix.getPostfixToInfix(""));
    }

    @Test
    void testNullPostfixExpression() {
        assertThrows(NullPointerException.class, () -> PostfixToInfix.getPostfixToInfix(null));
    }
}
