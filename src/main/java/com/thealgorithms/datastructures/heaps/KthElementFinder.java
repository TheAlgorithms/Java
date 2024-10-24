
package com.thealgorithms.datastructures.heaps;

import java.util.PriorityQueue;

/**
 * This class provides methods to find the Kth largest or Kth smallest element
 * in an array using heaps. It leverages a min-heap to find the Kth largest element
 * and a max-heap to find the Kth smallest element efficiently.
 *
 * @author Hardvan
 */
public final class KthElementFinder {
    private KthElementFinder() {
    }

    /**
     * Finds the Kth largest element in the given array.
     * Uses a min-heap of size K to track the largest K elements.
     *
     * Time Complexity: O(n * log(k)), where n is the size of the input array.
     * Space Complexity: O(k), as we maintain a heap of size K.
     *
     * @param nums the input array of integers
     * @param k the desired Kth position (1-indexed, i.e., 1 means the largest element)
     * @return the Kth largest element in the array
     */
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    /**
     * Finds the Kth smallest element in the given array.
     * Uses a max-heap of size K to track the smallest K elements.
     *
     * Time Complexity: O(n * log(k)), where n is the size of the input array.
     * Space Complexity: O(k), as we maintain a heap of size K.
     *
     * @param nums the input array of integers
     * @param k the desired Kth position (1-indexed, i.e., 1 means the smallest element)
     * @return the Kth smallest element in the array
     */
    public static int findKthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }
}
