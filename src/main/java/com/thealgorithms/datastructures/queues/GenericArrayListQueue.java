package com.thealgorithms.datastructures.queues;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a GenericArrayListQueue, a queue data structure that
 * holds elements of any type specified at runtime, allowing flexibility in the type
 * of elements it stores.
 *
 * <p>The GenericArrayListQueue operates on a First-In-First-Out (FIFO) basis, where
 * elements added first are the first to be removed. New elements are added to the back
 * (or rear) of the queue, while removal of elements occurs from the front.
 *
 * @param <T> The type of elements held in this queue.
 */
public class GenericArrayListQueue<T> {

    /**
     * A list that stores the queue's elements in insertion order.
     */
    private final List<T> elementList = new ArrayList<>();

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue has no elements; {@code false} otherwise.
     */
    public boolean isEmpty() {
        return elementList.isEmpty();
    }

    /**
     * Retrieves, but does not remove, the element at the front of the queue.
     *
     * @return The element at the front of the queue, or {@code null} if the queue is empty.
     */
    public T peek() {
        return isEmpty() ? null : elementList.getFirst();
    }

    /**
     * Inserts an element at the back of the queue.
     *
     * @param element The element to be added to the queue.
     * @return {@code true} if the element was successfully added.
     */
    public boolean add(T element) {
        return elementList.add(element);
    }

    /**
     * Retrieves and removes the element at the front of the queue.
     *
     * @return The element removed from the front of the queue, or {@code null} if the queue is empty.
     */
    public T poll() {
        return isEmpty() ? null : elementList.removeFirst();
    }
}
