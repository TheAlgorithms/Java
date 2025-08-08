package com.thealgorithms.slidingwindow;

/**
 * The Longest Subarray with Sum Less Than or Equal to k algorithm finds the length
 * of the longest subarray whose sum is less than or equal to a given value k.
 *
 * <p>
 * Worst-case performance O(n)
 * Best-case performance O(n)
 * Average performance O(n)
 * Worst-case space complexity O(1)
 *
 * @author https://github.com/Chiefpatwal
 */
public final class LongestSubarrayWithSumLessOrEqualToK {

    // Prevent instantiation
    private LongestSubarrayWithSumLessOrEqualToK() {
    }

    /**
     * This method finds the length of the longest subarray with a sum less than or equal to k.
     *
     * @param arr is the input array
     * @param k   is the maximum sum allowed
     * @return the length of the longest subarray with sum less than or equal to k
     */
    public static int longestSubarrayWithSumLEK(int[] arr, int k) {
       int length = 0 ;
        int currSum = 0 ;
        int maxLength = Integer.MIN_VALUE;
        // inspired by kadane's algorithm 
        for (int i = 0 ; i<arr.length;i++){
            currSum = currSum + arr[i];
             length++;
            if(currSum==k){
                maxLength = Math.max(maxLength, length);
                length = 0 ;
                currSum = 0 ;
            }if(i==arr.length-1){
                currSum=0;
                length=0;
            }
           
        }
        return maxLength ; 
       }
}
