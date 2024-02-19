/**
 * Author: Siddhant Swarup Mallick
 * Contributor: Sarthak Chaudhary
 * Github: https://github.com/siddhant2002
 * Github: https://github.com/SarthakChaudhary46
 *
 * Program Description:
 * Given an integer array, the task is to find the maximum of the minimum values
 * in the array.
 */

package com.thealgorithms.stacks;

import java.util.Arrays;

public class CalculateMaxOfMin {

    public static int calculateMaxOfMin(int[] a) {
        int n = a.length;
        if (n == 0) {
            return 0;
        }
        int[] ans = new int[n];
        int[] arr2 = Arrays.copyOf(a, n);
        Arrays.sort(arr2);
        int maxNum = arr2[arr2.length - 1];
        ans[0] = maxNum;
        int index = 1;
        while (index != ans.length) {
            int[] minimums = new int[n - index];
            for (int i = 0; i < n - index; i++) {
                int[] windowArray = Arrays.copyOfRange(a, i, i + index + 1);
                Arrays.sort(windowArray);
                int minNum = windowArray[0];
                minimums[i] = minNum;
            }
            Arrays.sort(minimums);
            ans[index] = minimums[minimums.length - 1];
            index += 1;
        }
        return ans[0];
    }
}
