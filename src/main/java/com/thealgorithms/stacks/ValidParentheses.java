package com.thealgorithms.stacks;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Valid Parentheses Problem
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 *
 * Examples:
 * Input: "()"
 * Output: true
 *
 * Input: "()[]{}"
 * Output: true
 *
 * Input: "(]"
 * Output: false
 *
 * Input: "([)]"
 * Output: false
 *
 * @author Gokul45-45
 */
public final class ValidParentheses {
    private ValidParentheses() {
    }

    /**
     * Checks if the given string has valid parentheses
     *
     * @param s the input string containing parentheses
     * @return true if valid, false otherwise
     */
    public static boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false;
        }

        Map<Character, Character> parenthesesMap = new HashMap<>();
        parenthesesMap.put('(', ')');
        parenthesesMap.put('{', '}');
        parenthesesMap.put('[', ']');

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (parenthesesMap.containsKey(c)) {
                // Opening bracket - push to stack
                stack.push(c);
            } else {
                // Closing bracket - check if it matches
                if (stack.isEmpty()) {
                    return false;
                }
                char openBracket = stack.pop();
                if (parenthesesMap.get(openBracket) != c) {
                    return false;
                }
            }
        }

        // Stack should be empty if all brackets are matched
        return stack.isEmpty();
    }
}
