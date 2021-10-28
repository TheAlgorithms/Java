package DynamicProgramming;

public class SubsetSum {

  /** Driver Code */
  public static void main(String[] args) {
    int[] arr = new int[] {50, 4, 10, 15, 34};
    assert subsetSum(arr, 64); /* 4 + 10 + 15 + 34 = 64 */
    assert subsetSum(arr, 99); /* 50 + 15 + 34 = 99 */
    assert !subsetSum(arr, 5);
    assert !subsetSum(arr, 66);
  }

  /**
   * Test if a set of integers contains a subset that sum to a given integer.
   *
   * @param arr the array contains integers.
   * @param sum target sum of subset.
   * @return {@code true} if subset exists, otherwise {@code false}.
   */
  private static boolean subsetSum(int[] arr, int sum) {
    int n = arr.length;
    boolean[][] isSum = new boolean[n + 2][sum + 1];

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
}
