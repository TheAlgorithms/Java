import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);

        // select the first element of the quadruplet
        for (int i = 0; i < n; i++) {
            // Skip duplicate 
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // select the second element 
            for (int j = i + 1; j < n; j++) {
                // Skip duplicate 
                if (j != i + 1 && nums[j] == nums[j - 1]) continue;
                //assign two pointers
                int k = j + 1;
                int l = n - 1;

                // Two-pointer approach to find the remaining two elements
                while (k < l) {
                    // Calculate the sum of the current quadruplet
                    long sum = nums[i];
                    sum += nums[j];
                    sum += nums[k];
                    sum += nums[l];

                    if (sum == target) {
                        //if the sum matches the target store it in ans
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        temp.add(nums[l]);
                        ans.add(temp);

                        // Move both pointers 
                        k++;
                        l--;

                        // skip duplicates for the third element & forth ele
                        while (k < l && nums[k] == nums[k - 1]) k++;
                        while (k < l && nums[l] == nums[l + 1]) l--;
                    } 
                    else if (sum < target)
                        // If the sum < target, move the left pointer (k) to increase the sum
                        //bcz array is in sorting order so next element always greater 
                        k++;
                    else 
                        // If the sum > the target, move the right pointer (l) to decrease the sum
                        //bcz array is in sorting order so previous element of 'l' pointer always smaller
                        l--;
                }
            }
        }
        return ans;
    }
}
