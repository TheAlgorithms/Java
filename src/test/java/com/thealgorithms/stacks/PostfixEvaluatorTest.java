package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;
import org.junit.jupiter.api.Test;

public class PostfixEvaluatorTest {

    @Test
    public void testValidExpressions() {
        assertEquals(22, PostfixEvaluator.evaluatePostfix("5 6 + 2 *"));
        assertEquals(27, PostfixEvaluator.evaluatePostfix("7 2 + 3 *"));
        assertEquals(3, PostfixEvaluator.evaluatePostfix("10 5 / 1 +"));
    }

    @Test
    public void testInvalidExpression() {
        assertThrows(EmptyStackException.class, () -> PostfixEvaluator.evaluatePostfix("5 +"));
    }

    @Test
    public void testMoreThanOneStackSizeAfterEvaluation() {
        assertThrows(IllegalArgumentException.class, () -> PostfixEvaluator.evaluatePostfix("5 6 + 2 * 3"));
    }
}
