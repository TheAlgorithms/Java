/**
 * Implementation of the Minimum Falling Path Sum problem using Dynamic
 * Programming.
 *
 * Given an n x n integer matrix, the algorithm finds the minimum sum of any
 * falling path through the matrix. A falling path starts at any element in the
 * first row and chooses one element from each row below, such that the next
 * element is in the same column or an adjacent column.
 *
 * <p>Example:
 * Input:
 * 3
 * 2 1 3
 * 6 5 4
 * 7 8 9
 *
 * Output:
 * 13
 *
 * <p>Approach: Bottom-up Dynamic Programming
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2)
 *
 * @see <a
 *     href="https://leetcode.com/problems/minimum-falling-path-sum/">LeetCode:
 *     Minimum Falling Path Sum</a>
 * @see <a href="https://en.wikipedia.org/wiki/Dynamic_programming">Wikipedia:
 *     Dynamic Programming</a>
 */

package dp;

import java.util.*;

public class MinFallingPathSum {

  public static int minFallingPathSum(int[][] matrix) {
    int n = matrix.length;
    int[][] dp = new int[n][n];
    for (int i = 0; i < n; i++)
      dp[n - 1][i] = matrix[n - 1][i];
    for (int i = n - 2; i >= 0; i--) {
      for (int j = 0; j < n; j++) {
        int downLeft =
            (i + 1 < n && j - 1 >= 0) ? dp[i + 1][j - 1] : Integer.MAX_VALUE;
        int downRight =
            (i + 1 < n && j + 1 < n) ? dp[i + 1][j + 1] : Integer.MAX_VALUE;
        int down = (i + 1 < n) ? dp[i + 1][j] : Integer.MAX_VALUE;
        dp[i][j] = matrix[i][j] + Math.min(down, Math.min(downLeft, downRight));
      }
    }
    int mini = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++)
      mini = Math.min(mini, dp[0][i]);
    return mini;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[][] matrix = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        matrix[i][j] = sc.nextInt();
    System.out.println(minFallingPathSum(matrix));
  }
}
