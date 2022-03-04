package com.thealgorithms.maths;

import java.util.Arrays;
import java.util.Random;

/**
 * use quick sort algorithm to get kth largest or kth smallest element in given array
 */
public class FindKthNumber {
    private static final Random random = new Random();

    public static void main(String[] args) {
        /* generate array with random size and random elements */
        int[] nums = generateArray(100);

        /* get 3th largest element */
        int kth = 3;
        int kthMaxIndex = nums.length - kth;
        int targetMax = findKthMax(nums, kthMaxIndex);

        /* get 3th smallest element */
        int kthMinIndex = kth - 1;
        int targetMin = findKthMax(nums, kthMinIndex);

        Arrays.sort(nums);
        assert nums[kthMaxIndex] == targetMax;
        assert nums[kthMinIndex] == targetMin;
    }

    private static int[] generateArray(int capacity) {
        int size = random.nextInt(capacity) + 1;
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt() % 100;
        }
        return array;
    }

    private static int findKthMax(int[] nums, int k) {
        int start = 0, end = nums.length;
        while (start < end) {
            int pivot = partition(nums, start, end);
            if (k == pivot) {
                return nums[pivot];
            } else if (k > pivot) {
                start = pivot + 1;
            } else {
                end = pivot;
            }
        }
        return -1;
    }

    private static int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        int j = start;
        for (int i = start + 1; i < end; i++) {
            if (nums[i] < pivot) {
                j++;
                swap(nums, i, j);
            }
        }
        swap(nums, start, j);
        return j;
    }

    private static void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
