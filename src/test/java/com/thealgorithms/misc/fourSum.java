import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class fourSum {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4)
            return result;

        Arrays.sort(nums); // Sort the array first

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue; // Skip duplicates
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue; // Skip duplicates
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1])
                            left++; // Skip duplicates
                        while (left < right && nums[right] == nums[right - 1])
                            right--; // Skip duplicates
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
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = { 1, 0, -1, 0, -2, 2 };
        int target1 = 0;
        System.out.println(fourSum(arr1, target1));

        int[] arr2 = { 4, 3, 3, 4, 4, 2, 1, 2, 1, 1 };
        int target2 = 9;
        System.out.println(fourSum(arr2, target2));
    }
}
