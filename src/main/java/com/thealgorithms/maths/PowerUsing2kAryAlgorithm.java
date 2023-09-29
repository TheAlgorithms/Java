package com.thealgorithms.maths;

public class PowerUsing2kAryAlgorithm {

    /**
     * What ?
     *        To calculate long pow(long base, long exponent, long mod) , where mod is a prime number to avoid long overflow issues.
     *   Why ?
     *        Brute-force approach proposed in /maths/PowerUsingRecursion.java takes Time complexity : O(exponent) time.
     *        Binary Exponentiation algorithm takes Time Complexity : O(log(exponent)) time , Aux-Space Complexity: O(log(exponent))
     *        2k-ary method Algorithm takes Time Complexity : O(log(exponent)) time , Aux-Space Complexity: O(1)
     *   how?
     *   link: https://en.wikipedia.org/wiki/Exponentiation_by_squaring#2k-ary_method
     *
     * @param base , exponent , mod (it's a prime no) e.g., 1e9 +7 (1000000007)
     * @return The ( base^exponent )% mod i.e., power of base raised to exponent whole modulo.
     * @throws IllegalArgumentException If the exponent,mod are negative!
     * @author Razat Aggarwal (https://github.com/razat-thapar)
     */

    public static long pow(long base, long exp, long mod) throws IllegalArgumentException {
        long t = 1L;
        if (exp < 0 || mod < 0) {
            throw new IllegalArgumentException("exponent and mod can't be negative!");
        }
        while (exp > 0) {
            // for cases where exponent
            // is not an even value
            if (exp % 2 != 0) t = (t * base) % mod;

            base = (base * base) % mod;
            exp /= 2;
        }
        return t % mod;
    }
}
