package Maths;
import java.math.BigInteger;

public class Factorial {

    /* Driver Code */
    public static void main(String[] args) {
        assert factorial(0).toString() == "1";
        assert factorial(1).toString() == "1";
        assert factorial(5).toString() == "120";
        assert factorial(10).toString() == "3628800";
    }

    /**
     * Calculate factorial N using iteration
     *
     * @param n the number
     * @return the factorial of {@code n} as a BigInteger
     */
    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("number is negative");
        }
        BigInteger factorial = BigInteger.valueOf(1);
        for (int i = 1; i <= n; i++)
            factorial = factorial.multiply(BigInteger.valueOf(i));
        return factorial;
    }
}
