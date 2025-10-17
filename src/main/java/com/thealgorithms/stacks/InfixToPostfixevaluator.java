import java.util.Stack;

public class InfixToPostfix {
    
    // Function to check operator precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Function to convert infix to postfix
    static String convert(String expression) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // If character is an operand, add it to output
            if (Character.isLetterOrDigit(c)) {
                result += c;
            }

            // If character is '(', push it to stack
            else if (c == '(') {
                stack.push(c);
            }

            // If character is ')', pop until '(' is found
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop(); // remove '('
            }

            // If character is operator
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result += stack.pop();
                }
                stack.push(c);
            }
        }

        // Pop all remaining operators from stack
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // Main method to test the program
    public static void main(String[] args) {
        String infix = "A*(B+C)/D";
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + convert(infix));
    }
}
