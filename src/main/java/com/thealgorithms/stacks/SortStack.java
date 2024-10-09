package com.thealgorithms.stacks;

import java.util.Stack;

/**
 * A utility class that provides a method to sort a stack using recursion.
 * The elements are sorted in ascending order, with the largest element at the top.
 * This algorithm is implemented using only recursion and the original stack,
 * without utilizing any additional data structures apart from the stack itself.
 */
public final class SortStack {
    private SortStack() {
    }

    /**
     * Sorts the given stack in ascending order using recursion.
     * The sorting is performed such that the largest element ends up on top of the stack.
     * This method modifies the original stack and does not return a new stack.
     *
     * The algorithm works as follows:
     * 1. Remove the top element.
     * 2. Recursively sort the remaining stack.
     * 3. Insert the removed element back into the sorted stack at the correct position.
     *
     * @param stack The stack to be sorted, containing Integer elements.
     * @throws IllegalArgumentException if the stack contains `null` elements.
     */
    public static void sortStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        int top = stack.pop();
        sortStack(stack);
        insertInSortedOrder(stack, top);
    }

    /**
     * Helper method to insert an element into the correct position in a sorted stack.
     * This method is called recursively to place the given element into the stack
     * such that the stack remains sorted in ascending order.
     *
     * The element is inserted in such a way that all elements below it are smaller
     * (if the stack is non-empty), and elements above it are larger, maintaining
     * the ascending order.
     *
     * @param stack The stack in which the element needs to be inserted.
     * @param element The element to be inserted into the stack in sorted order.
     */
    private static void insertInSortedOrder(Stack<Integer> stack, int element) {
        if (stack.isEmpty() || element > stack.peek()) {
            stack.push(element);
            return;
        }

        int top = stack.pop();
        insertInSortedOrder(stack, element);
        stack.push(top);
    }
}
