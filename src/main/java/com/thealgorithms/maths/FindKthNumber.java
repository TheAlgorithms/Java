package com.thealgorithms.maths;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Use a quicksort-based approach to identify the k-th largest or k-th max element within the provided array.
 */
public final class FindKthNumber {
    private FindKthNumber() {
    }

    private static final Random RANDOM = new Random();

    public static int findKthMax(int[] array, int k) {
        if (k <= 0 || k > array.length) {
            throw new IllegalArgumentException("k must be between 1 and the size of the array");
        }

        // Convert k-th largest to index for QuickSelect
        return quickSelect(array, 0, array.length - 1, array.length - k);
    }

    private static int quickSelect(int[] array, int left, int right, int kSmallest) {
        if (left == right) {
            return array[left];
        }

        // Randomly select a pivot index
        int pivotIndex = left + RANDOM.nextInt(right - left + 1);
        pivotIndex = partition(array, left, right, pivotIndex);

        if (kSmallest == pivotIndex) {
            return array[kSmallest];
        } else if (kSmallest < pivotIndex) {
            return quickSelect(array, left, pivotIndex - 1, kSmallest);
        } else {
            return quickSelect(array, pivotIndex + 1, right, kSmallest);
        }
    }

    private static int partition(int[] array, int left, int right, int pivotIndex) {
        int pivotValue = array[pivotIndex];
        // Move pivot to end
        swap(array, pivotIndex, right);
        int storeIndex = left;

        // Move all smaller elements to the left
        for (int i = left; i < right; i++) {
            if (array[i] < pivotValue) {
                swap(array, storeIndex, i);
                storeIndex++;
            }
        }

        // Move pivot to its final place
        swap(array, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int findKthMaxUsingHeap(int[] array, int k) {
        if (k <= 0 || k > array.length) {
            throw new IllegalArgumentException("k must be between 1 and the size of the array");
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // using max-heap to store numbers.
        for (int num : array) {
            maxHeap.add(num);
        }
        while (k > 1) {
            maxHeap.poll(); // removing max number from heap
            k--;
        }
        return maxHeap.peek();
    }
}
