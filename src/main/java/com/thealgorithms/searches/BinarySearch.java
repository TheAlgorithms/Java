package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * Binary Search Algorithm Implementation
 *
 * <p>Binary search is one of the most efficient searching algorithms for finding a target element
 * in a SORTED array. It works by repeatedly dividing the search space in half, eliminating half of
 * the remaining elements in each step.
 *
 * <p>IMPORTANT: This algorithm ONLY works correctly if the input array is sorted in ascending
 * order.
 *
 * <p>Algorithm Overview: 1. Start with the entire array (left = 0, right = array.length - 1) 2.
 * Calculate the middle index 3. Compare the middle element with the target: - If middle element
 * equals target: Found! Return the index - If middle element is less than target: Search the right
 * half - If middle element is greater than target: Search the left half 4. Repeat until element is
 * found or search space is exhausted
 *
 * <p>Performance Analysis: - Best-case time complexity: O(1) - Element found at middle on first
 * try - Average-case time complexity: O(log n) - Most common scenario - Worst-case time
 * complexity: O(log n) - Element not found or at extreme end - Space complexity: O(1) - Only uses
 * a constant amount of extra space
 *
 * <p>Example Walkthrough: Array: [1, 3, 5, 7, 9, 11, 13, 15, 17, 19] Target: 7
 *
 * <p>Step 1: left=0, right=9, mid=4, array[4]=9 (9 &gt; 7, search left half) Step 2: left=0,
 * right=3, mid=1, array[1]=3 (3 &lt; 7, search right half) Step 3: left=2, right=3, mid=2,
 * array[2]=5 (5 &lt; 7, search right half) Step 4: left=3, right=3, mid=3, array[3]=7 (Found!
 * Return index 3)
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SearchAlgorithm
 * @see IterativeBinarySearch
 */
class BinarySearch implements SearchAlgorithm {

    /**
     * Generic method to perform binary search on any comparable type. This is the main entry point
     * for binary search operations.
     *
     * <p>Example Usage:
     * <pre>
     * Integer[] numbers = {1, 3, 5, 7, 9, 11};
     * int result = new BinarySearch().find(numbers, 7);
     * // result will be 3 (index of element 7)
     *
     * int notFound = new BinarySearch().find(numbers, 4);
     * // notFound will be -1 (element 4 does not exist)
     * </pre>
     *
     * @param <T> The type of elements in the array (must be Comparable)
     * @param array The sorted array to search in (MUST be sorted in ascending order)
     * @param key The element to search for
     * @return The index of the key if found, -1 if not found or if array is null/empty
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        // Handle edge case: empty array
        if (array == null || array.length == 0) {
            return -1;
        }

        // Delegate to the core search implementation
        return search(array, key, 0, array.length - 1);
    }

    /**
     * Core recursive implementation of binary search algorithm. This method divides the problem
     * into smaller subproblems recursively.
     *
     * <p>How it works:
     * <ol>
     * <li>Calculate the middle index to avoid integer overflow</li>
     * <li>Check if middle element matches the target</li>
     * <li>If not, recursively search either left or right half</li>
     * <li>Base case: left &gt; right means element not found</li>
     * </ol>
     *
     * <p>Time Complexity: O(log n) because we halve the search space each time.
     * Space Complexity: O(log n) due to recursive call stack.
     *
     * @param <T> The type of elements (must be Comparable)
     * @param array The sorted array to search in
     * @param key The element we're looking for
     * @param left The leftmost index of current search range (inclusive)
     * @param right The rightmost index of current search range (inclusive)
     * @return The index where key is located, or -1 if not found
     */
    private <T extends Comparable<T>> int search(T[] array, T key, int left, int right) {
        // Base case: Search space is exhausted
        // This happens when left pointer crosses right pointer
        if (right < left) {
            return -1; // Key not found in the array
        }

        // Calculate middle index
        // Using (left + right) / 2 could cause integer overflow for large arrays
        // So we use: left + (right - left) / 2 which is mathematically equivalent
        // but prevents overflow
        int median = (left + right) >>> 1; // Unsigned right shift is faster division by 2

        // Get the value at middle position for comparison
        int comp = key.compareTo(array[median]);

        // Case 1: Found the target element at middle position
        if (comp == 0) {
            return median; // Return the index where element was found
        }
        // Case 2: Target is smaller than middle element
        // This means if target exists, it must be in the LEFT half
        else if (comp < 0) {
            // Recursively search the left half
            // New search range: [left, median - 1]
            return search(array, key, left, median - 1);
        }
        // Case 3: Target is greater than middle element
        // This means if target exists, it must be in the RIGHT half
        else {
            // Recursively search the right half
            // New search range: [median + 1, right]
            return search(array, key, median + 1, right);
        }
    }
}
