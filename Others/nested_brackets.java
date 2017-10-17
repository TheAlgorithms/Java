/*

The nested brackets problem is a problem that determines if a sequence of
brackets are properly nested.  A sequence of brackets s is considered properly nested
if any of the following conditions are true:
	- s is empty
	- s has the form (U) or [U] or {U} where U is a properly nested string
	- s has the form VW where V and W are properly nested strings
For example, the string "()()[()]" is properly nested but "[(()]" is not.
The function called is_balanced takes as input a string S which is a sequence of brackets and
returns true if S is nested and false otherwise.

	author: akshay sharma
	date: 2017-10-17
*/
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;

class nested_brackets {

    static boolean is_balanced(char[] S) {
        Stack<Character> stack = new Stack<>();
        String pair = "";
        for (int i = 0; i < S.length; ++i) {
            if (S[i] == '(' || S[i] == '{' || S[i] == '[') {
                stack.push(S[i]);
            } else if (stack.size() > 0) {
//                pair = (stack.lastElement() + S[i]);
                if (!pair.equals("[]") && !pair.equals("()") && !pair.equals("{}")) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }

    static void print(Object a) {
        System.out.println(a);
    }

    public static void main(String args[]) {
        try {
            Scanner in = new Scanner(System.in);
            print("Enter sequence of brackets: ");
            String S = in.nextLine();
            if (is_balanced(S.toCharArray())) {
                print(S + " is balanced");
            } else {
                print(S + " ain't balanced");
            }
            in.close();
        } catch (Exception e) {
            e.toString();
        }
    }
}
