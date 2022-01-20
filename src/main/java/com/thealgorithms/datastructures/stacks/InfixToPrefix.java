package notations;

import java.util.*;

public class InfixToPrefix {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the infix expression");
        String infix = sc.nextLine();
        String prefix = infixToPrefix(infix);
        System.out.println("Prefix expression is " + prefix);
    }

    public static String infixToPrefix(String infix) {
        Stack<Character> stack = new Stack<Character>();
        String prefix = "";
        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    prefix += stack.pop();
                }
                stack.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {
                    prefix += stack.pop();
                }
                stack.push(ch);
            } else {
                prefix += ch;
            }
        }
        while (!stack.isEmpty()) {
            prefix += stack.pop();
        }
        return prefix;
    }

    public static int precedence(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == '*' || ch == '/') {
            return 2;
        } else {
            return 0;
        }
    }

}
