package com.thealgorithms.backtracking;

import java.util.*;
// author => divij kathuria (github=>dikapitacion)
class Subsets {
    void solve(int[] nums,List<List<Integer>> ans,List<Integer> output,int index){
        if(index==nums.length){
            ans.add(new ArrayList<>(output));
            return;
        }
        //include
        output.add(nums[index]);
        solve(nums,ans,output,index+1);
        //exclude
        output.remove(output.size()-1);
        solve(nums,ans,output,index+1);
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> output = new ArrayList<Integer>();
        solve(nums,ans,output,0);
        return ans;
    }
}