package com.thealgorithms.stacks;

import java.util.Stack;

/**
 * Class for detecting unnecessary or redundant brackets in a mathematical expression.
 * Assumes the expression is balanced (i.e., all opening brackets have matching closing brackets).
 */
public final class DuplicateBrackets {
    private DuplicateBrackets() {
    }

    /**
     * Checks for extra or redundant brackets in a given expression.
     *
     * @param expression the string representing the expression to be checked
     * @return true if there are extra or redundant brackets, false otherwise
     * @throws IllegalArgumentException if the input string is null
     */
    public static boolean check(String expression) {
        if (expression == null) {
            throw new IllegalArgumentException("Input expression cannot be null.");
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == ')') {
                if (stack.isEmpty() || stack.peek() == '(') {
                    return true;
                }
                while (!stack.isEmpty() && stack.peek() != '(') {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(ch);
            }
        }
        return false;
    }
}
