/*
Problem: In a N x N grid representing a field of cherries, each cell is one of three possible integers.
    0 means the cell is empty, so you can pass through;
    1 means the cell contains a cherry, that you can pick up and pass through;
    -1 means the cell contains a thorn that blocks your way.

Your task is to collect maximum number of cherries possible by following the rules below:
    Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
    After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
    When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
    If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.

Example 1:  Input: grid =   [[0, 1, -1],
                            [1, 0, -1],
                            [1, 1,  1]]
            Output: 5

Time Complexity: O(N^3)
Space Complexity: O(N^2)

@author Kundan Kumar
*/

import java.util.Scanner;
import java.util.Arrays;

public class CherryPickup
{
    //Bottom Up Approach to find the max cherry
    public static int cherryPickup(int[][] grid) 
    {
        int N = grid.length;
        int[][] dp = new int[N][N];
        for (int[] row: dp) Arrays.fill(row, Integer.MIN_VALUE);
        dp[0][0] = grid[0][0];

        for (int t = 1; t <= 2*N - 2; ++t) 
        {
            int[][] dp2 = new int[N][N];
            for (int[] row: dp2) Arrays.fill(row, Integer.MIN_VALUE);

            for (int i = Math.max(0, t-(N-1)); i <= Math.min(N-1, t); ++i) 
            {
                for (int j = Math.max(0, t-(N-1)); j <= Math.min(N-1, t); ++j) 
                {
                    if (grid[i][t-i] == -1 || grid[j][t-j] == -1) continue;
                    int val = grid[i][t-i];
                    if (i != j) val += grid[j][t-j];
                    for (int pi = i-1; pi <= i; ++pi)
                        for (int pj = j-1; pj <= j; ++pj)
                            if (pi >= 0 && pj >= 0)
                                dp2[i][j] = Math.max(dp2[i][j], dp[pi][pj] + val);
                }
            }
            dp = dp2;
        }
        return Math.max(0, dp[N-1][N-1]);
    }

    //Main Function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Size of Array:");
        int n = sc.nextInt();
        System.out.println("Enter Elements of Array:");
        int[][] arr=new int[n][n];
        for (int i = 0; i < n; i++) 
        {
            for(int j=0; j<n; j++)
            {
                arr[i][j] = sc.nextInt();
            }
        }
        int maxcherry = cherryPickup(arr);
        System.out.println("Maximum Cherry will be: "+maxcherry);
        sc.close();
    }
}