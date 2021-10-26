package Maths;

public class FactorialRecursion {

  /* Driver Code */
  public static void main(String[] args) {
    assert factorial(0) == 1;
    assert factorial(1) == 1;
    assert factorial(2) == 2;
    assert factorial(3) == 6;
    assert factorial(5) == 120;
  }

  /**
   * Recursive FactorialRecursion Method
   *
   * @param n The number to factorial
   * @return The factorial of the number
   */
  public static long factorial(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("number is negative");
    }
    return n == 0 || n == 1 ? 1 : n * factorial(n - 1);
  }
}
