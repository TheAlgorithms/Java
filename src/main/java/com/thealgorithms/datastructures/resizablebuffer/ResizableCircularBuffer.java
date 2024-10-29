package com.thealgorithms.datastructures.resizablebuffer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The {@code ResizableCircularBuffer} class implements a generic resizable circular (or ring) buffer.
 * This buffer grows automatically when it becomes full, retaining a FIFO (First In, First Out) structure.
 *
 * @param <Item> The type of elements stored in the circular buffer.
 */
public class ResizableCircularBuffer<Item> {
    private Item[] buffer;
    private int putPointer = 0;
    private int getPointer = 0;
    private final AtomicInteger size = new AtomicInteger(0);

    /**
     * Constructor to initialize the circular buffer with a specified initial size.
     *
     * @param initialSize The initial size of the circular buffer.
     * @throws IllegalArgumentException if the size is zero or negative.
     */
    @SuppressWarnings("unchecked")
    public ResizableCircularBuffer(int initialSize) {
        if (initialSize <= 0) {
            throw new IllegalArgumentException("Buffer size must be positive");
        }
        this.buffer = (Item[]) new Object[initialSize];
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
    public synchronized Item get() {
        if (isEmpty()) {
            return null;
        }
        Item item = buffer[getPointer];
        buffer[getPointer] = null;  // Optional: clear reference
        getPointer = (getPointer + 1) % buffer.length;
        size.decrementAndGet();
        return item;
    }

    /**
     * Adds an item to the end of the buffer (FIFO). If the buffer is full, it doubles in size.
     *
     * @param item The item to be added.
     * @throws IllegalArgumentException if the item is null.
     */
    public synchronized void put(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null items are not allowed");
        }

        if (isFull()) {
            resizeBuffer();
        }

        buffer[putPointer] = item;
        putPointer = (putPointer + 1) % buffer.length;
        size.incrementAndGet();
    }

    /**
     * Resizes the buffer to twice its current size to accommodate more items.
     */
    @SuppressWarnings("unchecked")
    private void resizeBuffer() {
        Item[] newBuffer = (Item[]) new Object[buffer.length * 2];
        for (int i = 0; i < buffer.length; i++) {
            newBuffer[i] = buffer[(getPointer + i) % buffer.length];
        }
        buffer = newBuffer;
        getPointer = 0;
        putPointer = size.get();
    }
}