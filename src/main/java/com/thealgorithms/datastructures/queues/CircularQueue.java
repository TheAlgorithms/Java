package com.thealgorithms.datastructures.queues;

/**
 * The CircularQueue class represents a generic circular queue data structure that uses an array to
 * store elements. This queue allows efficient utilization of space by wrapping around the array,
 * thus avoiding the need to shift elements during enqueue and dequeue operations.
 *
 * <p>When the queue reaches its maximum capacity, further enqueues will raise an exception.
 * Similarly, attempts to dequeue or peek from an empty queue will also result in an exception.
 *
 * <p>Reference: <a href="https://en.wikipedia.org/wiki/Circular_buffer">Circular Buffer</a>
 *
 * <p>Usage Example:
 * <pre>
 *     CircularQueue<Integer> queue = new CircularQueue<>(3);
 *     queue.enQueue(1);
 *     queue.enQueue(2);
 *     queue.enQueue(3);
 *     queue.deQueue(); // Removes 1
 *     queue.enQueue(4); // Wraps around and places 4 at the position of removed 1
 * </pre>
 *
 * @param <T> the type of elements in this queue
 */
public class CircularQueue<T> {
    private T[] array;
    private int topOfQueue;
    private int beginningOfQueue;
    private final int size;
    private int currentSize;

    /**
     * Constructs a CircularQueue with a specified capacity.
     *
     * @param size the maximum number of elements this queue can hold
     * @throws IllegalArgumentException if the size is less than 1
     */
    @SuppressWarnings("unchecked")
    public CircularQueue(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        this.array = (T[]) new Object[size];
        this.topOfQueue = -1;
        this.beginningOfQueue = -1;
        this.size = size;
        this.currentSize = 0;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * Checks if the queue is full.
     *
     * @return {@code true} if the queue has reached its maximum capacity; {@code false} otherwise
     */
    public boolean isFull() {
        return currentSize == size;
    }

    /**
     * Adds a new element to the queue. If the queue is full, an exception is thrown.
     *
     * @param value the element to be added to the queue
     * @throws IllegalStateException if the queue is already full
     */
    public void enQueue(T value) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        if (isEmpty()) {
            beginningOfQueue = 0;
        }
        topOfQueue = (topOfQueue + 1) % size;
        array[topOfQueue] = value;
        currentSize++;
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element at the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    public T deQueue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T removedValue = array[beginningOfQueue];
        array[beginningOfQueue] = null; // Optional: Nullify to help garbage collection
        beginningOfQueue = (beginningOfQueue + 1) % size;
        currentSize--;
        if (isEmpty()) {
            beginningOfQueue = -1;
            topOfQueue = -1;
        }
        return removedValue;
    }

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return array[beginningOfQueue];
    }

    /**
     * Deletes the entire queue by resetting all elements and pointers.
     */
    public void deleteQueue() {
        array = null;
        beginningOfQueue = -1;
        topOfQueue = -1;
        currentSize = 0;
    }

    /**
     * Returns the current number of elements in the queue.
     *
     * @return the number of elements currently in the queue
     */
    public int size() {
        return currentSize;
    }
}
