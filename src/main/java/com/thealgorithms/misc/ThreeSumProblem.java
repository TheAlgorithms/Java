package com.thealgorithms.misc;

import java.util.*;

public class ThreeSumProblem {

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
        ThreeSumProblem th = new ThreeSumProblem();
        System.out.println(
            "Brute Force Approach\n" + (th.BruteForce(arr, ts)) + "\n"
        );
        System.out.println(
            "Two Pointer Approach\n" + (th.TwoPointer(arr, ts)) + "\n"
        );
        System.out.println("Hashmap Approach\n" + (th.Hashmap(arr, ts)));
    }

    public List<List<Integer>> BruteForce(int[] nums, int target) {
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
        arr =
            new ArrayList<List<Integer>>(new LinkedHashSet<List<Integer>>(arr));
        return arr;
    }

    public List<List<Integer>> TwoPointer(int[] nums, int target) {
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

    public List<List<Integer>> Hashmap(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> ts = new HashSet();
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
        return new ArrayList(ts);
    }
}
