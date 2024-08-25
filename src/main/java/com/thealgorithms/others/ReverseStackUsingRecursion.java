package com.thealgorithms.others;

import java.util.Stack;

/**
 * Class that provides methods to reverse a stack using recursion.
 */
public final class ReverseStackUsingRecursion {
    private ReverseStackUsingRecursion() {
    }

    /**
     * Reverses the elements of the given stack using recursion.
     *
     * @param stack the stack to be reversed
     * @throws IllegalArgumentException if the stack is null
     */
    public static void reverse(Stack<Integer> stack) {
        if (stack == null) {
            throw new IllegalArgumentException("Stack cannot be null");
        }
        if (!stack.isEmpty()) {
            int topElement = stack.pop();
            reverse(stack);
            insertAtBottom(stack, topElement);
        }
    }

    /**
     * Inserts an element at the bottom of the given stack.
     *
     * @param stack    the stack where the element will be inserted
     * @param element  the element to be inserted at the bottom
     */
    private static void insertAtBottom(Stack<Integer> stack, int element) {
        if (stack.isEmpty()) {
            stack.push(element);
        } else {
            int topElement = stack.pop();
            insertAtBottom(stack, element);
            stack.push(topElement);
        }
    }
}
