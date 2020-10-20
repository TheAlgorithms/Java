package Others;
import java.util.*;

/** 
 * Reference/more information:
* https://leetcode.com/problems/two-sum/
*/


public class TwoSum {

    public static void main(String[] args) {

        int[] test = {2,7,11,15};
        int[] result = {0,1};

        assert twoSum(test, 9) == result;
        
    }

    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
    
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
    
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}
