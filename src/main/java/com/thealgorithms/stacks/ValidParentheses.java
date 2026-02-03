package com.thealgorithms.stacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * Valid Parentheses Problem (consolidated implementation).
 *
 * <p>Resolves duplicate implementations: single stack-based solution for validating
 * matching parentheses. Previously duplicated in strings and stacks packages.
 *
 * <p>Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * <p>An input string is valid if:
 * <ul>
 *   <li>Open brackets are closed by the same type of brackets.</li>
 *   <li>Open brackets are closed in the correct order.</li>
 *   <li>Every closing bracket has a corresponding open bracket of the same type.</li>
 * </ul>
 *
 * <p>Examples: "()" → true, "()[]{}" → true, "(]" → false, "([)]" → false.
 *
 * @author Gokul45-45
 */
public final class ValidParentheses {
    private ValidParentheses() {
    }

    // Map closing bracket to opening bracket for stack matching
    private static final Map<Character, Character> CLOSE_TO_OPEN = Map.of(')', '(', '}', '{', ']', '[');
    private static final Map<Character, Character> OPEN_TO_CLOSE = Map.of('(', ')', '{', '}', '[', ']');

    /**
     * Checks if the given string has valid matching parentheses using a stack.
     * Only the six bracket characters are allowed; any other character makes the string invalid.
     *
     * @param s the input string (only bracket characters allowed)
     * @return true if valid, false if null, odd length, contains non-bracket chars, or mismatched
     */
    public static boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (OPEN_TO_CLOSE.containsKey(c)) {
                stack.push(c);
            } else if (CLOSE_TO_OPEN.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != CLOSE_TO_OPEN.get(c)) {
                    return false;
                }
            } else {
                // Non-bracket character
                return false;
            }
        }

        return stack.isEmpty();
    }
}
