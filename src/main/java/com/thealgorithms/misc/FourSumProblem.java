package com.thealgorithms.misc;

import java.util.*;

public class FourSumProblem {

    // HashMap Approach
    public List<List<Integer>> Hashmap(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> ts = new HashSet<>();
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            hm.put(nums[i], i);
        }

        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    int t = target - nums[i] - nums[j] - nums[k];
                    if (hm.containsKey(t) && hm.get(t) > k) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        temp.add(t);
                        ts.add(temp);
                    }
                }
            }
        }
        return new ArrayList<>(ts);
    }
}
