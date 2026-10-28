package com.thealgorithms.datastructures.queues;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @brief Thread-safe bounded queue implementation using ReentrantLock and Condition variables
 * @details A blocking queue that supports multiple producers and consumers.
 * Uses a circular buffer internally with lock-based synchronization to ensure
 * thread safety. Producers block when the queue is full, and consumers block
 * when the queue is empty.
 * @see <a href="https://en.wikipedia.org/wiki/Producer%E2%80%93consumer_problem">Producer-Consumer Problem</a>
 */
public class ThreadSafeQueue<T> {

    private final Object[] buffer;
    private final int capacity;
    private int head;
    private int tail;
    private int count;
    private final ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    /**
     * @brief Constructs a ThreadSafeQueue with the specified capacity
     * @param capacity the maximum number of elements the queue can hold
     * @throws IllegalArgumentException if capacity is less than or equal to zero
     */
    public ThreadSafeQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.capacity = capacity;
        this.buffer = new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.lock = new ReentrantLock();
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
    }

    /**
     * @brief Adds an element to the tail of the queue, blocking if full
     * @param item the element to add
     * @throws InterruptedException if the thread is interrupted while waiting
     * @throws IllegalArgumentException if the item is null
     */
    public void enqueue(T item) throws InterruptedException {
        if (item == null) {
            throw new IllegalArgumentException("Cannot enqueue null item.");
        }

        lock.lock();
        try {
            while (count == capacity) {
                notFull.await();
            }
            buffer[tail] = item;
            tail = (tail + 1) % capacity;
            count++;
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * @brief Removes and returns the element at the head of the queue, blocking if empty
     * @return the element at the head of the queue
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    @SuppressWarnings("unchecked")
    public T dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            T item = (T) buffer[head];
            buffer[head] = null;
            head = (head + 1) % capacity;
            count--;
            notFull.signalAll();
            return item;
        } finally {
            lock.unlock();
        }
    }

    /**
     * @brief Adds an element to the tail of the queue without blocking
     * @param item the element to add
     * @return true if the element was added, false if the queue was full
     * @throws IllegalArgumentException if the item is null
     */
    public boolean offer(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot enqueue null item.");
        }

        lock.lock();
        try {
            if (count == capacity) {
                return false;
            }
            buffer[tail] = item;
            tail = (tail + 1) % capacity;
            count++;
            notEmpty.signalAll();
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * @brief Removes and returns the element at the head without blocking
     * @return the element at the head, or null if the queue is empty
     */
    @SuppressWarnings("unchecked")
    public T poll() {
        lock.lock();
        try {
            if (count == 0) {
                return null;
            }
            T item = (T) buffer[head];
            buffer[head] = null;
            head = (head + 1) % capacity;
            count--;
            notFull.signalAll();
            return item;
        } finally {
            lock.unlock();
        }
    }

    /**
     * @brief Returns the number of elements in the queue
     * @return the current size of the queue
     */
    public int size() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    /**
     * @brief Checks if the queue is empty
     * @return true if the queue contains no elements
     */
    public boolean isEmpty() {
        lock.lock();
        try {
            return count == 0;
        } finally {
            lock.unlock();
        }
    }

    /**
     * @brief Checks if the queue is full
     * @return true if the queue has reached its capacity
     */
    public boolean isFull() {
        lock.lock();
        try {
            return count == capacity;
        } finally {
            lock.unlock();
        }
    }

    /**
     * @brief Returns the maximum capacity of the queue
     * @return the capacity
     */
    public int capacity() {
        return capacity;
    }
}
