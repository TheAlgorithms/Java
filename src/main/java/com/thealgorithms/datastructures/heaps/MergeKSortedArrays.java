package com.thealgorithms.datastructures.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * This class provides a method to merge multiple sorted arrays into a single sorted array.
 * It utilizes a min-heap to efficiently retrieve the smallest elements from each array.
 *
 * Time Complexity: O(n * log k), where n is the total number of elements across all arrays
 * and k is the number of arrays.
 *
 * Space Complexity: O(k) for the heap, where k is the number of arrays.
 *
 * @author Hardvan
 */
public final class MergeKSortedArrays {
    private MergeKSortedArrays() {
    }

    /**
     * Merges k sorted arrays into one sorted array using a min-heap.
     * Steps:
     * 1. Create a min-heap to store elements in the format: {value, array index, element index}
     * 2. Add the first element from each array to the heap
     * 3. While the heap is not empty, remove the smallest element from the heap
     *   and add it to the result array. If there are more elements in the same array,
     *   add the next element to the heap.
     *   Continue until all elements have been processed.
     *   The result array will contain all elements in sorted order.
     * 4. Return the result array.
     *
     * @param arrays a 2D array, where each subarray is sorted in non-decreasing order
     * @return a single sorted array containing all elements from the input arrays
     */
    public static int[] mergeKArrays(int[][] arrays) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        int totalLength = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new int[] {arrays[i][0], i, 0});
                totalLength += arrays[i].length;
            }
        }

        int[] result = new int[totalLength];
        int index = 0;
        while (!minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            result[index++] = top[0];

            if (top[2] + 1 < arrays[top[1]].length) {
                minHeap.offer(new int[] {arrays[top[1]][top[2] + 1], top[1], top[2] + 1});
            }
        }

        return result;
    }
}
