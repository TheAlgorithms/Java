package com.thealgorithms.misc;

/**
 * Utility class for operations to find the range of occurrences of a key
 * in a sorted (non-decreasing) array, and to count elements less than or equal to a given key.
 */
public final class RangeInSortedArray {

    private RangeInSortedArray() {
    }

    /**
     * Finds the first and last occurrence indices of the key in a sorted array.
     *
     * @param nums sorted array of integers (non-decreasing order)
     * @param key the target value to search for
     * @return int array of size two where
     *         - index 0 is the first occurrence of key,
     *         - index 1 is the last occurrence of key,
     *         or [-1, -1] if the key does not exist in the array.
     */
    public static int[] sortedRange(int[] nums, int key) {
        int[] range = new int[] {-1, -1};
        alteredBinSearchIter(nums, key, 0, nums.length - 1, range, true); // find left boundary
        alteredBinSearchIter(nums, key, 0, nums.length - 1, range, false); // find right boundary
        return range;
    }

    /**
     * Recursive altered binary search to find either the leftmost or rightmost occurrence of a key.
     *
     * @param nums the sorted array
     * @param key the target to find
     * @param left current left bound in search
     * @param right current right bound in search
     * @param range array to update with boundaries: range[0] for leftmost, range[1] for rightmost
     * @param goLeft if true, searches for leftmost occurrence; if false, for rightmost occurrence
     */
    public static void alteredBinSearch(int[] nums, int key, int left, int right, int[] range, boolean goLeft) {
        if (left > right) {
            return;
        }
        int mid = left + ((right - left) >>> 1);
        if (nums[mid] > key) {
            alteredBinSearch(nums, key, left, mid - 1, range, goLeft);
        } else if (nums[mid] < key) {
            alteredBinSearch(nums, key, mid + 1, right, range, goLeft);
        } else {
            if (goLeft) {
                if (mid == 0 || nums[mid - 1] != key) {
                    range[0] = mid;
                } else {
                    alteredBinSearch(nums, key, left, mid - 1, range, goLeft);
                }
            } else {
                if (mid == nums.length - 1 || nums[mid + 1] != key) {
                    range[1] = mid;
                } else {
                    alteredBinSearch(nums, key, mid + 1, right, range, goLeft);
                }
            }
        }
    }

    /**
     * Iterative altered binary search to find either the leftmost or rightmost occurrence of a key.
     *
     * @param nums the sorted array
     * @param key the target to find
     * @param left initial left bound
     * @param right initial right bound
     * @param range array to update with boundaries: range[0] for leftmost, range[1] for rightmost
     * @param goLeft if true, searches for leftmost occurrence; if false, for rightmost occurrence
     */
    public static void alteredBinSearchIter(int[] nums, int key, int left, int right, int[] range, boolean goLeft) {
        while (left <= right) {
            int mid = left + ((right - left) >>> 1);
            if (nums[mid] > key) {
                right = mid - 1;
            } else if (nums[mid] < key) {
                left = mid + 1;
            } else {
                if (goLeft) {
                    if (mid == 0 || nums[mid - 1] != key) {
                        range[0] = mid;
                        return;
                    }
                    right = mid - 1;
                } else {
                    if (mid == nums.length - 1 || nums[mid + 1] != key) {
                        range[1] = mid;
                        return;
                    }
                    left = mid + 1;
                }
            }
        }
    }

    /**
     * Counts the number of elements strictly less than the given key.
     *
     * @param nums sorted array
     * @param key the key to compare
     * @return the count of elements less than the key
     */
    public static int getCountLessThan(int[] nums, int key) {
        return getLessThan(nums, key, 0, nums.length - 1);
    }

    /**
     * Helper method using binary search to count elements less than or equal to the key.
     *
     * @param nums sorted array
     * @param key the key to compare
     * @param left current left bound
     * @param right current right bound
     * @return count of elements less than or equal to the key
     */
    public static int getLessThan(int[] nums, int key, int left, int right) {
        int count = 0;
        while (left <= right) {
            int mid = left + ((right - left) >>> 1);
            if (nums[mid] > key) {
                right = mid - 1;
            } else {
                // nums[mid] <= key
                count = mid + 1; // all elements from 0 to mid inclusive are <= key
                left = mid + 1;
            }
        }
        return count;
    }
}
