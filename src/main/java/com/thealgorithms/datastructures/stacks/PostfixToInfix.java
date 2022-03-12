package com.thealgorithms.datastructures.stacks;

import java.util.Stack;

/**
 * Postfix to Infix implementation via Stack
 *
 * Function: String getPostfixToInfix(String postfix)
 * Returns the Infix Expression for the given postfix parameter.
 *
 * Avoid using parentheses/brackets/braces for the postfix string.
 * Postfix Expressions don't require these.
 *
 *
 * @author nikslyon19 (Nikhil Bisht)
 *
 */

public class PostfixToInfix {


    public static boolean isOperator(char token)
    {
        switch(token)
        {
            case '+':
            case '-':
            case '/':
            case '*':
            case '^':
                return true;
        }

        return false;
    }


    public static boolean isValidPostfixExpression(String postfix)
    {
        /* Postfix expression length should NOT be less than 3 */
        if(postfix.length() < 3) return false;


        /* First two characters should NOT be operators */
        if(isOperator(postfix.charAt(0))) return false;
        if(isOperator(postfix.charAt(1))) return false;


        int operandCount = 0;
        int operatorCount = 0;


        /* Traverse the postfix string to check if --> Number of operands = Number of operators + 1 */
        for(int i = 0; i < postfix.length(); i++)
        {
            char token = postfix.charAt(i);

            if(isOperator(token))
            {
                operatorCount++;
                if(operatorCount >= operandCount) return false;
            }

            else
            {
                if(operatorCount == 0)
                {
                    operandCount++;
                    continue;
                }

                if(operandCount != operatorCount + 1) return false;


                /* Operand count is set to 2 because:-
                 *
                 * 1) the previous set of operands & operators combined have become a single valid expression,
                 * which could be considered/assigned as a single operand.
                 *
                 * 2) the operand in the current iteration.
                 */
                operandCount = 2;


                /* Reset operator count */
                operatorCount = 0;
            }
        }

        return (operandCount == operatorCount + 1);
    }


    public static String getPostfixToInfix(String postfix)
    {
        String infix = "";

        if(postfix.isEmpty()) return infix;


        /* Validate Postfix expression before proceeding with the Infix conversion */
        if(!isValidPostfixExpression(postfix))
        {
            throw new IllegalArgumentException("Invalid Postfix Expression");
        }

        Stack<String> stack = new Stack<>();
        StringBuilder valueString = new StringBuilder();

        String operandA, operandB;
        char operator;


        for(int index = 0; index < postfix.length(); index++)
        {
            char token = postfix.charAt(index);

            if(!isOperator(token))
            {
                stack.push(Character.toString(token));
                continue;
            }

            operator = token;
            operandB = stack.pop();
            operandA = stack.pop();

            valueString.append('(');

            valueString.append(operandA);
            valueString.append(operator);
            valueString.append(operandB);

            valueString.append(')');

            stack.push(valueString.toString());
            valueString.setLength(0);
        }

        infix = stack.pop();
        return infix;
    }


    public static void main(String args[])
    {
        assert getPostfixToInfix("ABC+/").equals("(A/(B+C))");
        assert getPostfixToInfix("AB+CD+*").equals("((A+B)*(C+D))");
        assert getPostfixToInfix("AB+C+D+").equals("(((A+B)+C)+D)");
        assert getPostfixToInfix("ABCDE^*/-").equals("(A-(B/(C*(D^E))))");
        assert getPostfixToInfix("AB+CD^/E*FGH+-^").equals("((((A+B)/(C^D))*E)^(F-(G+H)))");
    }
}
