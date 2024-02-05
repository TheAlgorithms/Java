/**
 * Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

/**
 * The {@code CalculateMaxOfMin} class provides a method to calculate the maximum
 * value among the minimum values of a given array.
*/
package com.thealgorithms.stacks;

import java.util.Arrays;

public class CalculateMaxOfMin {

      /**
     * Calculates the maximum value among the minimum values of all subarrays.
     *
     * @param a The input array of integers.
     * @return The maximum value among the minimum values of all subarrays.
     * 
     * The algorithm works by considering subarrays of increasing lengths using
     * a sliding window approach. It calculates the minimum value for each subarray
     * and then finds the maximum value among those minimum values. This process
     * is repeated for different subarray lengths until the entire array is considered.
     * The result is the maximum of the minimum values for all subarrays.
     */
    public static int calculateMaxOfMin(int[] a) {
        int n = a.length;
        int[] ans = new int[n];
        int[] arr2 = Arrays.copyOf(a, n);
        Arrays.sort(arr2);
        int maxNum = arr2[arr2.length - 1];
        ans[0] = maxNum;

        // Iterate through the array to calculate the maximum of the minimum values
        int index = 1;
        while (index != ans.length) {

            // Array to store minimum values for subarrays of the specified length
            int[] minimums = new int[n - index];

            // Calculate minimum values for each subarray
            for (int i = 0; i < n - index; i++) {
                int[] windowArray = Arrays.copyOfRange(a, i, i + index + 1);
                Arrays.sort(windowArray);
                int minNum = windowArray[0];
                minimums[i] = minNum;
            }

            // Sort the minimums array and set the corresponding value in the result array
            Arrays.sort(minimums);
            ans[index] = minimums[minimums.length - 1];

            index += 1;
        }
        return ans[0];
    }
}

