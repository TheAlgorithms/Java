package DynamicProgramming;

import java.util.Scanner;

/**
 * @author Dipyaman Saha (https://github.com/dipyamansaha) - MATRIX MAXIMUM PATH SUM PROBLEM. -
 *     Given a matrix of m * n, we find the maximum path sum in the matrix. We are allowed to move
 *     only down or diagonally to the left or to the right. We can start from any element in the
 *     first row.
 */
public class MatrixMaxPathSum {
  static final int N = 1001;
  static int m, n;
  static int[][] matrix = new int[N][N];
  static int[][] dp = new int[N][N];
  static boolean[][] vis = new boolean[N][N];

  static int go(int i, int j) {
    if (i == (m - 1) && j == (n - 1)) return matrix[i][j];

    if (vis[i][j]) return dp[i][j];

    vis[i][j] = true;

    if ((i < (m - 1)) && (j < (n - 1)))
      dp[i][j] = matrix[i][j] + Math.max(go(i, (j + 1)), go((i + 1), j));
    else if (i == (m - 1)) dp[i][j] = matrix[i][j] + go(i, (j + 1));
    else dp[i][j] = matrix[i][j] + go((i + 1), j);

    return dp[i][j];
  }

  public static void main(String[] args) {
    // Sample output: 4 4  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 -> 73
    Scanner sc = new Scanner(System.in);

    m = sc.nextInt();
    n = sc.nextInt();

    for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) matrix[i][j] = sc.nextInt();

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) System.out.print(matrix[i][j] + "\t");
      System.out.println();
    }

    System.out.println("Sum: " + go(0, 0));
  }
}
