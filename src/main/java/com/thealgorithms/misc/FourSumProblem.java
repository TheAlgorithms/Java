package com.thealgorithms.misc;

import java.util.*;


public class FourSumProblem {
    public static void main(String[] args) {
        // Take user input for the array and target sum
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();
        int[] Nums = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            Nums[i] = scanner.nextInt();
        }
        System.out.print("Enter the target sum: ");
        int target = scanner.nextInt();
        scanner.close();

        // Solve using all five approaches
        List<List<Integer>> results1 = BruteForce4Sum(Nums, target);
        List<List<Integer>> results2 = TwoPointers4Sum(Nums, target);
        List<List<Integer>> results3 = ThreePointersBinarySearch4Sum(Nums, target);
        List<List<Integer>> results4 = Hashing4Sum(Nums, target);
        List<List<Integer>> results5 = OptimizedTwoPointers4Sum(Nums, target);

        // Print results
        System.out.println("Results using Brute Force:");
        PrintQuadruplets(results1);

        System.out.println("Results using Two Pointers:");
        PrintQuadruplets(results2);

        System.out.println("Results using Three Pointers + Binary Search:");
        PrintQuadruplets(results3);

        System.out.println("Results using Hashing:");
        PrintQuadruplets(results4);

        System.out.println("Results using Optimized Two Pointers:");
        PrintQuadruplets(results5);
    }

    // Helper method to print quadruplets
    private static void PrintQuadruplets(List<List<Integer>> quadruplets) {
        for (List<Integer> quad : quadruplets) {
            System.out.println(quad);
        }
    }

    // Approach 1: Brute Force O(n^4)
    public static List<List<Integer>> BruteForce4Sum(int[] Nums, int target) {
        List<List<Integer>> List = new ArrayList<>();
        Arrays.sort(Nums);
        int n = Nums.length;

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        int sum = Nums[i] + Nums[j] + Nums[k] + Nums[l];
                        if (sum == target) {
                            List.add(Arrays.asList(Nums[i], Nums[j], Nums[k], Nums[l]));
                        }
                    }
                }
            }
        }
        return List;
    }

    // Approach 2: Two Pointers O(n^3)
    public static List<List<Integer>> TwoPointers4Sum(int[] Nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(Nums);
        int n = Nums.length;

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    int sum = Nums[i] + Nums[j] + Nums[left] + Nums[right];
                    if (sum == target) {
                        list.add(Arrays.asList(Nums[i], Nums[j], Nums[left], Nums[right]));

                        // Skip duplicates
                        while (left < right && Nums[left] == Nums[left + 1]) left++;
                        while (left < right && Nums[right] == Nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }

                // Skip duplicates
                while (j < n - 2 && Nums[j] == Nums[j + 1]) j++;
            }

            // Skip duplicates
            while (i < n - 3 && Nums[i] == Nums[i + 1]) i++;
        }

        return list;
    }

    // Approach 3: Three Pointers + Binary Search O(n^3 * log(n))
    //   https://en.wikipedia.org/wiki/Binary_search_algorithm
    public static List<List<Integer>> ThreePointersBinarySearch4Sum(int[] Nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(Nums);
        int n = Nums.length;

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    int complement = target - Nums[i] - Nums[j] - Nums[k];
                    int l = BinarySearch(Nums, k + 1, n - 1, complement);
                    if (l != -1) {
                        list.add(Arrays.asList(Nums[i], Nums[j], Nums[k], Nums[l]));
                    }
                }
            }
        }

        return list;
    }

    private static int BinarySearch(int[] Nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (Nums[mid] == target) {
                return mid;
            } else if (Nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // Approach 4: Hashing O(n^2)
    public static List<List<Integer>> Hashing4Sum(int[] Nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(Nums);
        int n = Nums.length;

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                Set<Integer> seen = new HashSet<>();
                int target2 = target - Nums[i] - Nums[j];

                for (int k = j + 1; k < n; k++) {
                    int complement = target2 - Nums[k];
                    if (seen.contains(complement)) {
                        list.add(Arrays.asList(Nums[i], Nums[j], Nums[k], complement));
                    }
                    seen.add(Nums[k]);
                }
                // Skip duplicates
                while (j < n - 2 && Nums[j] == Nums[j + 1]) j++;
            }

            // Skip duplicates
            while (i < n - 3 && Nums[i] == Nums[i + 1]) i++;
        }

        return list;
    }

    // Approach 5: Optimized Two Pointers
    public static List<List<Integer>> OptimizedTwoPointers4Sum(int[] Nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(Nums);
        int n = Nums.length;

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && Nums[i] == Nums[i - 1]) continue; // Skip duplicates

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && Nums[j] == Nums[j - 1]) continue; // Skip duplicates

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    int sum = Nums[i] + Nums[j] + Nums[left] + Nums[right];
                    if (sum == target) {
                        list.add(Arrays.asList(Nums[i], Nums[j], Nums[left], Nums[right]));

                        // Skip duplicates
                        while (left < right && Nums[left] == Nums[left + 1]) left++;
                        while (left < right && Nums[right] == Nums[right - 1]) right--;

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

