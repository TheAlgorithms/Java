package Maths;

import java.math.BigInteger;

public class FactorialBigInteger {

    /* Driver Code */
    public static void main(String[] args) {
        System.out.println(factorial(41));
        System.out.println(factorial(45));
    }


    /**
     * Calculate factorial N using iteration
     *
     * @param n the number
     * @return the factorial of {@code n}
     */
    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("number is negative");
        }
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
}
