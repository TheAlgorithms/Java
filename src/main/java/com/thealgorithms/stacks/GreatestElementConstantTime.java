package com.thealgorithms.stacks;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A class that implements a stack that gives the maximum element in O(1) time.
 * The mainStack is used to store the all the elements of the stack
 * While the maxStack stores the maximum elements
 * When we want to get a maximum element, we call the top of the maximum stack
 *
 * Problem: https://www.baeldung.com/cs/stack-constant-time
 */
public class GreatestElementConstantTime {
    private Stack<Integer> mainStack; // initialize a mainStack
    private Stack<Integer> maxStack; // initialize a maxStack

    /**
     * Constructs two empty stacks
     */
    public GreatestElementConstantTime() {
        mainStack = new Stack<>();
        maxStack = new Stack<>();
    }

    /**
     * Pushes an element onto the top of the stack.
     * Checks if the element is the maximum or not
     * If so, then pushes to the maximum stack
     * @param data The element to be pushed onto the stack.
     */
    public void push(int data) {
        if (mainStack.isEmpty()) {
            mainStack.push(data);
            maxStack.push(data);
            return;
        }

        mainStack.push(data);
        if (data > maxStack.peek()) {
            maxStack.push(data);
        }
    }

    /**
     * Pops an element from the stack.
     * Checks if the element to be popped is the maximum or not
     * If so, then pop from the minStack
     *
     * @throws NoSuchElementException if the stack is empty.
     */
    public void pop() {
        if (mainStack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        int ele = mainStack.pop();
        if (ele == maxStack.peek()) {
            maxStack.pop();
        }
    }

    /**
     * Returns the maximum element present in the stack
     *
     * @return The element at the top of the maxStack, or null if the stack is empty.
     */
    public Integer getMaximumElement() {
        if (maxStack.isEmpty()) {
            return null;
        }
        return maxStack.peek();
    }
}
