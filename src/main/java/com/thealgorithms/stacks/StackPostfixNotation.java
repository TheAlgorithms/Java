package com.thealgorithms.stacks;

import java.util.Scanner;
import java.util.Stack;
import java.util.function.BiFunction;

/**
 * @brief Utility class evaluating postix expressions, cf. https://en.wikipedia.org/wiki/Reverse_Polish_notation
 * @details The computation is done using Integers.
 */
public final class StackPostfixNotation {
    private StackPostfixNotation() {
    }

    private static BiFunction<Integer, Integer, Integer> getOperator(final String operationSymbol) {
        // note the order of operands
        switch (operationSymbol) {
        case "+":
            return (a, b) -> b + a;
        case "-":
            return (a, b) -> b - a;
        case "*":
            return (a, b) -> b * a;
        case "/":
            return (a, b) -> b / a;
        default:
            throw new IllegalArgumentException("exp contains an unknown operation.");
        }
    }

    private static void performOperation(Stack<Integer> s, final String operationSymbol) {
        if (s.size() < 2) {
            throw new IllegalArgumentException("exp is not a proper postfix expression (too few arguments).");
        }
        s.push(getOperator(operationSymbol).apply(s.pop(), s.pop()));
    }

    private static void consumeExpression(Stack<Integer> s, final String exp) {
        Scanner tokens = new Scanner(exp);

        while (tokens.hasNext()) {
            if (tokens.hasNextInt()) {
                s.push(tokens.nextInt());
            } else {
                performOperation(s, tokens.next());
            }
        }
        tokens.close();
    }

    /**
     * @brief Evaluates the given postfix expression.
     * @param exp the expression to evaluate.
     * @return the value of the given expression.
     * @exception IllegalArgumentException exp is not a valid postix expression.
     */
    public static int postfixEvaluate(final String exp) {
        Stack<Integer> s = new Stack<Integer>();
        consumeExpression(s, exp);
        if (s.size() != 1) {
            throw new IllegalArgumentException("exp is not a proper postfix expression.");
        }
        return s.pop();
    }
}
