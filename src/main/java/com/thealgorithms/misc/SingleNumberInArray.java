/*Given a non-empty array of integers nums, every element appears twice except for one.
 Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.
*/
class Solution {
    public int singleNumber(int[] nums) {
        if(nums.length==1)
            return nums[0];
        else
        {
            Arrays.sort(nums);
            for(int x=1;x<nums.length;x=x+2)
            {
                if(nums[x]!=nums[x-1])
                    return nums[x-1];
                    
            }
            return nums[nums.length-1];
        }
    }
}