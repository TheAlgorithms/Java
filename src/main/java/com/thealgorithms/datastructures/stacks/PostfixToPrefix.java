package notations;

import java.util.*;

public class PostfixToPrefix {
    
    public static Scanner s = new Scanner(System.in);

    public static String convert(String postfix) {
        String prefix = "";
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                prefix += c;
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                prefix += " " + op1 + " " + op2 + " " + c;
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        String postfix = s.nextLine();
        System.out.println(convert(postfix));
    }
}
