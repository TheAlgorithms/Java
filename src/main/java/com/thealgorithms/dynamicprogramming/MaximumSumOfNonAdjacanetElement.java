package com.thealgorithms.dynamicprogramming;

public final class MaximumSumOfNonAdjacanetElement {

  private MaximumSumOfNonAdjacanetElement() {

  }

  public static int maxSumNonAdjacent(int[] nums) {
    if (nums == null || nums.length == 0)
      return 0;
    if (nums.length == 1)
      return nums[0];

    int[] dp = new int[nums.length];

    dp[0] = nums[0];
    dp[1] = Math.max(nums[0], nums[1]);

    for (int i = 2; i < nums.length; i++) {
      dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
    }

    return dp[nums.length - 1];
  }
}
