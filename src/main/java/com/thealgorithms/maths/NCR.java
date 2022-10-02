package com.thealgorithms.maths;

/**
 * This is NCR method which is used to find the combination value
 * of two integer n and r
 *
 * @author ahmeh360 02/10/22
 */

pubilc class NCR {

    /**
     * get combination value
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return combination value
     */

    static void printNcR(int n, int r)
    {

        long p = 1, k = 1;

        if (n - r < r) {
            r = n - r;
        }

        if (r != 0) {
            while (r > 0) {
                p *= n;
                k *= r;

                long m = __gcd(p, k);
                p /= m;
                k /= m;

                n--;
                r--;
            }
        }
        else {
            p = 1;
        }
        System.out.println(p);
    }

    /**
     * get greatest common divisor value
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return gcd value
     */

    static long __gcd(long n1, long n2)
    {
        long gcd = 1;

        for (int i = 1; i <= n1 && i <= n2; ++i) {
            if (n1 % i == 0 && n2 % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    public static void main(String[] args)
    {
        int n = 50, r = 25;

        printNcR(n, r);
    }
}

