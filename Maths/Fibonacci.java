package Maths;

public class Fibonacci {
    public static void main(String[] args) {
        int n = 8;
        System.out.println(n + "th fibonacci number: " + fibonacci(n));
    }

    /**
     *  Calculate the n-th fibonnaci number
     *  F_n = Fn-2 + Fn-1
     * 
     * @param n the index of the number in the sequence
     * @return the {@code n}th fibonnaci number
     */
    public static long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n-2) + fibonacci(n-1);
    }
}