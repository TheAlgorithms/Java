/** Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

/** Program description - Given an integer array. The task is to find the maximum of the minimum of the array */
package com.thealgorithms.datastructures.stacks;

import java.util.*;

public class CalculateMaxOfMin {
    public static boolean calculateMaxOfMin(int[] a, int b[], int n) {
        int[] ans = new int[n];
        int[] arr2 = Arrays.copyOf(a, a.length);
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
        for (int i = 0; i < b.length; i++) {
            if (b[i] != ans[i]) {
                return false;
                // checks whether the calculated answer matches with the expected answer
            }
        }
        return true;
        // returns true if calculated answer matches with the expected answer
    }
}
/**
 * Given an integer array. The task is to find the maximum of the minimum of the
 * given array
 */