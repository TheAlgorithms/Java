package com.thealgorithms.stacks;

import java.util.Stack;

/**
 * Min-Stack implementation that supports push, pop, and retrieving the minimum element in constant time.
 *
 * @author Hardvan
 */
public final class MinStackUsingTwoStacks {
    MinStackUsingTwoStacks() {
    }

    private final Stack<Integer> stack = new Stack<>();
    private final Stack<Integer> minStack = new Stack<>();

    /**
     * Pushes a new element onto the {@code stack}.
     * If the value is less than or equal to the current minimum, it is also pushed onto the {@code minStack}.
     *
     * @param value The value to be pushed.
     */
    public void push(int value) {
        stack.push(value);
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    /**
     * Removes the top element from the stack.
     * If the element is the minimum element, it is also removed from the {@code minStack}.
     */
    public void pop() {
        if (stack.pop().equals(minStack.peek())) {
            minStack.pop();
        }
    }

    /**
     * Retrieves the top element of the stack.
     *
     * @return The top element.
     */
    public int top() {
        return stack.peek();
    }

    /**
     * Retrieves the minimum element in the stack.
     *
     * @return The minimum element.
     */
    public int getMin() {
        return minStack.peek();
    }
}
