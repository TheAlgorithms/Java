package com.thealgorithms.stacks;

import java.util.Stack;

/**
 * Utility class to evaluate a basic arithmetic expression containing
 * non-negative integers, '+', '-', parentheses '(', ')', and spaces.
 *
 * <p>The evaluation is done using two stacks:
 * one for operands and one for operators.
 *
 * <p>This class cannot be instantiated.</p>
 */
public final class BasicCalculatorUsingStack {

    private BasicCalculatorUsingStack() {
    }

    /**
     * Evaluates a mathematical expression.
     *
     * @param expression the input expression
     * @return the evaluated result
     * @throws IllegalArgumentException if the expression is null or empty
     */
    public static int evaluate(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("Expression must not be null or empty.");
        }

        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);

            if (current == ' ') {
                continue;
            }

            if (Character.isDigit(current)) {
                int number = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    number = number * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                i--;
                operands.push(number);
            } else if (current == '(') {
                operators.push(current);
            } else if (current == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    applyOperation(operands, operators);
                }
                operators.pop(); // remove '('
            } else if (current == '+' || current == '-') {
                if (isUnaryMinus(expression, i)) {
                    operands.push(0);
                }
                while (!operators.isEmpty() && operators.peek() != '(') {
                    applyOperation(operands, operators);
                }
                operators.push(current);
            }
        }

        while (!operators.isEmpty()) {
            applyOperation(operands, operators);
        }

        return operands.pop();
    }

    private static void applyOperation(Stack<Integer> operands, Stack<Character> operators) {
        int b = operands.pop();
        int a = operands.pop();
        char operator = operators.pop();
        operands.push(operator == '+' ? a + b : a - b);
    }

    private static boolean isUnaryMinus(String expression, int index) {
        int j = index - 1;
        while (j >= 0 && expression.charAt(j) == ' ') {
            j--;
        }
        return j < 0 || expression.charAt(j) == '('
            || expression.charAt(j) == '+'
            || expression.charAt(j) == '-';
    }
}
