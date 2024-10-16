package com.thealgorithms.bitmanipulation;

/**
 * This class provides a method to find the element that appears an
 * odd number of times in an array. All other elements in the array
 * must appear an even number of times for the logic to work.
 *
 * The solution uses the XOR operation, which has the following properties:
 * - a ^ a = 0 (XOR-ing the same numbers cancels them out)
 * - a ^ 0 = a
 * - XOR is commutative and associative.
 *
 * Time Complexity: O(n), where n is the size of the array.
 * Space Complexity: O(1), as no extra space is used.
 *
 * Usage Example:
 * int result = NumberAppearingOddTimes.findOddOccurrence(new int[]{1, 2, 1, 2, 3});
 * // result will be 3
 *
 * @author Lakshyajeet Singh Goyal (https://github.com/DarkMatter-999)
 */

public final class NumberAppearingOddTimes {
    private NumberAppearingOddTimes() {
    }

    /**
     * Finds the element in the array that appears an odd number of times.
     *
     * @param arr the input array containing integers, where all elements
     *            except one appear an even number of times.
     * @return the integer that appears an odd number of times.
     */
    public static int findOddOccurrence(int[] arr) {
        int result = 0;
        for (int num : arr) {
            result ^= num;
        }
        return result;
    }
}
