/**
 * Binary Search Algorithm Implementation
 *
 * <p>Binary search is an efficient algorithm used to find a target element in a
 * <b>sorted array</b>. It works by repeatedly dividing the search space into half,
 * reducing the number of comparisons significantly.
 *
 * <p><b>Important:</b> The input array must be sorted in ascending order.
 * Otherwise, the result will be incorrect.
 *
 * <p><b>Simple Example:</b>
 * Array: [2, 4, 6, 8, 10]
 * Target: 6
 * Output: 2
 *
 * <p><b>Algorithm Steps:</b>
 * <ul>
 *   <li>Initialize left = 0 and right = n - 1</li>
 *   <li>Find middle index: mid = left + (right - left) / 2</li>
 *   <li>If array[mid] == target → return index</li>
 *   <li>If target < array[mid] → search left half</li>
 *   <li>If target > array[mid] → search right half</li>
 *   <li>Repeat until element is found or range becomes invalid</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(log n)</p>
 * <p><b>Space Complexity:</b> O(1)</p>
 *
 * <p><b>Detailed Example:</b>
 * Array: [1, 3, 5, 7, 9]
 * Target: 7
 *
 * Step 1: mid=2 → value=5 → search right
 * Step 2: mid=3 → value=7 → Found at index 3
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SearchAlgorithm
 * @see IterativeBinarySearch
 */