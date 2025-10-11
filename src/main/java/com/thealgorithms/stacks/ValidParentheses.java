package com.thealgorithms.stacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * Utility class to check if a string has valid parentheses.
 */
public final class ValidParentheses {

    private ValidParentheses() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    private static final Map<Character, Character> PAIRS = Map.of(
            ')', '(', '}', '{', ']', '['
    );

    /**
     * Checks if the input string has valid parentheses.
     *
     * @param s the input string
     * @return true if valid, false otherwise
     */
    public static boolean isValid(final String s) {
        if (s == null) {
            throw new NullPointerException("Input cannot be null");
        }

        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (PAIRS.containsValue(ch)) { // opening bracket
                stack.push(ch);
            } else if (PAIRS.containsKey(ch)) { // closing bracket
                if (stack.isEmpty() || stack.pop() != PAIRS.get(ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
