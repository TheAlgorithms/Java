package com.thealgorithms.others;

/**
 * You can read more about Euler's totient function
 *
 * <p>
 * See https://en.wikipedia.org/wiki/Euler%27s_totient_function
 */
public class EulersFunction {
    // This method returns us number of x that (x < n) and gcd(x, n) == 1 in O(sqrt(n)) time
    // complexity;

    public static int getEuler(int n) {
        int result = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                }
                result -= result / i;
            }
        }
        if (n > 1) {
            result -= result / n;
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            System.out.println(getEuler(i));
        }
    }
}
