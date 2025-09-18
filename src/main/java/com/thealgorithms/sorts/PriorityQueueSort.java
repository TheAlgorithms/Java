package com.thealgorithms.sorts;

import java.util.PriorityQueue;

/**
 * Sorts an array using Java's PriorityQueue (Min-Heap).
 *
 * <p>Example: Input: [7, 2, 9, 4, 1] Output: [1, 2, 4, 7, 9]
 *
 * <p>Time Complexity:
 * - Inserting n elements into the PriorityQueue → O(n log n)
 * - Polling n elements → O(n log n)
 * - Total: O(n log n)
 *
 * <p>Space Complexity: O(n) for the PriorityQueue
 *
 * @see <a href="https://en.wikipedia.org/wiki/Heap_(data_structure)">
 *     Heap / PriorityQueue</a>
 */
public final class PriorityQueueSort {

    // Private constructor to prevent instantiation (utility class)
    private PriorityQueueSort() {
    }

    /**
     * Sorts the given array in ascending order using a PriorityQueue.
     *
     * @param arr the array to be sorted
     * @return the sorted array (in-place)
     */
    public static int[] sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : arr) {
            pq.offer(num);
        }

        int i = 0;
        while (!pq.isEmpty()) {
            arr[i++] = pq.poll();
        }

        return arr;
    }
}
