import java.io.*;
import java.util.*;
/*Given a triangular arraylist of arraylists, you have to find the minimum path sum from top to bottom.In every step you can move to adjacent numbers on the row just below current row.
Sample Input - 
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
Sample Output-
11
Explanation - 
 2 + 3 + 5 + 1 = 11*/
public class MinPathSum {
    public static int min(int[][] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        for(int i=0;i<arr[n-1].length;i++){
            dp[i]=arr[n-1][i];
        }
        for(int i=n-2;i>=0;i--){
            for(int j=0;j<arr[i].length;j++){
                dp[j]=arr[i][j]+(int)(Math.min(dp[j],dp[j+1]));
            }
        }
        return dp[0];
    }
    public static void main(String args[]){
       int[][] arr = {{2},
                      {3,4},
                     {6,5,7},
                     {4,1,8,3}};
       System.out.println(min(arr));
    }
}