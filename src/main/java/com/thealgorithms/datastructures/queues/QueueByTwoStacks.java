package com.thealgorithms.datastructures.queues;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A queue implementation using two stacks. This class provides methods to
 * enqueue (add) elements to the end of the queue and dequeue (remove)
 * elements from the front, while utilizing two internal stacks to manage
 * the order of elements.
 *
 * @param <T> The type of elements held in this queue.
 */
@SuppressWarnings("unchecked")
public class QueueByTwoStacks<T> {

    private final Stack<T> enqueueStk;
    private final Stack<T> dequeueStk;

    /**
     * Constructor that initializes two empty stacks for the queue.
     * The `enqueueStk` is used to push elements when enqueuing, and
     * the `dequeueStk` is used to pop elements when dequeuing.
     */
    public QueueByTwoStacks() {
        enqueueStk = new Stack<>();
        dequeueStk = new Stack<>();
    }

    /**
     * Adds an element to the end of the queue. This method pushes the element
     * onto the `enqueueStk`.
     *
     * @param item The element to be added to the queue.
     */
    public void put(T item) {
        enqueueStk.push(item);
    }

    /**
     * Removes and returns the element at the front of the queue.
     * If `dequeueStk` is empty, it transfers all elements from
     * `enqueueStk` to `dequeueStk` to maintain the correct FIFO
     * (First-In-First-Out) order before popping.
     *
     * @return The element at the front of the queue.
     * @throws NoSuchElementException If the queue is empty.
     */
    public T get() {
        if (dequeueStk.isEmpty()) {
            while (!enqueueStk.isEmpty()) {
                dequeueStk.push(enqueueStk.pop());
            }
        }
        if (dequeueStk.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return dequeueStk.pop();
    }

    /**
     * Returns the total number of elements currently in the queue.
     * This is the sum of the sizes of both stacks.
     *
     * @return The number of elements in the queue.
     */
    public int size() {
        return enqueueStk.size() + dequeueStk.size();
    }

    /**
     * Returns a string representation of the queue, showing the elements
     * in the correct order (from front to back).
     * The `dequeueStk` is first cloned, and then all elements from the
     * `enqueueStk` are added to the cloned stack in reverse order to
     * represent the queue accurately.
     *
     * @return A string representation of the queue.
     */
    @Override
    public String toString() {
        Stack<T> tempStack = (Stack<T>) dequeueStk.clone();
        while (!enqueueStk.isEmpty()) {
            tempStack.push(enqueueStk.pop());
        }
        return "Queue(" + tempStack + ")";
    }
}
