package com.thealgorithms.datastructures.queues;

/**
 * This class implements a Queue data structure using an array.
 * A queue is a first-in-first-out (FIFO) data structure where elements are
 * added to the rear and removed from the front.
 *
 * Note: This implementation is not thread-safe.
 */
public final class Queue<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private final int maxSize;
    private final Object[] queueArray;
    private int front;
    private int rear;
    private int nItems;

    /**
     * Initializes a queue with a default capacity.
     */
    public Queue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor to initialize a queue with a specified capacity.
     *
     * @param capacity The initial size of the queue.
     * @throws IllegalArgumentException if the capacity is less than or equal to zero.
     */
    public Queue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Queue capacity must be greater than 0");
        }
        this.maxSize = capacity;
        this.queueArray = new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.nItems = 0;
    }

    /**
     * Inserts an element at the rear of the queue.
     *
     * @param element Element to be added.
     * @return True if the element was added successfully, false if the queue is full.
     */
    public boolean insert(T element) {
        if (isFull()) {
            return false;
        }
        rear = (rear + 1) % maxSize;
        queueArray[rear] = element;
        nItems++;
        return true;
    }

    /**
     * Removes and returns the element from the front of the queue.
     *
     * @return The element removed from the front of the queue.
     * @throws IllegalStateException if the queue is empty.
     */
    @SuppressWarnings("unchecked")
    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty, cannot remove element");
        }
        T removedElement = (T) queueArray[front];
        queueArray[front] = null; // Optional: Clear the reference for garbage collection
        front = (front + 1) % maxSize;
        nItems--;
        return removedElement;
    }

    /**
     * Checks the element at the front of the queue without removing it.
     *
     * @return Element at the front of the queue.
     * @throws IllegalStateException if the queue is empty.
     */
    @SuppressWarnings("unchecked")
    public T peekFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty, cannot peek front");
        }
        return (T) queueArray[front];
    }

    /**
     * Checks the element at the rear of the queue without removing it.
     *
     * @return Element at the rear of the queue.
     * @throws IllegalStateException if the queue is empty.
     */
    @SuppressWarnings("unchecked")
    public T peekRear() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty, cannot peek rear");
        }
        return (T) queueArray[rear];
    }

    /**
     * Returns true if the queue is empty.
     *
     * @return True if the queue is empty.
     */
    public boolean isEmpty() {
        return nItems == 0;
    }

    /**
     * Returns true if the queue is full.
     *
     * @return True if the queue is full.
     */
    public boolean isFull() {
        return nItems == maxSize;
    }

    /**
     * Returns the number of elements currently in the queue.
     *
     * @return Number of elements in the queue.
     */
    public int getSize() {
        return nItems;
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return String representation of the queue.
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < nItems; i++) {
            int index = (front + i) % maxSize;
            sb.append(queueArray[index]).append(", ");
        }
        sb.setLength(sb.length() - 2); // Remove the last comma and space
        sb.append("]");
        return sb.toString();
    }
}
