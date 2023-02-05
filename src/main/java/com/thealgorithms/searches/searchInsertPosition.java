package com.thealgorithms.searches;

/*
Leet code 35 problem :-
 Given a sorted array of distinct integers and a target value, return the index if the target is found.
 If not, return the index where it would be if it were inserted in order.You must write an algorithm 
 with O(log n) runtime complexity.

Example :-
        Input :-- nums = [1,3,5,6], target = 5
        Output :-- 2.
 */

import java.util.Scanner;
public class searchInsertPosition {
    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in);
        int nums[]=new int[5];
        for(int i=0;i<nums.length;i++){
            nums[i]=sc.nextInt();
        }
        System.out.println("enter a target to find the index of the number");
        int target=sc.nextInt();
        sc.close();
        System.out.println(searchInsert(nums, target));
    }
    public static int searchInsert(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            if(nums[i]>=target){
                return i;
            }
        }
        return nums.length;
    }
}
/*
 * Time Complexitity :-- O(logn)-- Best case ,Average case .
 * In worst case like (corner cases) end element in array, It will take O(n);
 * Space Complexitity :-- O(1)  
 */