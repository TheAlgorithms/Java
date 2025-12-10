package com.thealgorithms.others;

import java.util.Stack;

/**
 * This implements Queue using two Stacks.
 *
 * <p>
 * Big O Runtime: insert(): O(1) remove(): O(1) amortized isEmpty(): O(1)
 *
 * <p>
 * A queue data structure functions the same as a real world queue. The elements
 * that are added first are the first to be removed. New elements are added to
 * the back/rear of the queue.
 *
 * @author sahilb2 (https://www.github.com/sahilb2)
 */
public class QueueUsingTwoStacks {
    private final Stack<Object> inStack;
    private final Stack<Object> outStack;

    /**
     * Constructor
     */
    public QueueUsingTwoStacks() {
        this.inStack = new Stack<>();
        this.outStack = new Stack<>();
    }

    /**
     * Inserts an element at the rear of the queue
     *
     * @param x element to be added
     */
    public void insert(Object x) {
        // Insert element into inStack
        this.inStack.push(x);
    }

    /**
     * Remove an element from the front of the queue
     *
     * @return the new front of the queue
     */
    public Object remove() {
        if (this.outStack.isEmpty()) {
            // Move all elements from inStack to outStack (preserving the order)
            while (!this.inStack.isEmpty()) {
                this.outStack.push(this.inStack.pop());
            }
        }
        return this.outStack.pop();
    }

    /**
     * Peek at the element from the front of the queue
     *
     * @return the front element of the queue
     */
    public Object peekFront() {
        if (this.outStack.isEmpty()) {
            // Move all elements from inStack to outStack (preserving the order)
            while (!this.inStack.isEmpty()) {
                this.outStack.push(this.inStack.pop());
            }
        }
        return this.outStack.peek();
    }

    /**
     * Peek at the element from the back of the queue
     *
     * @return the back element of the queue
     */
    public Object peekBack() {
        return this.inStack.peek();
    }

    /**
     * Returns true if the queue is empty
     *
     * @return true if the queue is empty
     */
    public boolean isEmpty() {
        return (this.inStack.isEmpty() && this.outStack.isEmpty());
    }

    /**
     * Returns true if the inStack is empty.
     *
     * @return true if the inStack is empty.
     */
    public boolean isInStackEmpty() {
        return (inStack.isEmpty());
    }

    /**
     * Returns true if the outStack is empty.
     *
     * @return true if the outStack is empty.
     */
    public boolean isOutStackEmpty() {
        return (outStack.isEmpty());
    }
}
