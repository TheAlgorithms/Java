package com.thealgorithms.stacks;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Min-Stack implementation using a single stack.
 *
 * This stack supports push, pop, and retrieving the minimum element
 * in constant time (O(1)) using a modified approach where the stack
 * stores both the element and the minimum value so far.
 *
 * @author Hardvan
 */
public class MinStackUsingSingleStack {
    private final Stack<long[]> stack = new Stack<>();

    /**
     * Pushes a new value onto the stack.
     * Each entry stores both the value and the minimum value so far.
     *
     * @param value The value to be pushed onto the stack.
     */
    public void push(int value) {
        if (stack.isEmpty()) {
            stack.push(new long[] {value, value});
        } else {
            long minSoFar = Math.min(value, stack.peek()[1]);
            stack.push(new long[] {value, minSoFar});
        }
    }

    /**
     * Removes the top element from the stack.
     */
    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    /**
     * Retrieves the top element from the stack.
     *
     * @return The top element of the stack.
     */
    public int top() {
        if (!stack.isEmpty()) {
            return (int) stack.peek()[0];
        }
        throw new EmptyStackException();
    }

    /**
     * Retrieves the minimum element in the stack.
     *
     * @return The minimum element so far.
     */
    public int getMin() {
        if (!stack.isEmpty()) {
            return (int) stack.peek()[1];
        }
        throw new EmptyStackException();
    }
}
