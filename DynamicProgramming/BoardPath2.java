package DynamicProgramming;

/* This is important algo which helps to find maximum or minimum way that you should go through cells
 * and eventually you will gather maximum value of cells in board.
 *  @Author Sabirov Jakhongir
 *
 */

import java.util.Scanner;

public class BoardPath2 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int m = cin.nextInt();
        int a[] [] = new int[n + 1][m + 1];
        for(int i = 1 ; i <= n ; i ++){
            for(int j = 1 ; j <= m ;j ++){
                a[i][j] = cin.nextInt();
            }
        }
        // In this case always we should start path from i = 1, j = 1

        // we well find out max value of first row
        for(int i = 1 ; i <= m ;i ++)
            a[1][i] += a[1][i - 1];

        //we well find out min value of first column
        for(int i = 1 ; i <= n ;i ++)
            a[i][1] += a[i - 1] [1];


        for(int i = 2; i <= n; i++){
            for(int j = 2 ; j <= m;j ++){
                a[i][j] = Math.max(a[i-1][j],a[i][j-1]) + a[i][j];
            }
        }

        // The max value of always will be in last cell of matrix
        System.out.println(a[n][m]);

    }
}
