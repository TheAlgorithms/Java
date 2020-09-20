package DynamicProgramming;

public class SubsetSum {

    /*
    This algorithm will determine if a set of integers contains
    a subset that sum to a given integer. The algorithm will
    return true if such a subset exists and false otherwise.
    This is a dynamic programming implementation.
     */

    private static boolean subsetSum(int arr[], int n, int sum){
        boolean isSum[][] = new boolean[n + 2][sum + 1];

        isSum[n + 1][0] = true;
        for (int i = 1; i <= sum; i++) {
            isSum[n + 1][i] = false;
        }

        for (int i = n; i > 0; i--) {
            isSum[i][0] = true;
            for (int j = 1; j <= arr[i - 1] - 1; j++) {
                if (j <= sum) {
                    isSum[i][j] = isSum[i + 1][j];
                }
            }
            for (int j = arr[i - 1]; j <= sum; j++) {
                isSum[i][j] = (isSum[i + 1][j] || isSum[i + 1][j - arr[i - 1]]);
            }
        }

        return isSum[1][sum];
    }

    /*
    This is a driver method to run the algorithm with four
    test values: the first two should evaluate true, and the
    last two should evaluate false.
     */
    public static void main(String args[]) {
        int arr[] = new int[]{50, 4, 10, 15, 34};
        int n = arr.length;
        // 4 + 10 + 15 + 34 = 64
        int sum1 = 64;
        // 50 + 15 + 34 = 99
        int sum2 = 99;
        // No subset of the given array will give a sum
        //  of 5 or 66
        int sum3 = 5;
        int sum4 = 66;

        System.out.println(subsetSum(arr, n, sum1));
        System.out.println(subsetSum(arr, n, sum2));
        System.out.println(subsetSum(arr, n, sum3));
        System.out.println(subsetSum(arr, n, sum4));
    }
}