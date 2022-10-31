// Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. 
// The relative order of the elements should be kept the same.
// Since it is impossible to change the length of the array in some languages, you must instead have the result be placed 
// in the first part of the array nums. More formally, if there are k elements after removing the duplicates, 
// then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.

// Return k after placing the final result in the first k slots of nums.


package com.thealgorithms.strings;
import java.util.*;

public class RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        ArrayList<Integer> intList = new ArrayList<Integer>(nums.length);
        for (int i : nums)
            {
                intList.add(i);
            }
        
        Set<Integer> s = new LinkedHashSet<Integer>(intList);
        int n = s.size();
        int x = 0;
        for(Integer num: s){
            nums[x]=num;
            x++;
        }
        return n;
    }
}
