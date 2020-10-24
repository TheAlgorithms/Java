package DynamicProgramming;

/**
 * A DynamicProgramming solution for Rod cutting problem Returns the best obtainable price for a rod
 * of length n and price[] as prices of different pieces
 */
public class RodCutting {

  private static int cutRod(int[] price, int n) {
    int val[] = new int[n + 1];
    val[0] = 0;

    for (int i = 1; i <= n; i++) {
      int max_val = Integer.MIN_VALUE;
      for (int j = 0; j < i; j++) max_val = Math.max(max_val, price[j] + val[i - j - 1]);

      val[i] = max_val;
    }

    return val[n];
  }

  // main function to test
  public static void main(String args[]) {
    int[] arr = new int[] {2, 5, 13, 19, 20};
    int size = arr.length;
    int result = cutRod(arr, size);
    System.out.println("Maximum Obtainable Value is " + result);
  }
}
