package com.thealgorithms.strings;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * Validates if a given string has valid matching parentheses.
 * <p>
 * A string is considered valid if:
 * <ul>
 *     <li>Open brackets are closed by the same type of brackets.</li>
 *     <li>Brackets are closed in the correct order.</li>
 *     <li>Every closing bracket has a corresponding open bracket of the same type.</li>
 * </ul>
 *
 * Allowed characters: '(', ')', '{', '}', '[', ']'
 */
public final class ValidParentheses {
    private ValidParentheses() {
    }

    private static final Map<Character, Character> BRACKET_PAIRS = Map.of(')', '(', '}', '{', ']', '[');

    /**
     * Checks if the input string has valid parentheses.
     *
     * @param s the string containing only bracket characters
     * @return true if valid, false otherwise
     * @throws IllegalArgumentException if the string contains invalid characters or is null
     */
    public static boolean isValid(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (BRACKET_PAIRS.containsValue(c)) {
                stack.push(c); // opening bracket
            } else if (BRACKET_PAIRS.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != BRACKET_PAIRS.get(c)) {
                    return false;
                }
            } else {
                throw new IllegalArgumentException("Unexpected character: " + c);
            }
        }

        return stack.isEmpty();
    }
}
