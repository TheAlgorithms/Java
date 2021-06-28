package DynamicProgramming;

/**
 * DynamicProgramming solution for the Egg Dropping Puzzle
 */
public class EggDropping {

    // min trials with n eggs and m floors 

    private static int minTrials(int n, int m) {

        int[][] eggFloor = new int[n + 1][m + 1];
        int result, x;

        for (int i = 1; i <= n; i++) {
            eggFloor[i][0] = 0;   // Zero trial for zero floor.
            eggFloor[i][1] = 1;   // One trial for one floor 
        }

        // j trials for only 1 egg

        for (int j = 1; j <= m; j++)
            eggFloor[1][j] = j;

        // Using bottom-up approach in DP

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                eggFloor[i][j] = Integer.MAX_VALUE;
                for (x = 1; x <= j; x++) {
                    result = 1 + Math.max(eggFloor[i - 1][x - 1], eggFloor[i][j - x]);

                    // choose min of all values for particular x
                    if (result < eggFloor[i][j])
                        eggFloor[i][j] = result;
                }
            }
        }

        return eggFloor[n][m];
    }

    public static void main(String args[]) {
        int n = 2, m = 4;
        // result outputs min no. of trials in worst case for n eggs and m floors
        int result = minTrials(n, m);
        System.out.println(result);
    }
}
