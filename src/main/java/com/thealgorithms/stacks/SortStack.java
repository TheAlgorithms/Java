package com.thealgorithms.stacks;

import java.util.Stack;

/**
 * Class that provides a method to sort a stack using recursion.
 */
public class SortStack {

    /**
     * Sorts the provided stack using recursion. This method modifies the original stack
     * so that the elements are arranged in ascending order (largest on top).
     *
     * @param stack The stack to be sorted.
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
     * Inserts an element into the sorted stack at the correct position.
     *
     * @param stack The stack in which the element needs to be inserted.
     * @param element The element to be inserted in sorted order.
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
