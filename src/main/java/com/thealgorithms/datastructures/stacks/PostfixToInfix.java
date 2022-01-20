package notations;

import java.util.*;

public class PostfixToInfix {

    public static Scanner s = new Scanner(System.in);

    public static String convert(String postfix) {
        String infix = "";
        Stack<String> stack = new Stack<String>();
        String[] tokens = postfix.split(" ");
        for (String token : tokens) {
            
            if (isOperator(token)) {
                String op2 = stack.pop();
                String op1 = stack.pop();
                infix = "(" + op1 + token + op2 + ")";
                stack.push(infix);
            } else {
                stack.push(token);
            }
        }
        return stack.pop();
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    public static void main(String[] args) {
        String postfix = s.nextLine();
        System.out.println(convert(postfix));
    }
    
}
