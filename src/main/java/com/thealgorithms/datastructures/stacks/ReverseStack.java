package com.thealgorithms.datastructures.stacks;

import java.util.Stack;

/**
 * Provides methods to reverse a stack using recursion.
 *
 * <p>This class includes methods to reverse the order of elements in a stack
 * without using additional data structures. Elements are inserted at the bottom
 * of the stack to achieve the reverse order.
 *
 * <p>Example usage:
 * <pre>
 *     Stack<Integer> stack = new Stack<>();
 *     stack.push(1);
 *     stack.push(2);
 *     stack.push(3);
 *     ReverseStack.reverseStack(stack);
 * </pre>
 * After calling {@code reverseStack(stack)}, the stack's order is reversed.
 *
 * <p>This class is final and has a private constructor to prevent instantiation.
 *
 * @author Ishika Agarwal, 2021
 */
public final class ReverseStack {
    private ReverseStack() {
    }

    /**
     * Reverses the order of elements in the given stack using recursion.
     * Steps:
     * 1. Check if the stack is empty. If so, return.
     * 2. Pop the top element from the stack.
     * 3. Recursively reverse the remaining stack.
     * 4. Insert the originally popped element at the bottom of the reversed stack.
     *
     * @param stack the stack to reverse; should not be null
     */
    public static void reverseStack(Stack<Integer> stack) {
        if (stack == null) {
            throw new IllegalArgumentException("Stack cannot be null");
        }
        if (stack.isEmpty()) {
            return;
        }

        int element = stack.pop();
        reverseStack(stack);
        insertAtBottom(stack, element);
    }

    /**
     * Inserts the specified element at the bottom of the stack.
     *
     * <p>This method is a helper for {@link #reverseStack(Stack)}.
     *
     * Steps:
     * 1. If the stack is empty, push the element and return.
     * 2. Remove the top element from the stack.
     * 3. Recursively insert the new element at the bottom of the stack.
     * 4. Push the removed element back onto the stack.
     *
     * @param stack the stack in which to insert the element; should not be null
     * @param element the element to insert at the bottom of the stack
     */
    private static void insertAtBottom(Stack<Integer> stack, int element) {
        if (stack.isEmpty()) {
            stack.push(element);
            return;
        }

        int topElement = stack.pop();
        insertAtBottom(stack, element);
        stack.push(topElement);
    }
}
