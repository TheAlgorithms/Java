package com.thealgorithms.prefixsum;

class prefixsum {
    int[] prefix;
    public prefixsum(int[] nums) {
        int n = nums.length;
        prefix = new int[n];
        int cur = 0;
        for (int i = 0; i < n; i++) {
            cur += nums[i];
            prefix[i] = cur;
        }
    }
    public int sumRange(int left, int right) {
        if (left == 0) {
            return prefix[right];
        } else {
            return prefix[right] - prefix[left - 1];
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
        prefixsum obj = new prefixsum(nums);
        System.out.println(obj.sumRange(0, 2)); 
        System.out.println(obj.sumRange(1, 3)); 
    }
}
