package com.thealgorithms.stacks;

import java.util.Stack;

/**
 * Prefix to Infix implementation via Stack
 *
 * Function: String getPrefixToInfix(String prefix)
 * Returns the Infix Expression for the given prefix parameter.
 *
 * Avoid using parentheses/brackets/braces for the prefix string.
 * Prefix Expressions don't require these.
 */

public final class PrefixToInfix {
    private PrefixToInfix() {
    }

    /**
     * Determines if a given character is a valid arithmetic operator.
     *
     * @param token the character to check
     * @return true if the character is an operator, false otherwise
     */
    public static boolean isOperator(char token) {
        return token == '+' || token == '-' || token == '/' || token == '*' || token == '^';
    }

    /**
     * Converts a valid prefix expression to an infix expression.
     *
     * @param prefix the prefix expression to convert
     * @return the equivalent infix expression
     * @throws NullPointerException     if the prefix expression is null
     */
    public static String getPrefixToInfix(String prefix) {
        if (prefix == null) {
            throw new NullPointerException("Null prefix expression");
        }
        if (prefix.isEmpty()) {
            return "";
        }

        Stack<String> stack = new Stack<>();

        // Iterate over the prefix expression from right to left
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char token = prefix.charAt(i);

            if (isOperator(token)) {
                // Pop two operands from stack
                String operandA = stack.pop();
                String operandB = stack.pop();

                // Form the infix expression with parentheses
                String infix = "(" + operandA + token + operandB + ")";

                // Push the resulting infix expression back onto the stack
                stack.push(infix);
            } else {
                // Push operand onto stack
                stack.push(Character.toString(token));
            }
        }

        return stack.pop(); // final element on the stack is the full infix expression
    }
}
