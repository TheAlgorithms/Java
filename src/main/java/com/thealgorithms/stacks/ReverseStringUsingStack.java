package com.thealgorithms.stacks;

import java.util.Stack;

public final class ReverseStringUsingStack {
    private ReverseStringUsingStack() {
    }

    /**
     * @param str string to be reversed using stack
     * @return reversed string
     */
    public static String reverse(String str) {
        // Check if the input string is null
        if (str == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder reversedString = new StringBuilder();
        // Check if the input string is empty
        if (str.isEmpty()) {
            return str;
        }
        // Push each character of the string onto the stack
        for (char ch : str.toCharArray()) {
            stack.push(ch);
        }
        // Pop each character from the stack and append to the StringBuilder
        while (!stack.isEmpty()) {
            reversedString.append(stack.pop());
        }
        return reversedString.toString();
    }
}
