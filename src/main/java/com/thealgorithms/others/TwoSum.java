import java.util.*;
public class TwoSum{
    public int[] sum(int[] nums, int target) {
        int index = 0;
        int comp2 = 0;
        Map<Integer,Integer> val = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int comp = target - nums[i];
            if(val.containsKey(comp)){
                index = i;
                comp2=comp;
                break;
            }
            val.put(nums[i],i);
        }
        return new int[]{val.get(comp2),index};
    }
}

//Time Complexity = O(n)
//Space Complexity = O(n)

//In order to achieve a space complexity of O(1) the input can be sorted and a two pointer approach can be used in which case the time complexity would become O(nlogn)
