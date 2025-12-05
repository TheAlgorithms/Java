package com.thealgorithms.sorts;

/**
 * QuickSort Algorithm Implementation
 * 
 * QuickSort is a highly efficient sorting algorithm that uses the divide-and-conquer approach
 * to sort elements. It was developed by C. A. R. Hoare and is commonly used in practice because
 * of its efficiency and in-place sorting capability.
 * 
 * When to use QuickSort:
 * - When you need in-place sorting (O(log n) extra space on average)
 * - When average-case performance is important (O(n log n))
 * - When you need to sort large datasets efficiently
 * - When stability is not required
 * 
 * When NOT to use QuickSort:
 * - When stability is required (use MergeSort instead)
 * - When worst-case O(n^2) performance is unacceptable (use HeapSort or MergeSort)
 * - When working with nearly sorted data (consider insertion sort or adaptive sorts)
 * 
 * Complexity Analysis:
 * - Best Case: O(n log n) - when pivot divides array into two nearly equal parts
 * - Average Case: O(n log n) - expected behavior with random pivot selection
 * - Worst Case: O(n^2) - when pivot is always smallest/largest element (rare with random selection)
 * - Space Complexity: O(log n) - for recursion stack in average case, O(n) in worst case
 * 
 * Characteristics:
 * - In-place sorting algorithm (minimal extra space required)
 * - Not stable (relative order of equal elements may change)
 * - Uses randomized pivot selection to avoid worst-case scenarios
 * - Cache-friendly due to good memory access patterns
 * 
 * @author Varun Upadhyay (https://github.com/varunu28)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SortAlgorithm
 */
class QuickSort implements SortAlgorithm {

    /**
     * Main entry point for Quick Sort algorithm
     * This method implements the generic Quick Sort for arrays of comparable objects.
     * The algorithm works by selecting a pivot element and partitioning the array
     * into elements smaller and larger than the pivot, then recursively sorting each partition.
     *
     * @param array The array to be sorted. Sorts the array in increasing order.
     *              The array is modified in-place.
     * @return The sorted array (same reference as input, modified in-place)
     * @param <T> Generic type that must extend Comparable to allow element comparison
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        // Start the recursive sorting process from index 0 to array.length - 1
        doSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * Recursive helper method that performs the actual QuickSort sorting process.
     * This method divides the array into smaller subarrays and recursively sorts them.
     * The division is done using the partition method.
     *
     * How it works:
     * 1. If left >= right, the subarray has 0 or 1 element, so it's already sorted
     * 2. Use randomPartition to find a pivot position that divides the array
     * 3. Recursively sort the left subarray (elements < pivot)
     * 4. Recursively sort the right subarray (elements >= pivot)
     *
     * @param array The array being sorted
     * @param left The leftmost index of the current subarray
     * @param right The rightmost index of the current subarray
     * @param <T> Generic type that must extend Comparable
     */
    private static <T extends Comparable<T>> void doSort(T[] array, final int left, final int right) {
        // Base case: if left >= right, the subarray has 0 or 1 element (already sorted)
        if (left < right) {
            // Partition the array and get the final position of the pivot
            final int pivot = randomPartition(array, left, right);
            
            // Recursively sort the left partition (elements smaller than pivot)
            // Sort from left to pivot - 1 since element at pivot is now in final position
            doSort(array, left, pivot - 1);
            
            // Recursively sort the right partition (elements greater than or equal to pivot)
            // Sort from pivot to right since element at pivot is in final position
            doSort(array, pivot, right);
        }
    }

    /**
     * Selects a random pivot position and partitions the array around it.
     * Using a random pivot helps avoid the O(n^2) worst-case scenario that occurs
     * when the pivot is always the smallest or largest element (common with sorted data).
     *
     * The randomization strategy:
     * - Generates a random index between left and right (inclusive)
     * - Swaps the element at random index with the element at right position
     * - Then performs standard partition with the element at right as pivot
     * This ensures we avoid worst-case scenarios on already sorted or reverse sorted arrays
     *
     * @param array The array being partitioned
     * @param left The leftmost index of the current subarray
     * @param right The rightmost index of the current subarray
     * @return The final partition index where the pivot element is now positioned
     * @param <T> Generic type that must extend Comparable
     */
    private static <T extends Comparable<T>> int randomPartition(T[] array, final int left, final int right) {
        // Generate a random index between left and right (inclusive)
        // The formula: left + random_value ensures the index falls within [left, right]
        final int randomIndex = left + (int) (Math.random() * (right - left + 1));
        
        // Swap the element at randomIndex with the element at right position
        // This moves our randomly selected pivot to the right for standard partition
        SortUtils.swap(array, randomIndex, right);
        
        // Perform standard partition with the element at right as the pivot
        return partition(array, left, right);
    }

    /**
     * Partitions the array around a pivot element.
     * This is the core logic of QuickSort that divides the array into two regions:
     * - Elements smaller than or equal to pivot (on the left)
     * - Elements greater than pivot (on the right)
     *
     * Partitioning Strategy (Two-pointer approach):
     * 1. Choose the middle element as pivot to improve performance on sorted arrays
     * 2. Initialize left pointer at the start and right pointer at the end
     * 3. Move left pointer right until finding an element >= pivot
     * 4. Move right pointer left until finding an element < pivot
     * 5. If pointers haven't crossed, swap the elements (move them to correct sides)
     * 6. Continue until pointers meet or cross
     * 7. Return the position where the pivot is finally placed
     *
     * Time Complexity of this method: O(n) where n is the size of the subarray
     * 
     * Example:
     * Array: [3, 7, 2, 9, 1, 5] with pivot = 5 (middle element at index 2)
     * After partition: [3, 2, 1, 5, 9, 7] where 5 is at its final sorted position
     *
     * @param array The array being partitioned
     * @param left The starting index of the current partition
     * @param right The ending index of the current partition
     * @return The index where the pivot element ends up after partitioning
     * @param <T> Generic type that must extend Comparable
     */
    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        // Select the middle element as the pivot
        // Using middle element improves performance on partially sorted data
        // The >>> operator is unsigned right shift, equivalent to (left + right) / 2 but avoids overflow
        final int mid = (left + right) >>> 1;
        final T pivot = array[mid];
        
        // Two-pointer partitioning approach
        // Loop continues while left and right pointers haven't crossed each other
        while (left <= right) {
            // Move left pointer right until we find an element >= pivot
            // All elements to the left of final left position will be < pivot
            while (SortUtils.less(array[left], pivot)) {
                ++left;
            }
            
            // Move right pointer left until we find an element < pivot
            // All elements to the right of final right position will be >= pivot
            while (SortUtils.less(pivot, array[right])) {
                --right;
            }
            
            // If pointers haven't crossed, swap the elements
            // This places the smaller element on the left side and larger on the right side
            if (left <= right) {
                // Perform the swap to move elements to their correct sides relative to pivot
                SortUtils.swap(array, left, right);
                
                // Move pointers to continue partitioning
                ++left;  // Move left pointer right
                --right; // Move right pointer left
            }
        }
        
        // Return the final position of the left pointer
        // This is where we'll place the pivot in the next recursive step
        // Elements [original_left...left-1] are < pivot
        // Elements [left...original_right] are >= pivot
        return left;
    }
}
