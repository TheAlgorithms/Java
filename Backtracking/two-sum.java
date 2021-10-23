class Solution {
   public int[] twoSum(int[] nums, int target) {
        int[] ans= new int[2];
        
        for(int i=0;i<nums.length-1;i++)
        {
            for(int j=1;j<nums.length;j++)
            {
                if(i==j)
                    continue;
                else if (nums[i]+nums[j]==target)
                {
                    ans[0]=i;
                    ans[1]=j;
                }
            }
        }
        return ans;           
        
    }
}