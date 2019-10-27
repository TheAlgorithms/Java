<<<<<<< HEAD
package data_structures.Stacks;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * The nested brackets problem is a problem that determines if a sequence of
 * brackets are properly nested. A sequence of brackets s is considered properly
 * nested if any of the following conditions are true: - s is empty - s has the
 * form (U) or [U] or {U} where U is a properly nested string - s has the form
 * VW where V and W are properly nested strings For example, the string
 * "()()[()]" is properly nested but "[(()]" is not. The function called
 * is_balanced takes as input a string S which is a sequence of brackets and
 * returns true if S is nested and false otherwise.
 *
 * @author akshay sharma
 * @date: 2017-10-17
 * @author <a href="https://github.com/khalil2535">khalil2535<a>
 *
 */
class BalancedBrackets {

    /**
     *
     * @param s
     * @return
     */
    static boolean is_balanced(String s) {
        Stack<Character> bracketsStack = new Stack<>();
        char[] text = s.toCharArray();
        for (char x : text) {
            switch (x) {
                case '{':
                case '<':
                case '(':
                case '[':
                    bracketsStack.push(x);
                    break;
                case '}':
                    if (bracketsStack.peek() == '{') {
                        bracketsStack.pop();
                        break;
                    } else {
                        return false;
                    }
                case '>':
                    if (bracketsStack.peek() == '<') {
                        bracketsStack.pop();
                        break;
                    } else {
                        return false;
                    }
                case ')':
                    if (bracketsStack.peek() == '(') {
                        bracketsStack.pop();
                        break;
                    } else {
                        return false;
                    }
                case ']':
                    if (bracketsStack.peek() == '[') {
                        bracketsStack.pop();
                        break;
                    } else {
                        return false;
                    }
            }
        }
        return bracketsStack.empty();
    }

    /**
     *
     * @param args
     * @TODO remove main method and Test using JUnit or other methodology
     */
    public static void main(String args[]) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter sequence of brackets: ");
            String s = in.nextLine();
            if (is_balanced(s)) {
                System.out.println(s + " is balanced");
            } else {
                System.out.println(s + " ain't balanced");
            }
        }
    }
}
=======
package DataStructures.Stacks;

import java.util.Stack;

/**
 * The nested brackets problem is a problem that determines if a sequence of
 * brackets are properly nested. A sequence of brackets s is considered properly
 * nested if any of the following conditions are true: - s is empty - s has the
 * form (U) or [U] or {U} where U is a properly nested string - s has the form
 * VW where V and W are properly nested strings For example, the string
 * "()()[()]" is properly nested but "[(()]" is not. The function called
 * is_balanced takes as input a string S which is a sequence of brackets and
 * returns true if S is nested and false otherwise.
 *
 * @author akshay sharma
 * @author <a href="https://github.com/khalil2535">khalil2535<a>
 * @author shellhub
 */
class BalancedBrackets {

    /**
     * Check if {@code leftBracket} and {@code rightBracket} is paired or not
     *
     * @param leftBracket   left bracket
     * @param rightBracket right bracket
     * @return {@code true} if {@code leftBracket} and {@code rightBracket} is paired,
     * otherwise {@code false}
     */
    public static boolean isPaired(char leftBracket, char rightBracket) {
        char[][] pairedBrackets = {
                {'(', ')'},
                {'[', ']'},
                {'{', '}'},
                {'<', '>'}
        };
        for (char[] pairedBracket : pairedBrackets) {
            if (pairedBracket[0] == leftBracket && pairedBracket[1] == rightBracket) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if {@code brackets} is balanced
     *
     * @param brackets the brackets
     * @return {@code true} if {@code brackets} is balanced, otherwise {@code false}
     */
    public static boolean isBalanced(String brackets) {
        if (brackets == null) {
            throw new IllegalArgumentException("brackets is null");
        }
        Stack<Character> bracketsStack = new Stack<>();
        for (char bracket : brackets.toCharArray()) {
            switch (bracket) {
                case '(':
                case '[':
                case '{':
                    bracketsStack.push(bracket);
                    break;
                case ')':
                case ']':
                case '}':
                    if (!(!bracketsStack.isEmpty() && isPaired(bracketsStack.pop(), bracket))) {
                        return false;
                    }
                    break;
                default: /* other character is invalid */
                    return false;
            }
        }
        return bracketsStack.isEmpty();
    }


    public static void main(String[] args) {
        assert isBalanced("[()]{}{[()()]()}");
        assert !isBalanced("[(])");
    }
}
>>>>>>> 66410c126e598a1f718fdba749fbecbdca8130b9
