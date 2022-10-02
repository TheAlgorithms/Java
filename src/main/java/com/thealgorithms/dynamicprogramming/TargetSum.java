//[Problem Description] (https://leetcode.com/problems/target-sum/) 

class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        return helper(nums, target, 0, 0, 0);
    }
    private int helper(int[] nums, int target, int index, int count, int ans){
        if(index < 0){
            return 0;
        }
        if(index == nums.length ){
            if(target == count){
                ans++;
            } 
            return ans;
        }
        int sub =helper(nums, target, index +1, count -nums[index], ans);
        int sum =helper(nums, target, index +1, count +nums[index], ans);
        
        return sub + sum;
    }
}
