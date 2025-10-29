package com.thealgorithms.slidingwindow;

public class SmallestSubarrayWithSum {

    public static int smallestSubarrayLen(int[] arr, int target) {
        if (arr == null || arr.length == 0) return 0;
        int left = 0, sum = 0, minLen = Integer.MAX_VALUE;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];
            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= arr[left++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
