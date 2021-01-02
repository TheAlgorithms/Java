package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/** @author Varun Upadhyay (https://github.com/varunu28) */
public class Fibonacci {

  private static Map<Integer, Integer> map = new HashMap<>();

  public static void main(String[] args) {

    // Methods all returning [0, 1, 1, 2, 3, 5, ...] for n = [0, 1, 2, 3, 4, 5, ...]
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    System.out.println(fibMemo(n));
    System.out.println(fibBotUp(n));
    System.out.println(fibOptimized(n));
    sc.close();
  }

  /**
   * This method finds the nth fibonacci number using memoization technique
   *
   * @param n The input n for which we have to determine the fibonacci number Outputs the nth
   *     fibonacci number
   */
  public static int fibMemo(int n) {
    if (map.containsKey(n)) {
      return map.get(n);
    }

    int f;

    if (n <= 1) {
      f = n;
    } else {
      f = fibMemo(n - 1) + fibMemo(n - 2);
      map.put(n, f);
    }
    return f;
  }

  /**
   * This method finds the nth fibonacci number using bottom up
   *
   * @param n The input n for which we have to determine the fibonacci number Outputs the nth
   *     fibonacci number
   */
  public static int fibBotUp(int n) {

    Map<Integer, Integer> fib = new HashMap<>();

    for (int i = 0; i <= n; i++) {
      int f;
      if (i <= 1) {
        f = i;
      } else {
        f = fib.get(i - 1) + fib.get(i - 2);
      }
      fib.put(i, f);
    }

    return fib.get(n);
  }

  /**
   * This method finds the nth fibonacci number using bottom up
   *
   * @param n The input n for which we have to determine the fibonacci number Outputs the nth
   *     fibonacci number
   *     <p>This is optimized version of Fibonacci Program. Without using Hashmap and recursion. It
   *     saves both memory and time. Space Complexity will be O(1) Time Complexity will be O(n)
   *     <p>Whereas , the above functions will take O(n) Space.
   * @author Shoaib Rayeen (https://github.com/shoaibrayeen)
   */
  public static int fibOptimized(int n) {
    if (n == 0) {
      return 0;
    }
    int prev = 0, res = 1, next;
    for (int i = 2; i <= n; i++) {
      next = prev + res;
      prev = res;
      res = next;
    }
    return res;
  }
  
  /**
   * iterative implementation with memoization
   * bottom up approach
   * array is used to store the values
   *
   * @param n
   * @return
   */
  public int fibBottomUpUsingArray(int n)
  {
    int[] result = new int[n + 1];
    result[0] = 0;
    result[1] = 1;

    for (int i = 2; i < result.length; i++)
    {
      result[i] = result[i - 1] + result[i - 2];
    }

    return result[n];
  }
  
  /**
   * Time Complexity: O(log(n))
   *
   * @param n
   * @return
   */
  public int fibMatrix(int n)
  {
    int[][] F = new int[][]{{1, 1}, {1, 0}};

    if (n == 0) return 0;

    power(F, n - 1);

    return F[0][0];
  }

  private void power(int[][] F, int n)
  {
    if (n == 0 || n == 1) return;

    int[][] M = new int[][]{{1, 1}, {1, 0}};

    power(F, n / 2);
    multiply(F, F);

    if (n % 2 != 0) multiply(F, M);
  }

  private void multiply(int[][] F, int[][] M)
  {
    int x = F[0][0] * M[0][0] + F[0][1] * M[1][0];
    int y = F[0][0] * M[0][1] + F[0][1] * M[1][1];
    int z = F[1][0] * M[0][0] + F[1][1] * M[1][0];
    int w = F[1][0] * M[0][1] + F[1][1] * M[1][1];

    F[0][0] = x;
    F[0][1] = y;
    F[1][0] = z;
    F[1][1] = w;
  }
}
