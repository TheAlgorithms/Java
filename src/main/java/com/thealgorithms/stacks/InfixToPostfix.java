package com.thealgorithms.stacks;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for converting an infix arithmetic expression
 * into its equivalent postfix (Reverse Polish Notation) form.
 * <p>
 * This class provides a static method to perform the conversion,
 * validating balanced brackets before processing.
 * </p>
 */
public final class InfixToPostfix {

    private InfixToPostfix() {
    }

    /**
     * Converts a given infix expression string to a postfix expression string.
     * <p>
     * The method first checks if the brackets in the input expression are balanced
     * by calling {@code BalancedBrackets.isBalanced} on the filtered brackets.
     * If the brackets are not balanced, it throws an IllegalArgumentException.
     * </p>
     * <p>
     * Supported operators are: {@code +, -, *, /, ^}
     * and operands can be letters or digits.
     * </p>
     *
     * @param infixExpression the arithmetic expression in infix notation
     * @return the equivalent postfix notation expression
     * @throws IllegalArgumentException if the brackets in the expression are unbalanced
     */
    public static String infix2PostFix(String infixExpression) {
        if (!BalancedBrackets.isBalanced(filterBrackets(infixExpression))) {
            throw new IllegalArgumentException("Invalid expression: unbalanced brackets.");
        }

        StringBuilder output = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();

        for (char token : infixExpression.toCharArray()) {
            if (Character.isLetterOrDigit(token)) {
                // Append operands (letters or digits) directly to output
                output.append(token);
            } else if (token == '(') {
                // Push '(' to stack
                operatorStack.push(token);
            } else if (token == ')') {
                // Pop and append until '(' is found
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    output.append(operatorStack.pop());
                }
                operatorStack.pop(); // Remove '(' from stack
            } else {
                // Pop operators with higher or equal precedence and append them
                while (!operatorStack.isEmpty() && precedence(token) <= precedence(operatorStack.peek())) {
                    output.append(operatorStack.pop());
                }
                operatorStack.push(token);
            }
        }

        // Pop any remaining operators
        while (!operatorStack.isEmpty()) {
            output.append(operatorStack.pop());
        }

        return output.toString();
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
