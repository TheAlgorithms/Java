package com.thealgorithms.datastructures.buffers;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The {@code CircularBuffer} class implements a generic circular (or ring) buffer.
 * A circular buffer is a fixed-size data structure that operates in a FIFO (First In, First Out) manner.
 * The buffer allows you to overwrite old data when the buffer is full and efficiently use limited memory.
 *
 * @param <Item> The type of elements stored in the circular buffer.
 */
public class CircularBuffer<Item> {
    private final Item[] buffer;
    private final CircularPointer putPointer;
    private final CircularPointer getPointer;
    private final AtomicInteger size = new AtomicInteger(0);

    /**
     * Constructor to initialize the circular buffer with a specified size.
     *
     * @param size The size of the circular buffer.
     */
    public CircularBuffer(int size) {
        // noinspection unchecked
        this.buffer = (Item[]) new Object[size];
        this.putPointer = new CircularPointer(0, size);
        this.getPointer = new CircularPointer(0, size);
    }

    /**
     * Checks if the circular buffer is empty.
     *
     * @return {@code true} if the buffer is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return size.get() == 0;
    }

    /**
     * Checks if the circular buffer is full.
     *
     * @return {@code true} if the buffer is full, {@code false} otherwise.
     */
    public boolean isFull() {
        return size.get() == buffer.length;
    }

    /**
     * Retrieves and removes the item at the front of the buffer (FIFO).
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
     *
     * @param item The item to be added.
     * @return {@code true} if the item was successfully added, or {@code false} if the buffer is full.
     */
    public boolean put(Item item) {
        if (isFull()) {
            return false;
        }

        buffer[putPointer.getAndIncrement()] = item;
        size.incrementAndGet();
        return true;
    }

    /**
     * The {@code CircularPointer} class is a helper class used to track the current index (pointer)
     * in the circular buffer. It automatically wraps around when reaching the maximum index.
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
         * Increments the pointer by 1 and wraps it around to 0 if it exceeds the maximum value.
         *
         * @return The current pointer value before incrementing.
         */
        public int getAndIncrement() {
            if (pointer == max) {
                pointer = 0;
            }
            int tmp = pointer;
            pointer++;
            return tmp;
        }
    }
}
