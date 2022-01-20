package notations;

import java.util.*;

public class PostfixEvaluation {

    public static boolean isdigit(char c) {
        return (c >= '0' && c <= '9');
    }

    public static boolean isoperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    public static int evaluatePostfix(String postfix) {
        
        int ans = 0;

        Stack<Integer> stack = new Stack<Integer>();
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            if (isdigit(token.charAt(0))) {
                stack.push(Integer.parseInt(token));
            } else {
                int op2 = stack.pop();
                int op1 = stack.pop();
                switch (token.charAt(0)) {
                case '+':
                    ans = op1 + op2;
                    break;
                case '-':
                    ans = op1 - op2;
                    break;
                case '*':
                    ans = op1 * op2;
                    break;
                case '/':
                    ans = op1 / op2;
                    break;
                }
                stack.push(ans);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter postfix expression: ");
        String postfix = sc.nextLine();
        System.out.println("Postfix expression: " + postfix);
        System.out.println("Postfix expression evaluation: " + evaluatePostfix(postfix));
        sc.close();
    }
}
