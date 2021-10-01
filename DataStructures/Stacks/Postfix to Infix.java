import java.util.*;
import java.util.Scanner;

class postfix_to_Infix {

    static boolean is_operand(char x) {
        return ((x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z'));
    }
    static String convert_infix(String exp) {
        Stack < String > s = new Stack < String > ();
        for (int i = 0; i < exp.length(); i++) //iterate through the expression
        {
            if (is_operand(exp.charAt(i))) // If char is an operand we push it on stack
            {
                s.push(exp.charAt(i) + "");
            } else if (s.size() >= 2) // Else expect an operator
            {
                String op1 = s.peek();
                s.pop();
                String op2 = s.peek();
                s.pop();
                s.push("(" + op2 + exp.charAt(i) +
                    op1 + ")");
            }

        }
        if (s.isEmpty())
            s.push("error");
        return s.peek();
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the expression"); //We input the expression from user.
        String exp = sc.nextLine();
        System.out.println(convert_infix(exp));
    }
}
