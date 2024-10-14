package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;
import org.junit.jupiter.api.Test;

public class PrefixEvaluatorTest {

    @Test
    public void testValidExpressions() {
        assertEquals(10, PrefixEvaluator.evaluatePrefix("+ * 2 3 4"));
        assertEquals(5, PrefixEvaluator.evaluatePrefix("- + 7 3 5"));
        assertEquals(6, PrefixEvaluator.evaluatePrefix("/ * 3 2 1"));
    }

    @Test
    public void testInvalidExpression() {
        assertThrows(EmptyStackException.class, () -> PrefixEvaluator.evaluatePrefix("+ 3"));
    }

    @Test
    public void testMoreThanOneStackSizeAfterEvaluation() {
        assertThrows(IllegalArgumentException.class, () -> PrefixEvaluator.evaluatePrefix("+ 3 4 5"));
    }
}
