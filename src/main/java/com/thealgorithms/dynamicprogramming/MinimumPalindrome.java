/*
Given a string s. In one step you can insert any character at any index of the string.
Return the minimum number of steps to make s palindrome.
A Palindrome String is one that reads the same backward as well as forward.
*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int K) {
      int n = nums.length;
      if (n * K == 0) return new int[0];
      if (K == 1) return nums;

      int [] left = new int[n];
      left[0] = nums[0];

      int [] right = new int[n];
      right[n - 1] = nums[n - 1];

      for (int i = 1; i < n; i++) 
      {
        if (i % K == 0) left[i] = nums[i];  
        else left[i] = Math.max(left[i - 1], nums[i]);

        int j = n - i - 1;
        if ((j + 1) % K == 0) right[j] = nums[j];  // block_end
        else right[j] = Math.max(right[j + 1], nums[j]);
      }

      int [] output = new int[n - K + 1];
      for (int i = 0; i < n - K + 1; i++)
        output[i] = Math.max(left[i + K - 1], right[i]);

      return output;
    }
}
