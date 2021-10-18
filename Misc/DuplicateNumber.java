//question link : https://leetcode.com/problems/find-the-duplicate-number/

class Solution {
    public int findDuplicate(int[] nums) {
      int i = 0;
      while (i<nums.length){
	  //calculate the correct index 
          int correct = nums[i] -1;
          if (nums[i]!=nums[correct]){
              swap(nums,i,correct);
          }else{
              i++;
          }
      }
        for (int index = 0;index<nums.length;index++){
            if (nums[index] != index+1){
                return nums[index];
            }
        }
        return -1;
    }
    //swapping values
    void swap(int[] nums,int first,int second){
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}


