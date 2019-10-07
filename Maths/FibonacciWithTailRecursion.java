package Maths;

public class FibonacciWithTailRecursion {
    public static void main(String[] args) {
        int n = 8;
        System.out.println(n + "th fibonacci number: " + fibonacci(n));
    }

    /**
     *  Calculate the n-th fibonnaci number with tail recursion
     *  F_n = Fn-2 + Fn-1
     * 
     * @param n the index of the number in the sequence
     * @return the {@code n}th fibonnaci number
     */
    public static long fibonacci(int n) {
        return fibonacci(n, 0, 1);
    }

    private static long fibonacci(int n, int a, int b) {
        if (n == 0) return a;
        if (n == 1) return b;
        return fibonacci(n-1, b, a+b);
    }
}