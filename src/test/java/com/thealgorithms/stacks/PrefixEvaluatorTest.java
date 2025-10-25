package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PrefixEvaluatorTest {

    @ParameterizedTest(name = "Expression: \"{0}\" → Result: {1}")
    @CsvSource({"'+ * 2 3 4', 10", "'- + 7 3 5', 5", "'/ * 3 2 1', 6"})
    void testValidExpressions(String expression, int expected) {
        assertEquals(expected, PrefixEvaluator.evaluatePrefix(expression));
    }

    @Test
    @DisplayName("Should throw EmptyStackException for incomplete expression")
    void testInvalidExpression() {
        assertThrows(EmptyStackException.class, () -> PrefixEvaluator.evaluatePrefix("+ 3"));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException if stack not reduced to one result")
    void testMoreThanOneStackSizeAfterEvaluation() {
        assertThrows(IllegalArgumentException.class, () -> PrefixEvaluator.evaluatePrefix("+ 3 4 5"));
    }
}
