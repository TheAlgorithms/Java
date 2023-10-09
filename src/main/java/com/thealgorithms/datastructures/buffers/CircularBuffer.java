package com.thealgorithms.datastructures.buffers;

import java.util.concurrent.atomic.AtomicInteger;

public class CircularBuffer<Item> {
    private final Item[] buffer;
    private final CircularPointer putPointer;
    private final CircularPointer getPointer;
    private final AtomicInteger size = new AtomicInteger(0);

    public CircularBuffer(int size) {
        // noinspection unchecked
        this.buffer = (Item[]) new Object[size];
        this.putPointer = new CircularPointer(0, size);
        this.getPointer = new CircularPointer(0, size);
    }

    public boolean isEmpty() {
        return size.get() == 0;
    }

    public boolean isFull() {
        return size.get() == buffer.length;
    }

    public Item get() {
        if (isEmpty()) return null;

        Item item = buffer[getPointer.getAndIncrement()];
        size.decrementAndGet();
        return item;
    }

    public boolean put(Item item) {
        if (isFull()) return false;

        buffer[putPointer.getAndIncrement()] = item;
        size.incrementAndGet();
        return true;
    }

    private static class CircularPointer {
        private int pointer;
        private final int max;

        public CircularPointer(int pointer, int max) {
            this.pointer = pointer;
            this.max = max;
        }

        public int getAndIncrement() {
            if (pointer == max) pointer = 0;
            int tmp = pointer;
            pointer++;
            return tmp;
        }
    }
}
