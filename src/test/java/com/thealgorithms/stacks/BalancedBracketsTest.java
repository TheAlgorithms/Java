package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BalancedBracketsTest {

    @ParameterizedTest
    @CsvSource({"(, )", "[, ]", "{, }", "<, >"})
    void testIsPairedTrue(char opening, char closing) {
        assertTrue(BalancedBrackets.isPaired(opening, closing));
    }

    @ParameterizedTest
    @CsvSource({"(, ]", "[, )", "{, >", "<, )", "a, b", "!, @"})
    void testIsPairedFalse(char opening, char closing) {
        assertFalse(BalancedBrackets.isPaired(opening, closing));
    }

    @ParameterizedTest
    @CsvSource({"'[()]{}{[()()]()}', true", "'()', true", "'[]', true", "'{}', true", "'<>', true", "'[{<>}]', true", "'', true", "'[(])', false", "'([)]', false", "'{[<]>}', false", "'[', false", "')', false", "'[{', false", "']', false", "'[a+b]', false", "'a+b', false"})
    void testIsBalanced(String input, boolean expected) {
        assertEquals(expected, BalancedBrackets.isBalanced(input));
    }

    @Test
    void testIsBalancedNull() {
        assertThrows(IllegalArgumentException.class, () -> BalancedBrackets.isBalanced(null));
    }
}
