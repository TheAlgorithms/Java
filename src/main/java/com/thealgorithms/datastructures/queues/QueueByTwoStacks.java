package com.thealgorithms.datastructures.queues;

import java.util.Stack;
import java.util.NoSuchElementException;

public class QueueByTwoStacks<T> {

    private Stack<T> enqueueStk;
    private Stack<T> dequeueStk;

    // Constructor initializes stacks and optionally fills the queue from an iterable
    public QueueByTwoStacks() {
        enqueueStk = new Stack<>();
        dequeueStk = new Stack<>();
    }

    // Add an item to the queue
    public void put(T item) {
        enqueueStk.push(item);
    }

    // Remove and return the first item from the queue
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

    // Get the number of elements in the queue
    public int size() {
        return enqueueStk.size() + dequeueStk.size();
    }

    // Return string representation of the queue
    @Override
    public String toString() {
        Stack<T> tempStack = (Stack<T>) dequeueStk.clone();
        while (!enqueueStk.isEmpty()) {
            tempStack.push(enqueueStk.pop());
        }
        return "Queue(" + tempStack + ")";
    }
}
