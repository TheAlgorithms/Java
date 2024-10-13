package com.thealgorithms.stacks;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A class that implements a stack that gives the minimum element in O(1) time.
 * The mainStack is used to store the all the elements of the stack
 * While the minStack stores the minimum elements
 * When we want to get a minimum element, we call the top of the minimum stack
 *
 * Problem: https://www.baeldung.com/cs/stack-constant-time
 */
public class SmallestElementConstantTime {
    private Stack<Integer> mainStack; // initialize a mainStack
    private Stack<Integer> minStack; // initialize a minStack

    /**
     * Constructs two empty stacks
     */
    public SmallestElementConstantTime() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    /**
     * Pushes an element onto the top of the stack.
     * Checks if the element is the minimum or not
     * If so, then pushes to the minimum stack
     * @param data The element to be pushed onto the stack.
     */
    public void push(int data) {
        if (mainStack.isEmpty()) {
            mainStack.push(data);
            minStack.push(data);
            return;
        }

        mainStack.push(data);
        if (data < minStack.peek()) {
            minStack.push(data);
        }
    }

    /**
     * Pops an element from the stack.
     * Checks if the element to be popped is the minimum or not
     * If so, then pop from the minStack
     *
     * @throws NoSuchElementException if the stack is empty.
     */
    public void pop() {
        if (mainStack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        int ele = mainStack.pop();
        if (ele == minStack.peek()) {
            minStack.pop();
        }
    }

    /**
     * Returns the minimum element present in the stack
     *
     * @return The element at the top of the minStack, or null if the stack is empty.
     */
    public Integer getMinimumElement() {
        if (minStack.isEmpty()) {
            return null;
        }
        return minStack.peek();
    }
}
