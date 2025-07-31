package com.thealgorithms.stacks;

import java.util.Scanner;
import java.util.Stack;

public class ReverseString {

    /**
     * Main method to take user input and print the reversed string.
     */
    public static void main(String[] args) {

        // Create a Scanner object to read input from the user
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the String to reverse : ");
        String str = sc.nextLine();

        // Call the reverse method and print the reversed string
        System.out.println("Reversed String : " + reverse(str));
        sc.close();

    }

    /**
     * Reverses a string using a stack.
     * 
     * @param str The input string to reverse
     * @return The reversed string
     */
    public static String reverse(String str) {
        // StringBuilder to build the reversed string
        StringBuilder sb = new StringBuilder();
        // Stack to hold the characters of the string
        Stack<Character> st = new Stack<>();

        // Push each character of the string onto the stack
        for (int i = 0; i < str.length(); i++) {
            st.push(str.charAt(i));
        }

        // Pop characters from the stack and append to StringBuilder
        while (!st.empty()) {
            sb.append(st.pop());
        }
        // Convert StringBuilder to String and return
        String rev = sb.toString();
        return rev;
    }
}
