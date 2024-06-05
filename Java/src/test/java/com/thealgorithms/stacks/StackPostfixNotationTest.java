package com.thealgorithms.stacks;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import org.junit.jupiter.api.Test;

public class StackPostfixNotationTest {
    @Test
    public void testEvaluate() {
        final Map<String, Integer> testCases = Map.ofEntries(entry("1 1 +", 2), entry("2 3 *", 6), entry("6 2 /", 3), entry("-5 -2 -", -3), entry("5 2 + 3 *", 21), entry("-5", -5));
        for (final var tc : testCases.entrySet()) {
            assertEquals(tc.getValue(), StackPostfixNotation.postfixEvaluate(tc.getKey()));
        }
    }

    @Test
    public void testIfEvaluateThrowsExceptionForEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> StackPostfixNotation.postfixEvaluate(""));
    }

    @Test
    public void testIfEvaluateThrowsExceptionForInproperInput() {
        assertThrows(IllegalArgumentException.class, () -> StackPostfixNotation.postfixEvaluate("3 3 3"));
    }

    @Test
    public void testIfEvaluateThrowsExceptionForInputWithUnknownOperation() {
        assertThrows(IllegalArgumentException.class, () -> StackPostfixNotation.postfixEvaluate("3 3 !"));
    }

    @Test
    public void testIfEvaluateThrowsExceptionForInputWithTooFewArgsA() {
        assertThrows(IllegalArgumentException.class, () -> StackPostfixNotation.postfixEvaluate("+"));
    }

    @Test
    public void testIfEvaluateThrowsExceptionForInputWithTooFewArgsB() {
        assertThrows(IllegalArgumentException.class, () -> StackPostfixNotation.postfixEvaluate("2 +"));
    }
}
