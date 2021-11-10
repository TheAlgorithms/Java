package com.thealgorithms.datastructures.stacks;

import java.util.Stack;

public class InfixToPostfix {

    public static void main(String[] args) throws Exception {
        assert "32+".equals(infix2PostFix("3+2"));
        assert "123++".equals(infix2PostFix("1+(2+3)"));
        assert "34+5*6-".equals(infix2PostFix("(3+4)*5-6"));
    }

    public static String infix2PostFix(String infixExpression) throws Exception {
        if (!BalancedBrackets.isBalanced(infixExpression)) {
            throw new Exception("invalid expression");
        }
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char element : infixExpression.toCharArray()) {
            if (Character.isLetterOrDigit(element)) {
                output.append(element);
            } else if (element == '(') {
                stack.push(element);
            } else if (element == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(element) <= precedence(stack.peek())) {
                    output.append(stack.pop());
                }
                stack.push(element);
            }
        }
        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }
        return output.toString();
    }

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
}
