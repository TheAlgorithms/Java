package com.thealgorithms.datastructures.queues;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a GenericArrayListQueue.
 *
 * A GenericArrayListQueue data structure functions the same as any
 * specific-typed queue. The GenericArrayListQueue holds elements of types
 * to-be-specified at runtime. The elements that are added first are the first
 * to be removed (FIFO). New elements are added to the back/rear of the queue.
 */
public class GenericArrayListQueue<T> {

    /**
     * The generic List for the queue. T is the generic element type.
     */
    private final List<T> elementList = new ArrayList<>();

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue is empty, false otherwise.
     */
    private boolean isEmpty() {
        return elementList.isEmpty();
    }

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return The element at the front of the queue, or null if the queue is empty.
     */
    public T peek() {
        return isEmpty() ? null : elementList.getFirst();
    }

    /**
     * Inserts an element of type T to the back of the queue.
     *
     * @param element the element to be added to the queue.
     * @return True if the element was added successfully.
     */
    public boolean add(T element) {
        return elementList.add(element);
    }

    /**
     * Retrieves and removes the element at the front of the queue.
     *
     * @return The element removed from the front of the queue, or null if the queue is empty.
     */
    public T poll() {
        return isEmpty() ? null : elementList.removeFirst();
    }
}
