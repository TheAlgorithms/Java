package com.thealgorithms.others;

import java.util.Scanner;
import java.util.Stack;

public final class StackPostfixNotation {
    private StackPostfixNotation() {
    }

    // Evaluates the given postfix expression string and returns the result.
    public static int postfixEvaluate(final String exp) {
        Stack<Integer> s = new Stack<Integer>();
        Scanner tokens = new Scanner(exp);

        while (tokens.hasNext()) {
            if (tokens.hasNextInt()) {
                s.push(tokens.nextInt()); // If int then push to stack
            } else { // else pop top two values and perform the operation
                if (s.size() < 2) {
                    throw new IllegalArgumentException("exp is not a proper postfix expression (too few arguments).");
                }
                int num2 = s.pop();
                int num1 = s.pop();
                String op = tokens.next();

                switch (op) {
                    case "+" -> s.push(num1 + num2);
                    case "-" -> s.push(num1 - num2);
                    case "*" -> s.push(num1 * num2);
                    case "/" -> s.push(num1 / num2);
                    default -> throw new IllegalArgumentException("exp contains an unknown operation.");
                }
                //  "+", "-", "*", "/"
            }
        }
        tokens.close();
        if (s.size() != 1) {
            throw new IllegalArgumentException("exp is not a proper postfix expression.");
        }
        return s.pop();
    }
}
