package com.thealgorithms.misc;
import java.util.*;
public class FourSumProblem {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length; // size of the array
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); // sort the given array
        for (int i = 0; i < n; i++) { // calculating the quadruplets
            if (i > 0 && nums[i] == nums[i - 1]) continue; // avoid the duplicates while moving i
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) // avoid the duplicates while moving j
                    continue;
                int k = j + 1; // 2 pointers
                int l = n - 1;
                while (k < l) {
                    long sum = nums[i];
                    sum += nums[j];
                    sum += nums[k];
                    sum += nums[l];
                    if (sum == target) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        temp.add(nums[l]);
                        ans.add(temp);
                        k++;
                        l--;

                        while (k < l && nums[k] == nums[k - 1])// skip the duplicates
                            k++;
                        while (k < l && nums[l] == nums[l + 1])
                            l--;
                    } else if (sum < target)
                        k++;
                    else
                        l--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 3, 4, 4, 2, 1, 2, 1, 1};
        int target = 9;
        List<List<Integer>> ans = fourSum(nums, target);
        System.out.println("The quadruplets are: ");
        for (List<Integer> it : ans) {
            System.out.print("[");
            for (int ele : it) {
                System.out.print(ele + " ");
            }
            System.out.print("] ");
        }
        System.out.println();
    }
}
