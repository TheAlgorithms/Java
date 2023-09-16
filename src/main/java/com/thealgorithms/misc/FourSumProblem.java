import java.util.*;

public class FourSumProblem {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the target sum: ");
        int target = scan.nextInt();
        System.out.print("Enter the number of elements in the array: ");
        int n = scan.nextInt();
        System.out.println("Enter all your array elements:");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scan.nextInt();
        }
        FourSumProblem solution = new FourSumProblem();
        List<List<Integer>> result = solution.fourSum(nums, target);
        
        System.out.println("Unique Quadruplets:");
        for (List<Integer> quad : result) {
            System.out.println(quad);
        }
        
        scan.close();
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> quadruplets = new ArrayList<>();

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // Skip duplicates
            }
            
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue; // Skip duplicates
                }
                
                int left = j + 1;
                int right = nums.length - 1;
                
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    
                    if (sum == target) {
                        List<Integer> quadruplet = Arrays.asList(nums[i], nums[j], nums[left], nums[right]);
                        quadruplets.add(quadruplet);
                        
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
            }
        }

        return quadruplets;
    }
}
// https://codewithgeeks.com/4-sum-leetcode-solution/
// https://redquark.org/leetcode/0018-4-sum/
