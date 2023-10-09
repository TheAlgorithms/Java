package com.thealgorithms.maths;

public class PowerUsingBinaryExponentiation {
    /**
     * What ?
     *        To calculate long pow(long base, long exponent, long mod) , where mod is a prime number to avoid long overflow issues.
     *   Why ?
     *        Brute-force approach proposed in /maths/PowerUsingRecursion.java takes Time complexity : O(exponent) time.
     *        Binary Exponentiation algorithm takes Time Complexity : O(log(exponent)) time , Aux-Space Complexity: O(log(exponent))
     *
     *   how?
     *   link: https://en.wikipedia.org/wiki/Exponentiation_by_squaring#Recursive_version
     *
     * @param base , exponent , mod (it's a prime no) e.g., 1e9 +7 (1000000007)
     * @return The ( base^exponent )% mod i.e., power of base raised to exponent whole modulo.
     * @throws IllegalArgumentException If the exponent,mod are negative!
     * @author Razat Aggarwal (https://github.com/razat-thapar)
     */
    public static long pow(int base, int exponent, int mod) throws IllegalArgumentException {
        // assumption : given base, exponent, mod, return base^exponent %mod.
        if (exponent < 0 || mod < 0) {
            throw new IllegalArgumentException("exponent and mod can't be negative!");
        }
        if (exponent == 0) {
            return 1 % mod;
        }
        // store power
        long p = pow(base, exponent / 2, mod);
        if (exponent % 2 == 0) {
            // even exponent
            return (p * p) % mod;
        } else {
            // odd exponent
            if (base < 0) {
                // handle -ve remainder in java due to -ve base.
                return (((p * p) % mod) * (mod + (base % mod))) % mod;
            } else {
                return (((p * p) % mod) * (base % mod)) % mod;
            }
        }
    }
}
