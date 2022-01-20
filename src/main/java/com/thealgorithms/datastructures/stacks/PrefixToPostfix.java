package notations;

import java.util.*;

public class PrefixToPostfix {

    public static Scanner s = new Scanner(System.in);

    public static String convert(String prefix) {
        String postfix = "";
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (stack.peek() != '(') {
                    postfix += stack.pop();
                }
                stack.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!stack.isEmpty() && stack.peek() != '(' && precedence(c) <= precedence(stack.peek())) {
                    postfix += stack.pop();
                }
                stack.push(c);
            } else {
                postfix += c;
            }
        }
        while (!stack.isEmpty()) {
            postfix += stack.pop();
        }
        return postfix;
    }

    private static int precedence(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        String prefix = s.nextLine();
        System.out.println(convert(prefix));
    }
    
}
