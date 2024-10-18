package com.thealgorithms.stacks;

import java.util.LinkedList;

/**
 * A class that implements a palindrome checker using a stack.
 * The stack is used to store the characters of the string,
 * which we will pop one-by-one to create the string in reverse.
 *
 * Reference: https://www.geeksforgeeks.org/check-whether-the-given-string-is-palindrome-using-stack/
 */
public class PalindromeWithStack {
    private LinkedList<Character> stack;

    /**
     * Constructs an empty stack that stores characters.
     */
    public PalindromeWithStack() {
        stack = new LinkedList<Character>();
    }

    /**
     * Check if the string is a palindrome or not.
     * Convert all characters to lowercase and push them into a stack.
     * At the same time, build a string
     * Next, pop from the stack and build the reverse string
     * Finally, compare these two strings
     *
     * @param string The string to check if it is palindrome or not.
     */
    public boolean checkPalindrome(String string) {
        // Create a StringBuilder to build the string from left to right
        StringBuilder stringBuilder = new StringBuilder(string.length());
        // Convert all characters to lowercase
        String lowercase = string.toLowerCase();

        // Iterate through the string
        for (int i = 0; i < lowercase.length(); ++i) {
            char c = lowercase.charAt(i);
            // Build the string from L->R
            stringBuilder.append(c);
            // Push to the stack
            stack.push(c);
        }

        // The stack contains the reverse order of the string
        StringBuilder reverseString = new StringBuilder(stack.size());
        // Until the stack is not empty
        while (!stack.isEmpty()) {
            // Build the string from R->L
            reverseString.append(stack.pop());
        }

        // Finally, compare the L->R string with the R->L string
        return reverseString.toString().equals(stringBuilder.toString());
    }
}
