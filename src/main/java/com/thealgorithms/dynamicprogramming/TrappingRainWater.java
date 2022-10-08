package com.thealgorithms.dynamicprogramming;

/*Problem Statement :
   Given n non-negative integers representing an elevation map where the width 
   of each bar is 1, compute how much water it can trap after raining.
 */
/*Variables used in code :
   l ----> left pointer initially pointing to (0th index) of height array.
   r ----> right pointer initially pointing to (height.length-1).
   maxl ----> maximum height towards left side of height array.
   maxr ----> maximum height towards right side of height array.
 */
public class TrappingRainWater {
    // Driver code
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int Trapped_water = trap(height);
        System.out.println("Water trap after raining : "+Trapped_water);
    }
    //function to find traped water
    public static int trap(int[] height) {
        if(height.length==1) {
            return 0;
        }
        //Declearing the variables with their initial values
        int l=0; 
        int r=height.length-1;
        int maxl=height[l]; 
        int maxr=height[r];
        int Trapped_water=0;
        
        while(l<=r)
        {
           maxl=Math.max(maxl,height[l]);
           maxr=Math.max(maxr,height[r]);
           
           if(height[l]<=height[r]){

             int ans=maxl-height[l];
             if(!(ans<=0))
             {Trapped_water+=ans;}
              l++;
           }
           else{
             
             int ans=maxr-height[r];
             if(!(ans<=0))
             {Trapped_water+=ans;}
             r--;
           }
        }
        return Trapped_water;
    }
}

/*
 Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9
 */

 //Problem statement link : (https://leetcode.com/problems/trapping-rain-water/)