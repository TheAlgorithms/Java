package com.thealgorithms.datastructures.stacks;

import java.util.Scanner;
import java.util.Stack;

/**
 * Reversal of a stack using recursion.
 *
 * @author Ishika Agarwal, 2021
 */
public final class ReverseStack {
    private ReverseStack() {
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the number of elements you wish to insert in the stack");
            int n = sc.nextInt();
            int i;
            Stack<Integer> stack = new Stack<Integer>();
            System.out.println("Enter the stack elements");
            for (i = 0; i < n; i++) {
                stack.push(sc.nextInt());
            }
            reverseStack(stack);
            System.out.println("The reversed stack is:");
            while (!stack.isEmpty()) {
                System.out.print(stack.peek() + ",");
                stack.pop();
            }
        }
    }

    private static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        // Store the topmost element
        int element = stack.peek();
        // Remove the topmost element
        stack.pop();

        // Reverse the stack for the leftover elements
        reverseStack(stack);

        // Insert the topmost element to the bottom of the stack
        insertAtBottom(stack, element);
    }

    private static void insertAtBottom(Stack<Integer> stack, int element) {
        if (stack.isEmpty()) {
            // When stack is empty, insert the element so it will be present at
            // the bottom of the stack
            stack.push(element);
            return;
        }

        int ele = stack.peek();
        // Keep popping elements till stack becomes empty. Push the elements
        // once the topmost element has moved to the bottom of the stack.
        stack.pop();
        insertAtBottom(stack, element);

        stack.push(ele);
    }
}
