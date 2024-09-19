package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
This class finds the majority element(s) in an array of integers.
A majority element is an element that appears more than or equal to n/2 times, where n is the length
of the array.
*/
public final class MajorityElement {
    private MajorityElement() {
    }
    /*
   This method returns the majority element(s) in the given array of integers.
   @param nums: an array of integers
   @return a list of majority elements
   */
    public static List<Integer> majority(int[] nums) {
        HashMap<Integer, Integer> numToCount = new HashMap<>();
        for (final var num : nums) {
            numToCount.merge(num, 1, Integer::sum);
        }
        List<Integer> majorityElements = new ArrayList<>();
        for (final var entry : numToCount.entrySet()) {
            if (entry.getValue() >= nums.length / 2) {
                majorityElements.add(entry.getKey());
            }
        }
        return majorityElements;
    }
}
