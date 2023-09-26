package com.thealgorithms.misc;
import java.util.*;

public class FourSumProblem {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the target sum: ");
        int ts = scan.nextInt();
        System.out.print("Enter the number of elements in the array: ");
        int n = scan.nextInt();
        System.out.println("Enter all your array elements:");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        FourSumProblem fs = new FourSumProblem();
        System.out.println("Brute Force Approach:\n" + fs.bruteForceFourSum(arr, ts) + "\n");
        System.out.println("Two Pointer Approach:\n" + fs.twoPointerFourSum(arr, ts) + "\n");
        System.out.println("Hashmap Approach:\n" + fs.hashmapFourSum(arr, ts));
        scan.close();
    }
    public List<List<Integer>> bruteForceFourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            List<Integer> temp = new ArrayList<>();
                            temp.add(nums[i]);
                            temp.add(nums[j]);
                            temp.add(nums[k]);
                            temp.add(nums[l]);
                            result.add(temp);
                        }
                    }
                }
            }
        }
        return result;
    }
    
    public List<List<Integer>> twoPointerFourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

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
                        result.add(temp);
                        
                        // Move left and right pointers to find other solutions
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

                while (j < nums.length - 2 && nums[j] == nums[j + 1]) j++;
            }

            while (i < nums.length - 3 && nums[i] == nums[i + 1]) i++;
        }

        return result;
    }
    public List<List<Integer>> hashmapFourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                HashMap<Integer, List<Integer>> map = new HashMap<>();

                for (int k = j + 1; k < nums.length; k++) {
                    int complement = target - nums[i] - nums[j] - nums[k];

                    if (map.containsKey(complement)) {
                        List<Integer> temp = map.get(complement);
                        temp.add(nums[k]);
                        result.add(temp);
                    }

                    if (!map.containsKey(nums[k])) {
                        List<Integer> tempList = new ArrayList<>();
                        tempList.add(nums[i]);
                        tempList.add(nums[j]);
                        tempList.add(nums[k]);
                        map.put(nums[k], tempList);
                    }
                }
            }

            while (i < nums.length - 3 && nums[i] == nums[i + 1]) i++;
        }

        return result;
    }
    
}
