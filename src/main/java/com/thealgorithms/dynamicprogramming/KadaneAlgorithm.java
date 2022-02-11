/** Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

/** Program description - To find the maximum subarray sum */
 package com.thealgorithms.dynamicprogramming;

public class KadaneAlgorithm {
    public static boolean max_Sum(int a[])
    {
        int sum=a[0],running_sum=0;
        for(int k:a)
        {
            running_sum=running_sum+k;
            // running sum of all the indexs are stored
            sum=Math.max(sum,running_sum);
            // the max is stored inorder to the get the maximum sum
            if(running_sum<0)
            running_sum=0;
            // if running sum is negative then it is initialized to zero
        }
        // for-each loop is used to iterate over the array and find the maximum subarray sum
        return task(a,sum);
        // the max_Sum method returns true if the "sub array sum" matches with the "sum of all the elements present in the array" else it returns false
    }
    /**
     * OUTPUT :
     * Input - {89,56,98,123,26,75,12,40,39,68,91}
     * Output: it returns either true or false
     * 1st approach Time Complexity : O(n)
     * Auxiliary Space Complexity : O(1)
     */
    static boolean task(int a[] , int s)
    {
        int p=0;
        // p is created inorder to store the sum of the elements present in the array
        for(int i:a)
        {
            p=p+i;
        }
        // for-each loop is initialized inorder to get the sum of all the elements of the array
        return p==s;
        // the task method adds all the elements of the array and checks it with the "maximum subarray sum". If the sum is equal then it returns true else it returns false
    }
}
