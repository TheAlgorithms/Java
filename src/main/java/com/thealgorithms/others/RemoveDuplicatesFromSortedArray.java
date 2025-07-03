package com.thealgorithms.others;

/**
 * Removes duplicates from a sorted integer array in-place
 * using the two-pointer technique and O(1) extra space.
 *
 * <p>
 * Example:
 * Input:  [1, 1, 2, 2, 3]
 * Output: 3, and the array becomes [1, 2, 3, _, _]
 * </p>
 */
public final class RemoveDuplicatesFromSortedArray {

    private RemoveDuplicatesFromSortedArray() {
        // Utility class, no instantiation
    }

    /**
     * Removes duplicates from a sorted array in-place.
     *
     * @param nums the sorted input array (non-decreasing order)
     * @return the number of unique elements
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int j = 0; // Pointer for the last unique element

        for (int i = 1; i < nums.length; i++) {
            if (nums[j] != nums[i]) {
                j++;
                nums[j] = nums[i];
            }
        }

        return j + 1;
    }
}
