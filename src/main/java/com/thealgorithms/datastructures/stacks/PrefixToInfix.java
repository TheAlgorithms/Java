package notations;

import java.util.*;

public class PrefixToInfix {
    
    //static scannner
    public static Scanner sc = new Scanner(System.in);

    public static String convert(String prefix) {
        String infix = "";
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < prefix.length(); i++) {
            String c = prefix.substring(i, i + 1);
            if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")) {
                String b = stack.pop();
                String a = stack.pop();
                stack.push("(" + a + c + b + ")");
            } else {
                stack.push(c);
            }
        }
        infix = stack.pop();
        return infix;
    }

    public static void main(String[] args) {
        String prefix = sc.nextLine();
        System.out.println(convert(prefix));
    }
}
