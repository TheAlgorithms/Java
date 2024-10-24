package com.thealgorithms.datastructures.buffers;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The {@code DoubleBuffer} class implements a double buffering mechanism.
 * It maintains two buffers, allowing the program to switch between them.
 * This class is useful for separating read/write operations and optimizing memory usage.
 *
 * @param <Item> The type of elements stored in the double buffer.
 */
public class DoubleBuffer<Item> {
    private final Item[] primaryBuffer;
    private final Item[] secondaryBuffer;
    private final AtomicInteger primaryIndex;
    private final AtomicInteger secondaryIndex;
    private final int capacity;
    private final AtomicBoolean isPrimaryActive;

    /**
     * Constructor to initialize the double buffer with a specified capacity.
     *
     * @param capacity The size of each buffer.
     * @throws IllegalArgumentException if the capacity is zero or negative.
     */
    public DoubleBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Buffer size must be positive");
        }
        // noinspection unchecked
        this.primaryBuffer = (Item[]) new Object[capacity];
        this.secondaryBuffer = (Item[]) new Object[capacity];
        this.primaryIndex = new AtomicInteger(0);
        this.secondaryIndex = new AtomicInteger(0);
        this.capacity = capacity;
        this.isPrimaryActive = new AtomicBoolean(true);
    }

    /**
     * Checks if the active buffer is empty.
     *
     * @return {@code true} if the active buffer is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return isPrimaryActive.get() ? primaryIndex.get() == 0 : secondaryIndex.get() == 0;
    }

    /**
     * Switches between the primary and secondary buffers.
     */
    public void switchBuffer() {
        isPrimaryActive.set(!isPrimaryActive.get());
    }

    /**
     * Checks if the primary buffer is currently active.
     *
     * @return {@code true} if the primary buffer is active, {@code false} otherwise.
     */
    public boolean isPrimaryActive() {
        return isPrimaryActive.get();
    }

    /**
     * Adds an item to the active buffer.
     *
     * @param item The item to be added.
     * @throws IllegalArgumentException if the item is null.
     */
    public void put(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null items are not allowed");
        }

        if (isPrimaryActive.get()) {
            if (primaryIndex.get() < capacity) {
                primaryBuffer[primaryIndex.getAndIncrement()] = item;
            }
        } else {
            if (secondaryIndex.get() < capacity) {
                secondaryBuffer[secondaryIndex.getAndIncrement()] = item;
            }
        }
    }

    /**
     * Retrieves and removes the item at the front of the active buffer.
     *
     * @return The item from the active buffer, or {@code null} if the buffer is empty.
     */
    public Item get() {
        if (isPrimaryActive.get()) {
            if (primaryIndex.get() == 0) {
                return null;
            }
            return primaryBuffer[primaryIndex.decrementAndGet()];
        } else {
            if (secondaryIndex.get() == 0) {
                return null;
            }
            return secondaryBuffer[secondaryIndex.decrementAndGet()];
        }
    }
}
