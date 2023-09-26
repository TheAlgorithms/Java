package com.thealgorithms.misc;

import java.util.*;

public class FourSumProblem {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the target sum ");
        int ts = scan.nextInt();
        System.out.print("Enter the number of elements in the array ");
        int n = scan.nextInt();
        System.out.println("Enter all your array elements:");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        FourSumProblem th = new FourSumProblem();
        System.out.println("Brute Force Approach\n" + (th.BruteForce(arr, ts)) + "\n");
        System.out.println("Two Pointer Approach\n" + (th.TwoPointer(arr, ts)) + "\n");
        System.out.println("Hashmap Approach\n" + (th.Hashmap(arr, ts)));
        scan.close();
    }

    // Brute Force Approach
    public List<List<Integer>> BruteForce(int[] nums, int target) {
        List<List<Integer>> arr = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            List<Integer> temp = new ArrayList<>();
                            temp.add(nums[i]);
                            temp.add(nums[j]);
                            temp.add(nums[k]);
                            temp.add(nums[l]);
                            Collections.sort(temp);
                            arr.add(temp);
                        }
                    }
                }
            }
        }
        arr = new ArrayList<List<Integer>>(new LinkedHashSet<List<Integer>>(arr));
        return arr;
    }

    // Two Pointer Approach
    public List<List<Integer>> TwoPointer(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> arr = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        arr.add(temp);

                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }

                while (j < nums.length - 2 && nums[j] == nums[j + 1]) {
                    j++;
                }
            }

            while (i < nums.length - 3 && nums[i] == nums[i + 1]) {
                i++;
            }
        }

        return arr;
    }

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
