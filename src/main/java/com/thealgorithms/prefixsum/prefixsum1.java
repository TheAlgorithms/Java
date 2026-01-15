package com.thealgorithms.prefixsum;

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int currentsum = 0;
        int low = 0;
        int high = 0;

        int minlenwindow = Integer.MAX_VALUE;

        for (high = 0; high < nums.length; high++) {
            currentsum += nums[high];

            while (currentsum >= target) {
                int currentwindow = high - low + 1;
                minlenwindow = Math.min(minlenwindow, currentwindow);
                currentsum = currentsum - nums[low];
                low++;
            }
        }

        return minlenwindow == Integer.MAX_VALUE ? 0 : minlenwindow;
    }
}
