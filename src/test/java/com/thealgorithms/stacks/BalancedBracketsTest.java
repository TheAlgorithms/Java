package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BalancedBracketsTest {

    @Test
    void testIsPaired() {
        assertTrue(BalancedBrackets.isPaired('(', ')'));
        assertTrue(BalancedBrackets.isPaired('[', ']'));
        assertTrue(BalancedBrackets.isPaired('{', '}'));
        assertTrue(BalancedBrackets.isPaired('<', '>'));

        assertFalse(BalancedBrackets.isPaired('(', ']'));
        assertFalse(BalancedBrackets.isPaired('[', ')'));
        assertFalse(BalancedBrackets.isPaired('{', '>'));
        assertFalse(BalancedBrackets.isPaired('<', ')'));

        assertFalse(BalancedBrackets.isPaired('a', 'b'));
        assertFalse(BalancedBrackets.isPaired('!', '@'));
    }

    @Test
    void testIsBalanced() {
        assertTrue(BalancedBrackets.isBalanced("[()]{}{[()()]()}"));
        assertTrue(BalancedBrackets.isBalanced("()"));
        assertTrue(BalancedBrackets.isBalanced("[]"));
        assertTrue(BalancedBrackets.isBalanced("{}"));
        assertTrue(BalancedBrackets.isBalanced("<>"));
        assertTrue(BalancedBrackets.isBalanced("[{<>}]"));
        assertTrue(BalancedBrackets.isBalanced(""));

        assertFalse(BalancedBrackets.isBalanced("[(])"));
        assertFalse(BalancedBrackets.isBalanced("([)]"));
        assertFalse(BalancedBrackets.isBalanced("{[<]>}"));
        assertFalse(BalancedBrackets.isBalanced("["));
        assertFalse(BalancedBrackets.isBalanced(")"));
        assertFalse(BalancedBrackets.isBalanced("[{"));
        assertFalse(BalancedBrackets.isBalanced("]"));

        assertFalse(BalancedBrackets.isBalanced("[a+b]"));
        assertFalse(BalancedBrackets.isBalanced("a+b"));
    }

    @Test
    void testIsBalancedNull() {
        assertThrows(IllegalArgumentException.class, () -> BalancedBrackets.isBalanced(null));
    }
}
