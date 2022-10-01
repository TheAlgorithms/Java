package com.thealgorithms.maths;

import java.math.BigInteger;

public class CullenNumbers {

    /**
     * Calculate the nth Cullen number(1, 3, 9, 25, 65, 161, 385, 897, 2049, 4609...) where n is a positive integer
     * @param n nth
     * @return nth Cullen number
     */
    public static BigInteger cullenNumber(int n) throws IllegalArgumentException {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be a positive integer");
        }
        else {
            BigInteger N = BigInteger.valueOf(n);
            return N.subtract(BigInteger.ONE).multiply(BigInteger.valueOf(2).pow(n-1)).add(BigInteger.ONE);
        }
    }
}
