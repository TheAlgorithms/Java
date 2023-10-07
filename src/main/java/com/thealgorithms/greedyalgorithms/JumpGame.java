package com.thealgorithms.greedyalgorithms;

// Problem Link: https://leetcode.com/problems/jump-game/description/

public class JumpGame{

    public boolean canJump(int[] nums) {
        int reachable = 0;
       for(int i = 0; i < nums.length; i ++) {
           if(i > reachable) return false;
           reachable = Math.max(reachable, i + nums[i]);
       } 
       return true;
    }
}