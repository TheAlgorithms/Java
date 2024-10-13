package com.thealgorithms.stacks;

import java.util.Set;
import java.util.Stack;

/**
 * Evaluate a prefix (Polish) expression using a stack.
 *
 * <p>Example: Expression "+ * 2 3 4" results in 10.
 * <p>Applications: Useful for implementing compilers and interpreters.
 *
 * @author Hardvan
 */
public final class PrefixEvaluator {
    private PrefixEvaluator() {
    }

    private static final Set<String> OPERATORS = Set.of("+", "-", "*", "/");

    /**
     * Evaluates the given prefix expression and returns the result.
     *
     * @param expression The prefix expression as a string with operands and operators separated by spaces.
     * @return The result of evaluating the prefix expression.
     * @throws IllegalArgumentException if the expression is invalid.
     */
    public static int evaluatePrefix(String expression) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = expression.split("\\s+");

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            if (isOperator(token)) {
                int operand1 = stack.pop();
                int operand2 = stack.pop();
                stack.push(applyOperator(token, operand1, operand2));
            } else {
                stack.push(Integer.valueOf(token));
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return stack.pop();
    }

    /**
     * Checks if the given token is an operator.
     *
     * @param token The token to check.
     * @return true if the token is an operator, false otherwise.
     */
    private static boolean isOperator(String token) {
        return OPERATORS.contains(token);
    }

    /**
     * Applies the given operator to the two operands.
     *
     * @param operator The operator to apply.
     * @param a The first operand.
     * @param b The second operand.
     * @return The result of applying the operator to the operands.
     */
    private static int applyOperator(String operator, int a, int b) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalArgumentException("Invalid operator");
        };
    }
}
