package notations;

import java.util.*;

public class InfixEvaluation {

    static final Scanner s = new Scanner(System.in);

    public int calculate(String expression) {

        if (expression == null || expression.isEmpty())
            return 0;
        int len = expression.length();
        Deque<Integer> stack = new ArrayDeque<>();
        int currentNumber = 0;
        char operation = '+';
        for (int i = 0; i < len; i++) {
            char currentChar = expression.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') {
                    stack.push(-currentNumber);
                } else if (operation == '+') {
                    stack.push(currentNumber);
                } else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (operation == '/') {
                    if (currentNumber == 0)
                        stack.push(0);
                    else
                        stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        int t = s.nextInt();
        while (t-- > 0) {
            String expresion = s.nextLine();
            System.out.println(new InfixEvaluation().calculate(expresion));
        }
    }

}
