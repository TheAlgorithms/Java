package com.thealgorithms.misc;

import java.util.*;

public class TwoSumProblem{
    /**
     * The function "twoSum" takes an array of integers and a target integer as input, and returns an
     * array of two indices where the corresponding elements in the input array add up to the target.
     * 
     * @param nums An array of integers.
     * @param target The target is the sum that we are trying to find using two numbers from the given array.
     * @return The method `twoSum` returns an array of integers.
     * 
     * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
     */
    public static int[] twoSum(int[] arr, int target) {
        HashMap <Integer,Integer> map = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            map.put(arr[i],i);
        }
        for(int i=0;i<arr.length;i++){
            int arrP = arr[i];
            int rem = target - arrP;
            if(map.containsKey(rem)){
                int index = map.get(rem);
                if(index == i){
                    continue;
                }
                return new int[]{i,index};
            }
        }
        return new int[]{};  
    }
}
