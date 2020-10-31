import java.util.*;

class Solution {
    
    private static boolean isBalanced(String expression) {
        if ((expression.length() & 1) == 1)
            return false;

        char[] brackets = expression.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char bracket : brackets)
            switch (bracket) {
                case '{':
                    stack.push('}');
                    break;
                case '(':
                    stack.push(')');
                    break;
                case '[':
                    stack.push(']');
                    break;
                default:
                    if (stack.empty() || bracket != stack.peek())
                        return false;
                    stack.pop();
            }
        return stack.empty();
    }

    public static void main(String[] argh) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String expression = sc.next();
            System.out.println(isBalanced(expression) ? "true" : "false");
        }
        sc.close();
    }
}
