package com.thealgorithms.datastructures.buffers;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The {@code CircularBuffer} class implements a generic circular (or ring) buffer.
 * A circular buffer is a fixed-size data structure that operates in a FIFO (First In, First Out) manner.
 * The buffer allows you to overwrite old data when the buffer is full and efficiently use limited memory.
 * When the buffer is full, adding a new item will overwrite the oldest data.
 *
 * @param <Item> The type of elements stored in the circular buffer.
 */
@SuppressWarnings("unchecked")
public class CircularBuffer<Item> {
    private final Item[] buffer;
    private final CircularPointer putPointer;
    private final CircularPointer getPointer;
    private final AtomicInteger size = new AtomicInteger(0);

    /**
     * Constructor to initialize the circular buffer with a specified size.
     *
     * @param size The size of the circular buffer.
     * @throws IllegalArgumentException if the size is zero or negative.
     */
    public CircularBuffer(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Buffer size must be positive");
        }
        // noinspection unchecked
        this.buffer = (Item[]) new Object[size];
        this.putPointer = new CircularPointer(0, size);
        this.getPointer = new CircularPointer(0, size);
    }

    /**
     * Checks if the circular buffer is empty.
     * This method is based on the current size of the buffer.
     *
     * @return {@code true} if the buffer is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return size.get() == 0;
    }

    /**
     * Checks if the circular buffer is full.
     * The buffer is considered full when its size equals its capacity.
     *
     * @return {@code true} if the buffer is full, {@code false} otherwise.
     */
    public boolean isFull() {
        return size.get() == buffer.length;
    }

    /**
     * Retrieves and removes the item at the front of the buffer (FIFO).
     * This operation will move the {@code getPointer} forward.
     *
     * @return The item at the front of the buffer, or {@code null} if the buffer is empty.
     */
    public Item get() {
        if (isEmpty()) {
            return null;
        }

        Item item = buffer[getPointer.getAndIncrement()];
        size.decrementAndGet();
        return item;
    }

    /**
     * Adds an item to the end of the buffer (FIFO).
     * If the buffer is full, this operation will overwrite the oldest data.
     *
     * @param item The item to be added.
     * @throws IllegalArgumentException if the item is null.
     * @return {@code true} if the item was successfully added, {@code false} if the buffer was full and the item overwrote existing data.
     */
    public boolean put(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null items are not allowed");
        }

        boolean wasEmpty = isEmpty();
        if (isFull()) {
            getPointer.getAndIncrement(); // Move get pointer to discard oldest item
        } else {
            size.incrementAndGet();
        }

        buffer[putPointer.getAndIncrement()] = item;
        return wasEmpty;
    }

    /**
     * The {@code CircularPointer} class is a helper class used to track the current index (pointer)
     * in the circular buffer.
     * The max value represents the capacity of the buffer.
     * The `CircularPointer` class ensures that the pointer automatically wraps around to 0
     * when it reaches the maximum index.
     * This is achieved in the `getAndIncrement` method, where the pointer
     * is incremented and then taken modulo the maximum value (`max`).
     * This operation ensures that the pointer always stays within the bounds of the buffer.
     */
    private static class CircularPointer {
        private int pointer;
        private final int max;

        /**
         * Constructor to initialize the circular pointer.
         *
         * @param pointer The initial position of the pointer.
         * @param max The maximum size (capacity) of the circular buffer.
         */
        CircularPointer(int pointer, int max) {
            this.pointer = pointer;
            this.max = max;
        }

        /**
         * Increments the pointer by 1 and wraps it around to 0 if it reaches the maximum value.
         * This ensures the pointer always stays within the buffer's bounds.
         *
         * @return The current pointer value before incrementing.
         */
        public int getAndIncrement() {
            int tmp = pointer;
            pointer = (pointer + 1) % max;
            return tmp;
        }
    }
}
