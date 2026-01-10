package com.thealgorithms.slidingwindow;

public class CountNiceSubarrays {
    private CountNiceSubarrays() {
    }

    public static int countNiceSubarrays(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        int oddCount = 0;
        int result = 0;
        int[] memo = new int[n];

        for (int right = 0; right < n; right++) {
            if ((nums[right] & 1) == 1) {
                oddCount++;
            }

            if (oddCount > k) {
                left += memo[left];
                oddCount--;
            }

            if (oddCount == k) {
                if (memo[left] == 0) {
                    int count = 0;
                    int temp = left;
                    while ((nums[temp] & 1) == 0) {
                        count++;
                        temp++;
                    }
                    memo[left] = count + 1;
                }
                result += memo[left];
            }
        }
        return result;
    }
}
