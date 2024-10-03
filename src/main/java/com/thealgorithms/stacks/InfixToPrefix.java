package com.thealgorithms.stacks;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InfixToPrefix {
    private InfixToPrefix() {
    }

    /**
     * Converts an infix expression to a prefix expression.
     *
     * @param infixExpression the infix expression to convert
     * @return the prefix expression
     * @throws Exception if the infix expression has unbalanced brackets
     */
    public static String infix2Prefix(String infixExpression) throws Exception {
        if (!BalancedBrackets.isBalanced(filterBrackets(infixExpression))) {
            throw new Exception("invalid expression");
        }
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        // Reverse the infix expression for prefix conversion
        String reversedInfix = new StringBuilder(infixExpression).reverse().toString();
        for (char element : reversedInfix.toCharArray()) {
            if (Character.isLetterOrDigit(element)) {
                output.append(element);
            } else if (element == ')') {
                stack.push(element);
            } else if (element == '(') {
                while (!stack.isEmpty() && stack.peek() != ')') {
                    output.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(element) < precedence(stack.peek())) {
                    output.append(stack.pop());
                }
                stack.push(element);
            }
        }
        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }

        // Reverse the result to get the prefix expression
        return output.reverse().toString();
    }

    /**
     * Determines the precedence of an operator.
     *
     * @param operator the operator whose precedence is to be determined
     * @return the precedence of the operator
     */
    private static int precedence(char operator) {
        switch (operator) {
        case '+':
        case '-':
            return 0;
        case '*':
        case '/':
            return 1;
        case '^':
            return 2;
        default:
            return -1;
        }
    }

    /**
     * Filters out all characters from the input string except brackets.
     *
     * @param input the input string to filter
     * @return a string containing only brackets from the input string
     */
    private static String filterBrackets(String input) {
        Pattern pattern = Pattern.compile("[^(){}\\[\\]<>]");
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll("");
    }
}
