package com.thealgorithms.datastructures.stacks;

import java.util.Deque;

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
        // create the StringBuilder variable to return at last
        StringBuilder output = new StringBuilder();
        // creating character stack using Deque for better inner implementation it also can be using Stack but Deque is more prefered.
        Deque<Character> stack = new ArrayDeque<>();
        for (char element : infixExpression.toCharArray()) {
            // if element is operand then just append it in output
            if (Character.isLetterOrDigit(element)) {
                output.append(element);
            } else if (element == '(') {
                // if it is opening bracket '(' then push it in stack
                stack.push(element);
            } else if (element == ')') {
                // if closing bracket ')' then pop all the elements in stack until we find the top/peek element as '('
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop());
                }
                // and finally pop the '('.
                stack.pop();
            } else {
                // if it is an operator like + , - , * , / then 
                
                /* first if stack is not empty and precedence of current element is not greater than top/peek of stack then pop from the stack until
                 these 2 conditions */
                while (!stack.isEmpty() && precedence(element) <= precedence(stack.peek())) {
                    output.append(stack.pop());
                }
                // and now top's precedence is greater than the our current element so push it in the stack
                stack.push(element);
            }
        }
        // now all the elements covered now keep popping from the stack until it is not empty and append it in the answer.
        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }
        // finally return the answer in casting it in string using stringbuilder method.
        return output.toString();
    }

    private static int precedence(char operator) {
        // precedence order : ^ > / == * > + == -
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
                // if non of them then return -1
                return -1;
        }
    }
}
