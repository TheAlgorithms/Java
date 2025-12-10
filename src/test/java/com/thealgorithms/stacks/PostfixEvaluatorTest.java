package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PostfixEvaluatorTest {

    @ParameterizedTest(name = "Expression: \"{0}\" → Result: {1}")
    @CsvSource({"'5 6 + 2 *', 22", "'7 2 + 3 *', 27", "'10 5 / 1 +', 3", "'8', 8", "'3 4 +', 7"})
    @DisplayName("Valid postfix expressions")
    void testValidExpressions(String expression, int expected) {
        assertEquals(expected, PostfixEvaluator.evaluatePostfix(expression));
    }

    @Test
    @DisplayName("Should throw EmptyStackException for incomplete expression")
    void testInvalidExpression() {
        assertThrows(EmptyStackException.class, () -> PostfixEvaluator.evaluatePostfix("5 +"));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for extra operands")
    void testExtraOperands() {
        assertThrows(IllegalArgumentException.class, () -> PostfixEvaluator.evaluatePostfix("5 6 + 2 * 3"));
    }

    @Test
    @DisplayName("Should throw ArithmeticException for division by zero")
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> PostfixEvaluator.evaluatePostfix("1 0 /"));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for invalid characters")
    void testInvalidToken() {
        assertThrows(IllegalArgumentException.class, () -> PostfixEvaluator.evaluatePostfix("1 a +"));
    }
}
