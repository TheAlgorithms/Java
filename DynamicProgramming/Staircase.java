package DynamicProgramming;

/* 
Given a stair with N steps,
implement a method to count how many possible ways
are there to reach the top of the staircase,
given that, at every step you can take either 1 step or 2 steps.
*/
public class Staircase {

  public int climbStairs(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;
    return climbStairsRec(n, dp);
  }
    
  private int climbStairsRec(int n, int[] dp) {
    if(dp[n] != 0) {
        return dp[n];
    }
    dp[n] = climbStairsRec(n - 1, dp) + climbStairsRec(n - 2, dp);
    return dp[n];
  }

  // Driver program to test above function
  public static void main(String args[]) {
    int stairs = 2;
    System.out.println(climbStairs(stairs)); // Output should be 2
    stairs = 5;
    System.out.println(climbStairs(stairs)); // Output should be 8
  }
}
