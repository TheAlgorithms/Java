package com.thealgorithms.misc;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * A generic abstract class to compute the median of a dynamically growing stream of numbers.
 *
 * @param <T> the number type, must extend Number and be Comparable
 *
 * Usage:
 * Extend this class and implement {@code calculateAverage(T a, T b)} to define how averaging is done.
 */
public abstract class MedianOfRunningArray<T extends Number & Comparable<T>> {

    private final PriorityQueue<T> maxHeap; // Lower half (max-heap)
    private final PriorityQueue<T> minHeap; // Upper half (min-heap)

    public MedianOfRunningArray() {
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
    }

    /**
     * Inserts a new number into the data structure.
     *
     * @param element the number to insert
     */
    public final void insert(final T element) {
        if (!minHeap.isEmpty() && element.compareTo(minHeap.peek()) < 0) {
            maxHeap.offer(element);
            balanceHeapsIfNeeded();
        } else {
            minHeap.offer(element);
            balanceHeapsIfNeeded();
        }
    }

    /**
     * Returns the median of the current elements.
     *
     * @return the median value
     * @throws IllegalArgumentException if no elements have been inserted
     */
    public final T getMedian() {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            throw new IllegalArgumentException("Median is undefined for an empty data set.");
        }

        if (maxHeap.size() == minHeap.size()) {
            return calculateAverage(maxHeap.peek(), minHeap.peek());
        }

        return (maxHeap.size() > minHeap.size()) ? maxHeap.peek() : minHeap.peek();
    }

    /**
     * Calculates the average between two values.
     * Concrete subclasses must define how averaging works (e.g., for Integer, Double, etc.).
     *
     * @param a first number
     * @param b second number
     * @return the average of a and b
     */
    protected abstract T calculateAverage(T a, T b);

    /**
     * Balances the two heaps so that their sizes differ by at most 1.
     */
    private void balanceHeapsIfNeeded() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        }
    }
}
