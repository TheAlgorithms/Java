package com.thealgorithms.others;

import java.util.Scanner;

/**
 * Remove duplicates from sorted array.
 *
 * <p>
 * Given an integer array nums sorted in non-decreasing order, remove the
 * duplicates in-place such that each unique element appears only once.
 * The relative order of the elements should be kept the same. Then return
 * the number of unique elements in nums.
 *
 * <p>
 */
public final class RemoveDuplicatesFromSortedArray {

    private RemoveDuplicatesFromSortedArray() {
        // private constructor to prevent instantiation
    }

    /**
     * Main method with user input.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter size of sorted array:");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Invalid array size.");
            scanner.close();
            return;
        }

        int[] nums = new int[n];
        System.out.println("Enter " + n + " integers in non-decreasing order:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int k = removeDuplicates(nums);

        System.out.println("Number of unique elements: " + k);
        System.out.print("Array after removing duplicates: ");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();

        scanner.close();
    }

    /**
     * Removes duplicates from a sorted array in-place using O(1) space.
     *
     * @param nums The input sorted array
     * @return The number of unique elements
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int j = 0; // points to last unique element
        for (int i = 1; i < nums.length; i++) {
            if (nums[j] != nums[i]) {
                j++;
                nums[j] = nums[i];
            }
        }
        return j + 1;
    }
}
