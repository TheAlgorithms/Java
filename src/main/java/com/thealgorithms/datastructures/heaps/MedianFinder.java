package com.thealgorithms.datastructures.heaps;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * This class maintains the median of a dynamically changing data stream using
 * two heaps: a max-heap and a min-heap. The max-heap stores the smaller half
 * of the numbers, and the min-heap stores the larger half.
 * This data structure ensures that retrieving the median is efficient.
 * <p>
 * Time Complexity:
 * - Adding a number: O(log n) due to heap insertion.
 * - Finding the median: O(1).
 * <p>
 * Space Complexity: O(n), where n is the total number of elements added.
 *
 * @author Hardvan
 */
public final class MedianFinder {

    MedianFinder() {
    }

    private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private final PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

    /**
     * Adds a new number to the data stream. The number is placed in the appropriate
     * heap to maintain the balance between the two heaps.
     *
     * @param num the number to be added to the data stream
     */
    public void addNum(final int num) {
        // element() throws NoSuchElementException instead of returning null,
        // so the null-peek warning is eliminated entirely.
        if (maxHeap.isEmpty() || num <= maxHeap.element()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    /**
     * Finds the median of the numbers added so far. If the total number of elements
     * is even, the median is the average of the two middle elements. If odd, the
     * median is the middle element from the max-heap.
     *
     * @return the median of the numbers in the data stream
     * @throws NoSuchElementException if no numbers have been added yet
     */
    public double findMedian() {
        if (maxHeap.isEmpty()) {
            throw new NoSuchElementException("Median is undefined for an empty data stream");
        }
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.element() + minHeap.element()) / 2.0;
        }
        return maxHeap.element();
    }
}