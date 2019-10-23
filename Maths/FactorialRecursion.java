package Maths;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FactorialRecursion {
    public static void main(String[] args) {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      System.out.println(n + "! = " + factorialrecursion(n));

    }

    /**
     * Calculate factorial
     *
     * @param n the number
     * @return the factorial of {@code n}
     */

    public static long factorialrecursion(int n) {
        if (n < 0) {
            throw new ArithmeticException("n < 0");
        }
        if(n==1){
          return 1;
        }

        else
        {
          return n*fact(n-1);
        }
    }
}
