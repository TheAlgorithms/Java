package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
This class finds the majority element(s) in an array of integers.
A majority element is an element that appears more than or equal to n/2 times, where n is the length
of the array.
*/
public class MajorityElement {
    /*
   This method returns the majority element(s) in the given array of integers.
   @param nums: an array of integers
   @return a list of majority elements
   */
    public static List<Integer> majority(int[] nums) {
        HashMap<Integer, Integer> numToCount = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (numToCount.containsKey(nums[i])) {
                numToCount.put(nums[i], numToCount.get(nums[i]) + 1);
            } else {
                numToCount.put(nums[i], 1);
            }
        }
        List<Integer> majorityElements = new ArrayList<>();
        for (int key : numToCount.keySet()) {
            if (numToCount.get(key) >= n / 2) {
                majorityElements.add(key);
            }
        }
        return majorityElements;
    }
}
