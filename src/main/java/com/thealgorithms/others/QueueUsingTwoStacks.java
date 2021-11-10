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
class QueueWithStack {

    // Stack to keep track of elements inserted into the queue
    private Stack<Object> inStack;
    // Stack to keep track of elements to be removed next in queue
    private Stack<Object> outStack;

    /**
     * Constructor
     */
    public QueueWithStack() {
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
        return (inStack.size() == 0);
    }

    /**
     * Returns true if the outStack is empty.
     *
     * @return true if the outStack is empty.
     */
    public boolean isOutStackEmpty() {
        return (outStack.size() == 0);
    }
}

/**
 * This class is the example for the Queue class
 *
 * @author sahilb2 (https://www.github.com/sahilb2)
 */
public class QueueUsingTwoStacks {

    /**
     * Main method
     *
     * @param args Command line arguments
     */
    public static void main(String args[]) {
        QueueWithStack myQueue = new QueueWithStack();
        myQueue.insert(1);
        System.out.println(myQueue.peekBack()); // Will print 1
        // instack: [(top) 1]
        // outStack: []
        myQueue.insert(2);
        System.out.println(myQueue.peekBack()); // Will print 2
        // instack: [(top) 2, 1]
        // outStack: []
        myQueue.insert(3);
        System.out.println(myQueue.peekBack()); // Will print 3
        // instack: [(top) 3, 2, 1]
        // outStack: []
        myQueue.insert(4);
        System.out.println(myQueue.peekBack()); // Will print 4
        // instack: [(top) 4, 3, 2, 1]
        // outStack: []

        System.out.println(myQueue.isEmpty()); // Will print false

        System.out.println(myQueue.remove()); // Will print 1
        System.out.println((myQueue.isInStackEmpty()) ? "null" : myQueue.peekBack()); // Will print NULL
        // instack: []
        // outStack: [(top) 2, 3, 4]

        myQueue.insert(5);
        System.out.println(myQueue.peekFront()); // Will print 2
        // instack: [(top) 5]
        // outStack: [(top) 2, 3, 4]

        myQueue.remove();
        System.out.println(myQueue.peekFront()); // Will print 3
        // instack: [(top) 5]
        // outStack: [(top) 3, 4]
        myQueue.remove();
        System.out.println(myQueue.peekFront()); // Will print 4
        // instack: [(top) 5]
        // outStack: [(top) 4]
        myQueue.remove();
        // instack: [(top) 5]
        // outStack: []
        System.out.println(myQueue.peekFront()); // Will print 5
        // instack: []
        // outStack: [(top) 5]
        myQueue.remove();
        // instack: []
        // outStack: []

        System.out.println(myQueue.isEmpty()); // Will print true
    }
}
