package com.thealgorithms.misc;

import java.util.*;

public class FourSumProblem {
    public static void main(String[] args) {
        // Take user input for the array and target sum
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();
        int[] nums = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        System.out.print("Enter the target sum: ");
        int target = scanner.nextInt();
        scanner.close();

        // Solve using all five approaches
        List<List<Integer>> results1 = bruteForce4Sum(nums, target);
        List<List<Integer>> results2 = twoPointers4Sum(nums, target);
        List<List<Integer>> results3 = threePointersBinarySearch4Sum(nums, target);
        List<List<Integer>> results4 = hashing4Sum(nums, target);
        List<List<Integer>> results5 = optimizedTwoPointers4Sum(nums, target);

        // Print results
        System.out.println("Results using Brute Force:");
        printQuadruplets(results1);

        System.out.println("Results using Two Pointers:");
        printQuadruplets(results2);

        System.out.println("Results using Three Pointers + Binary Search:");
        printQuadruplets(results3);

        System.out.println("Results using Hashing:");
        printQuadruplets(results4);

        System.out.println("Results using Optimized Two Pointers:");
        printQuadruplets(results5);
    }

    // Helper method to print quadruplets
    private static void printQuadruplets(List<List<Integer>> quadruplets) {
        for (List<Integer> quad : quadruplets) {
            System.out.println(quad);
        }
    }

    // Approach 1: Brute Force O(n^4)
    public static List<List<Integer>> bruteForce4Sum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        int sum = nums[i] + nums[j] + nums[k] + nums[l];
                        if (sum == target) {
                            results.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        }
                    }
                }
            }
        }
        return results;
    }

    // Approach 2: Two Pointers O(n^3)
    public static List<List<Integer>> twoPointers4Sum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        list.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicates
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }

                // Skip duplicates
                while (j < n - 2 && nums[j] == nums[j + 1]) j++;
            }

            // Skip duplicates
            while (i < n - 3 && nums[i] == nums[i + 1]) i++;
        }

        return list;
    }

    // Approach 3: Three Pointers + Binary Search O(n^3 * log(n))
    public static List<List<Integer>> threePointersBinarySearch4Sum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    int complement = target - nums[i] - nums[j] - nums[k];
                    int l = binarySearch(nums, k + 1, n - 1, complement);
                    if (l != -1) {
                        list.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                    }
                }
            }
        }

        return list;
    }

    private static int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // Approach 4: Hashing O(n^2)
    public static List<List<Integer>> hashing4Sum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                Set<Integer> seen = new HashSet<>();
                int target2 = target - nums[i] - nums[j];

                for (int k = j + 1; k < n; k++) {
                    int complement = target2 - nums[k];
                    if (seen.contains(complement)) {
                        list.add(Arrays.asList(nums[i], nums[j], nums[k], complement));
                    }
                    seen.add(nums[k]);
                }
                // Skip duplicates
                while (j < n - 2 && nums[j] == nums[j + 1]) j++;
            }

            // Skip duplicates
            while (i < n - 3 && nums[i] == nums[i + 1]) i++;
        }

        return list;
    }

    // Approach 5: Optimized Two Pointers
    public static List<List<Integer>> optimizedTwoPointers4Sum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // Skip duplicates

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        list.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicates
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return list;
    }
}

