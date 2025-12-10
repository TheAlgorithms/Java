package com.thealgorithms.stacks;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for converting an infix arithmetic expression
 * into its equivalent prefix notation expression.
 * <p>
 * This class provides a static method to perform the conversion,
 * validating balanced brackets before processing.
 * </p>
 */
public final class InfixToPrefix {

    private InfixToPrefix() {
    }

    /**
     * Converts a given infix expression string to a prefix expression string.
     * <p>
     * The method validates that the input expression has balanced brackets using
     * {@code BalancedBrackets.isBalanced} on the filtered bracket characters.
     * It throws an {@code IllegalArgumentException} if the brackets are unbalanced,
     * and a {@code NullPointerException} if the input is null.
     * </p>
     * <p>
     * Supported operators: {@code +, -, *, /, ^} and operands can be letters or digits.
     * </p>
     *
     * @param infixExpression the arithmetic expression in infix notation
     * @return the equivalent prefix notation expression
     * @throws IllegalArgumentException if brackets are unbalanced
     * @throws NullPointerException     if the input expression is null
     */
    public static String infix2Prefix(String infixExpression) {
        if (infixExpression == null) {
            throw new NullPointerException("Input expression cannot be null.");
        }

        infixExpression = infixExpression.trim();
        if (infixExpression.isEmpty()) {
            return "";
        }

        if (!BalancedBrackets.isBalanced(filterBrackets(infixExpression))) {
            throw new IllegalArgumentException("Invalid expression: unbalanced brackets.");
        }

        StringBuilder output = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();

        // Reverse the infix expression to facilitate prefix conversion
        String reversedInfix = new StringBuilder(infixExpression).reverse().toString();

        for (char token : reversedInfix.toCharArray()) {
            if (Character.isLetterOrDigit(token)) {
                // Append operands directly to output
                output.append(token);
            } else if (token == ')') {
                // Push ')' onto stack (since expression is reversed, '(' and ')' roles swapped)
                operatorStack.push(token);
            } else if (token == '(') {
                // Pop operators until ')' is found
                while (!operatorStack.isEmpty() && operatorStack.peek() != ')') {
                    output.append(operatorStack.pop());
                }
                operatorStack.pop(); // Remove the ')'
            } else {
                // Pop operators with higher precedence before pushing current operator
                while (!operatorStack.isEmpty() && precedence(token) < precedence(operatorStack.peek())) {
                    output.append(operatorStack.pop());
                }
                operatorStack.push(token);
            }
        }

        // Append any remaining operators in stack
        while (!operatorStack.isEmpty()) {
            output.append(operatorStack.pop());
        }

        // Reverse the output to obtain the final prefix expression
        return output.reverse().toString();
    }

    /**
     * Returns the precedence level of the given operator.
     *
     * @param operator the operator character (e.g., '+', '-', '*', '/', '^')
     * @return the precedence value: higher means higher precedence,
     *         or -1 if the character is not a recognized operator
     */
    private static int precedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 0;
            case '*', '/' -> 1;
            case '^' -> 2;
            default -> -1;
        };
    }

    /**
     * Extracts only the bracket characters from the input string.
     * Supports parentheses (), curly braces {}, square brackets [], and angle brackets &lt;&gt;.
     *
     * @param input the original expression string
     * @return a string containing only bracket characters from the input
     */
    private static String filterBrackets(String input) {
        Pattern pattern = Pattern.compile("[^(){}\\[\\]<>]");
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll("");
    }
}
