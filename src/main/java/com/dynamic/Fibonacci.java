package src.main.java.com.dynamic;

/**
 * The Fibonacci sequence is a sequence of numbers in which each number after the first two is the sum of the two preceding ones.
 * 1, 1, 2, 3, 5, 8, 13, ...
 */
public class Fibonacci {

    /**
     * A recursive method that calculates and returns the nth fibonacci number.
     *
     * @return the nth fibonacci number.
     */
    public int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

}
