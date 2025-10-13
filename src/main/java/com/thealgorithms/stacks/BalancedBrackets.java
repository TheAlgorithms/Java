package com.thealgorithms.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Optimized and robust Balanced Parenthesis (Valid Brackets) checker.
 *
 * Supports: (), [], {}, <>
 * Rules:
 * - Returns true if brackets are properly nested and matched.
 * - Returns false for any non-bracket character.
 * - Empty string is balanced.
 * - Null input throws IllegalArgumentException.
 *
 * Time complexity: O(n)
 * Space complexity: O(n) in worst case (stack contains all opening brackets).
 */
public final class BalancedBrackets {

    private BalancedBrackets() {
        // Utility class
    }

    /**
     * Returns true if {@code opening} and {@code closing} are matching bracket pair.
     */
    public static boolean isPaired(char opening, char closing) {
        return (opening == '(' && closing == ')')
                || (opening == '[' && closing == ']')
                || (opening == '{' && closing == '}')
                || (opening == '<' && closing == '>');
    }

    /**
     * Checks if the input string has balanced brackets.
     *
     * @throws IllegalArgumentException when input is null
     */
    public static boolean isBalanced(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        if (input.isEmpty()) {
            return true;
        }

        // Odd-length strings cannot be fully balanced
        if ((input.length() & 1) == 1) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : input.toCharArray()) {
            switch (c) {
                case '(', '[', '{', '<' -> stack.push(c);
 case ')',  ']', '}', '>' -> {
     if (stack.isEmpty() || !isPaired(stack.pop(), c)) {
         return false;
     }
                }
 default -> {
     // Any non-bracket character makes string invalid
     return false;
                }
            }
        }

        return stack.isEmpty();
    }
}