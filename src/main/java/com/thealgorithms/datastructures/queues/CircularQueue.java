package com.thealgorithms.datastructures.queues;

// This program implements the concept of CircularQueue in Java
// Link to the concept: (https://en.wikipedia.org/wiki/Circular_buffer)
public class CircularQueue<T> {
    private T[] array;
    private int topOfQueue;
    private int beginningOfQueue;
    private final int size;
    private int currentSize;

    @SuppressWarnings("unchecked")
    public CircularQueue(int size) {
        this.array = (T[]) new Object[size];
        this.topOfQueue = -1;
        this.beginningOfQueue = -1;
        this.size = size;
        this.currentSize = 0;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == size;
    }

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

    public T deQueue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T removedValue = array[beginningOfQueue];
        array[beginningOfQueue] = null; // Optional: Help GC
        beginningOfQueue = (beginningOfQueue + 1) % size;
        currentSize--;
        if (isEmpty()) {
            beginningOfQueue = -1;
            topOfQueue = -1;
        }
        return removedValue;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return array[beginningOfQueue];
    }

    public void deleteQueue() {
        array = null;
        beginningOfQueue = -1;
        topOfQueue = -1;
        currentSize = 0;
    }

    public int size() {
        return currentSize;
    }

    public static void main(String[] args) {
        CircularQueue<Integer> cq = new CircularQueue<>(5);
        System.out.println(cq.isEmpty()); // true
        System.out.println(cq.isFull()); // false
        cq.enQueue(1);
        cq.enQueue(2);
        cq.enQueue(3);
        cq.enQueue(4);
        cq.enQueue(5);

        System.out.println(cq.deQueue()); // 1
        System.out.println(cq.deQueue()); // 2
        System.out.println(cq.deQueue()); // 3
        System.out.println(cq.deQueue()); // 4
        System.out.println(cq.deQueue()); // 5

        System.out.println(cq.isFull()); // false
        System.out.println(cq.isEmpty()); // true
        cq.enQueue(6);
        cq.enQueue(7);
        cq.enQueue(8);

        System.out.println(cq.peek()); // 6
        System.out.println(cq.peek()); // 6

        cq.deleteQueue();
    }
}
