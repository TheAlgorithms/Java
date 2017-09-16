package misc;

/**
 * This program will print out the factorial of any non-negative
 * number that you input into it.
 *
 * @author Marcus
 */
public class Factorial {

    /**
     * Recursive Factorial Method
     *
     * @param n The number to factorial
     * @return The factorial of the number
     */
    public static long factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }
}
