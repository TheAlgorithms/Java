package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/*
This class finds the majority element(s) in an array of integers.
A majority element is an element that appears more than or more than equal to n/2 times, where n is the length of the array.
*/
public class MajorityElement {
      /*
     This method returns the majority element(s) in the given array of integers.
     @param nums: an array of integers
     @return a list of majority elements
     */
    public static List<Integer> majority(int[] nums){
        if (nums.length == 0) {
            return new ArrayList<>(); // return an empty list if input array is empty
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            } else {
                map.put(nums[i],1);
            }
        }
        List<Integer> majorityElements = new ArrayList<>();
        for (int key: map.keySet()) {
            if (map.get(key) >= n/2){
                majorityElements.add(key);
            }
        }
        return majorityElements;
    }
}
