import java.util.*;

/**
 * Solution for The Four Sum Problem.
 * @author md-shamim-ahmad
 */
public class FourSumProblem {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // deduplicate
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue; // deduplicate
                }
                int sum = target - nums[i] - nums[j];
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[right] > sum) {
                        right--;
                    } else if (nums[left] + nums[right] < sum) {
                        left++;
                    } else {
                        result.add(Arrays.asList(new Integer[]{nums[i], nums[j], nums[left++], nums[right--]}));
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    }
                }
            }
        }
        return result;
    }
}
