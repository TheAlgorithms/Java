package com.thealgorithms.stacks;

import java.util.Set;
import java.util.Stack;

/**
 * Evaluate a postfix (Reverse Polish) expression using a stack.
 *
 * <p>Example: Expression "5 6 + 2 *" results in 22.
 * <p>Applications: Used in calculators and expression evaluation in compilers.
 *
 * @author Hardvan
 */
public final class PostfixEvaluator {
    private PostfixEvaluator() {
    }

    private static final Set<String> OPERATORS = Set.of("+", "-", "*", "/");

    /**
     * Evaluates the given postfix expression and returns the result.
     *
     * @param expression The postfix expression as a string with operands and operators separated by spaces.
     * @return The result of evaluating the postfix expression.
     * @throws IllegalArgumentException if the expression is invalid.
     */
    public static int evaluatePostfix(String expression) {
        Stack<Integer> stack = new Stack<>();

        for (String token : expression.split("\\s+")) {
            if (isOperator(token)) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
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
