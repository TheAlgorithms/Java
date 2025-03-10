package com.thealgorithms.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ThreeSumProblem {

    public List<List<Integer>> bruteForce(int[] nums, int target) {
        List<List<Integer>> arr = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == target) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        Collections.sort(temp);
                        arr.add(temp);
                    }
                }
            }
        }
        arr = new ArrayList<List<Integer>>(new LinkedHashSet<List<Integer>>(arr));
        return arr;
    }

    public List<List<Integer>> twoPointer(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> arr = new ArrayList<List<Integer>>();
        int start = 0;
        int end = 0;
        int i = 0;
        while (i < nums.length - 1) {
            start = i + 1;
            end = nums.length - 1;
            while (start < end) {
                if (nums[start] + nums[end] + nums[i] == target) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[start]);
                    temp.add(nums[end]);
                    arr.add(temp);
                    start++;
                    end--;
                } else if (nums[start] + nums[end] + nums[i] < target) {
                    start += 1;
                } else {
                    end -= 1;
                }
            }
            i++;
        }
        Set<List<Integer>> set = new LinkedHashSet<List<Integer>>(arr);
        return new ArrayList<List<Integer>>(set);
    }

    public List<List<Integer>> hashMap(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> ts = new HashSet<>();
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            hm.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int t = target - nums[i] - nums[j];
                if (hm.containsKey(t) && hm.get(t) > j) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(t);
                    ts.add(temp);
                }
            }
        }
        return new ArrayList<>(ts);
    }
}
