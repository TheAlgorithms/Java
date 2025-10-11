package com.thealgorithms.stacks;

import java.util.Stack;

/**
 * Utility class for checking if a given string of parentheses is valid.
 * <p>
 * A string containing just the characters '(', ')', '{', '}', '[' and ']' is valid if:
 * <ul>
 *   <li>Open brackets are closed by the same type of brackets.</li>
 *   <li>Open brackets are closed in the correct order.</li>
 * </ul>
 * <p>
 * Example:
 * <pre>
 *   Input: "()[]{}"
 *   Output: true
 * </pre>
 * <p>
 * For more details, see
 * <a href="https://leetcode.com/problems/valid-parentheses/">LeetCode: Valid Parentheses</a>.
 * </p>
 */
public final class ValidParentheses {

    private ValidParentheses() {
        // prevent instantiation
    }

    /**
     * Checks whether the input string has valid parentheses.
     *
     * @param str input string containing parentheses
     * @return true if the string is valid, false otherwise
     */
    public static boolean isValid(final String str) {
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.peek();
                if ((top == '(' && ch == ')')
                        || (top == '{' && ch == '}')
                        || (top == '[' && ch == ']')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * Example usage.
     */
    public static void main(final String[] args) {
        System.out.println(isValid("()[]{}")); // true
        System.out.println(isValid("(]"));     // false
        System.out.println(isValid("([)]"));   // false
        System.out.println(isValid("{[]}"));   // true
    }
}
