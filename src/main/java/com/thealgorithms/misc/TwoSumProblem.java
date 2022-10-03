package com.thealgorithms.misc;

import java.util.*;
import java.util.stream.Collectors;

public class TwoSumProblem {

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the target sum ");
        int ts = scan.nextInt();
        System.out.print("Enter the number of elements in the array ");
        int n = scan.nextInt();
        System.out.println("Enter all your array elements:");
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        TwoSumProblem t = new TwoSumProblem();
        System.out.println(
            "Brute Force Approach\n" +
            Arrays.toString(t.BruteForce(arr, ts)) +
            "\n"
        );
        System.out.println(
            "Two Pointer Approach\n" +
            Arrays.toString(t.TwoPointer(arr, ts)) +
            "\n"
        );
        System.out.println(
            "Hashmap Approach\n" + Arrays.toString(t.HashMap(arr, ts))
        );
    }

    public int[] BruteForce(int[] nums, int target) {
        //Brute Force Approach
        int ans[] = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ans[0] = i;
                    ans[1] = j;

                    break;
                }
            }
        }

        return ans;
    }

    public int[] TwoPointer(int[] nums, int target) {
        // HashMap Approach
        int ans[] = new int[2];
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            hm.put(i, nums[i]);
        }
        HashMap<Integer, Integer> temp = hm
            .entrySet()
            .stream()
            .sorted((i1, i2) -> i1.getValue().compareTo(i2.getValue()))
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
                )
            );

        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int currSum = (Integer) temp.values().toArray()[start] +
            (Integer) temp.values().toArray()[end];

            if (currSum == target) {
                ans[0] = (Integer) temp.keySet().toArray()[start];
                ans[1] = (Integer) temp.keySet().toArray()[end];
                break;
            } else if (currSum > target) {
                end -= 1;
            } else if (currSum < target) {
                start += 1;
            }
        }
        return ans;
    }

    public int[] HashMap(int[] nums, int target) {
        //Using Hashmaps
        int ans[] = new int[2];
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            hm.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int t = target - nums[i];
            if (hm.containsKey(t) && hm.get(t) != i) {
                ans[0] = i;
                ans[1] = hm.get(t);
                break;
            }
        }

        return ans;
    }
}
