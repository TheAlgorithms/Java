package com.thealgorithms.strings;

import java.util.Stack;

/**
 * A utility class to reverse a given string using a Stack.
 */
public final class ReverseStringUsingStack {

    // Private constructor to prevent instantiation of utility class
    private ReverseStringUsingStack() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Reverses the input string using a Stack.
     *
     * @param str the input string to be reversed
     * @return the reversed string
     */
    public static String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        // Push each character of the string into the stack
        for (int i = 0; i < str.length(); ++i) {
            stack.push(str.charAt(i));
        }

        // Pop each character from the stack and append to StringBuilder
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        // Return the reversed string
        return sb.toString();
    }
}
