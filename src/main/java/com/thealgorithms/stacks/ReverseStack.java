package com.thealgorithms.stacks;

import java.util.Stack;
/**
 * The ReverseStack class provides a method to reverse a given stack of integers
 * using an auxiliary stack. It demonstrates how to reverse the order of elements
 * in a stack while maintaining their integrity.
 *
 * Usage:
 * - Create a stack and push elements onto it.
 * - Use the reverseStack() method to reverse the stack.
 * - The reversed stack will have its elements in the opposite order.
 *
 * Example:
 * ```
 * Stack<Integer> stack = new Stack<>();
 * stack.push(1);
 * stack.push(2);
 * stack.push(3);
 *
 * System.out.println("Original Stack: " + stack);
 *
 * // Reverse the stack
 * ReverseStack.reverseStack(stack);
 *
 * System.out.println("Reversed Stack: " + stack);
 * ```
 *
 * Output:
 * Original Stack: [1, 2, 3]
 * Reversed Stack: [3, 2, 1]

@author sheetal neeraj
@author <a href = "https://github.com/sheetalneeraj">sheetalneeraj<a>
 */

public class ReverseStack {
    // Function to reverse a given stack using another stack
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        // Create an auxiliary stack
        Stack<Integer> auxStack = new Stack<>();

        // Transfer elements from the original stack to the auxiliary stack
        while (!stack.isEmpty()) {
            auxStack.push(stack.pop());
        }

        // Transfer elements back from the auxiliary stack to the original stack, reversing their order
        while (!auxStack.isEmpty()) {
            stack.push(auxStack.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Original Stack: " + stack);

        // Reverse the stack
        reverseStack(stack);

        System.out.println("Reversed Stack: " + stack);
    }
}
