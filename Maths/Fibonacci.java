package Maths;

public class Fibonacci {
    public static int f[];

    // returns nth Fibonacci Number in log(n) time
    public static int fib(int n) {
        // base cases
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        
        // if already computed, return it
        if (f[n] != 0)
            return f[n];
        
        /*
        | 1 1 |^n = | F(n + 1)  F(n)    |
        | 1 0 |     | F(n)      F(n - 1)|

        If n is even then k = n/2:
        F(n) = [2*F(k-1) + F(k)]*F(k)

        If n is odd then k = (n + 1)/2
        F(n) = F(k)*F(k) + F(k-1)*F(k-1)
        */

        int k = ((n & 1)) == 1 ? (n + 1) / 2 : n / 2;

        f[n] = ((n & 1) == 1) ?
                (fib(k) * fib(k) + fib(k - 1) * fib(k - 1)) 
                : (2 * fib(k - 1) + fib(k)) * fib(k); 

        return f[n];
    }

    public static void main(String args[]) {
        f = new int[1000];

        int n = 8;
        System.out.println("Fibonacci number " + n + " is : " + fib(n));
    }
}