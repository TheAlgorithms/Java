package com.thealgorithms.stacks;


// Postfix to Infix 
import java.util.*;
public class PostfixInfix{
    public static String postfixToInfix(String postfixExpression) {
        Stack<String> stack = new Stack<>();

        for (char c : postfixExpression.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            } else {
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                String result = "(" + operand1 + c + operand2 + ")";
                stack.push(result);
            }
        }

        if (stack.size() != 1) {
            return "Invalid postfix expression"; // More than one item left in the stack is an error
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        String postfixExpression = "abcd^e-fgh*+^*+i-";
        String infixExpression = postfixToInfix(postfixExpression);
        System.out.println("Postfix Expression: " + postfixExpression);
        System.out.println("Infix Expression: " + infixExpression);
    }
}

