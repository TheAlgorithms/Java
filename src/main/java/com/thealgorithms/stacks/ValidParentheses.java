package com.thealgorithms.stacks;

import java.util.Stack;

/**
 * Utility class to check for valid parentheses in a string.
 */
public final class ValidParentheses {

    private ValidParentheses() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    /**
     * Returns true if the input string has valid parentheses.
     *
     * @params the string containing parentheses
     * @return true if valid, false otherwise
     */
    public static boolean isValid(final String s) {
        final Stack<Character> stack = new Stack<>();

        for (final char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                final char top = stack.peek();
                if ((top == '(' && ch == ')')
                        || (top == '{' && ch == '}')
                        || (top == '[' && ch == ']')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(final String[] args) {
        System.out.println(isValid("()[]{}")); // true
        System.out.println(isValid("(]")); // false
        System.out.println(isValid("([)]")); // false
        System.out.println(isValid("{[]}")); // true
    }
}
