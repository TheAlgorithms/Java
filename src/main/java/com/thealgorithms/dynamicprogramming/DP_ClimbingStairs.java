/* Question:
Minimum no of steps required to reach the top, given the maximum no of jumps it can take at every stair. */

import java.util.*;
class ClimbStairs_DP
{  
  public static void main(String args[])
  {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the no. of jumps: ");
    int n = sc.nextInt();
    int arr[] = new int[n];
    System.out.println("Enter the maximum jumps: ");
    for(int i = 0; i < n; i++)
      arr[i] = sc.nextInt();
    int move = minMoves(arr, n);
    System.out.println("Minimum Jumps required to climb the stairs is " + move);    
  }
  public static int minMoves(int arr[], int n)
  {
    int i;
    int dp[] = new int[n];
    
    for(i = 0; i < n; i++)
      dp[i] = Integer.MAX_VALUE;
    
    dp[n-1] = 0;
    
    for(i = n-2; i >= 0; i--)
    {
      if(arr[i] != 0)
      {
        int m = Integer.MAX_VALUE;
        int j = arr[i];
        for(j = 1; j < arr[i]+1; j++)
        {
          if(i+j < n && dp[i+j] != Integer.MAX_VALUE)
          {
            m = Math.min(m, dp[i+j]);
            if(dp[i+j] < m)
              m = dp[i+j];
          }
        }
        if(m != Integer.MAX_VALUE)
          dp[i] = 1 + m;
      }
    }
    if(dp[0] != Integer.MAX_VALUE)
      return dp[0];
    else
      return -1;
  }
}
