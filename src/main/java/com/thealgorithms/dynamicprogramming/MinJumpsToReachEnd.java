
package com.thealgorithms.dynamicprogramming;

/**
 * Given an array arr[] where each element represents the max number of steps that can be made
 * forward from that index. The task is to find the minimum number of jumps to reach the end of
 * the array starting from index 0. If the end isn’t reachable, return -1.
 * 
 * Example:
 * 
 * Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
 * Output: 3 (1-> 3 -> 9 -> 9)
 * 
 * Explanation:
 * Jump from 1st element to 2nd element as there is only 1 step.
 * Now there are three options 5, 8 or 9. I
 * f 8 or 9 is chosen then the end node 9 can be reached. So 3 jumps are made.
 * 
 * 
 */

class MinJumpsToReaachEnd {

    static int minJumps(int arr[], int n) 
      { 
          
          int dp[]= new int[n]; 
          int i, j; 
        
          
        
          dp[0] = 0; 
        
          
          for (i = 1; i < n; i++) { 
              dp[i] = Integer.MAX_VALUE; 
              for (j = 0; j < i; j++) { 
                  if (i <= j + arr[j] && dp[j] != Integer.MAX_VALUE) { 
                      dp[i] = Math.min(dp[i], dp[j] + 1); 
                      break; 
                  } 
              } 
          } 
          return dp[n - 1]; 
      }
              
      public static void main (String[] args) 
      {
          int arr[] = {3, 4, 2, 1, 2, 1}, n =6;
          
          
          System.out.println(minJumps(arr, n));
  
          
      }
  }