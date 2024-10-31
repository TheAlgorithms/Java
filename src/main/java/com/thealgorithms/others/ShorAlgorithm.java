package com.thealgorithms.others;

import java.math.BigInteger;
import java.util.Random;

// The algorithm is referred from
// https://www.geeksforgeeks.org/shors-factorization-algorithm/
public class ShorAlgorithm {
    // trying to find the order of exponent given the base and the number
    private int exponent(BigInteger base, BigInteger number) {
        BigInteger result = BigInteger.ONE;
        int increment = 0;
        while (!result.equals(BigInteger.ONE) || increment == 0) {
            result = result.multiply(base).mod(number);
            increment++;
        }
        return increment;
    }

    // implementing the shor algorithm
    public BigInteger[] shorAlgorithm(BigInteger number) {
        if (number.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
            BigInteger p = number.divide(new BigInteger("2"));
            BigInteger q = new BigInteger("2");
            return new BigInteger[] {p, q};
        }

        Random random = new Random();
        BigInteger base = BigInteger.ZERO;
        do {
            base = new BigInteger(number.bitLength(), random);
        } while (base.compareTo(BigInteger.ZERO) <= 0 || base.compareTo(number) >= 0);

        BigInteger hcf = base.gcd(number);
        if (hcf.compareTo(BigInteger.ONE) > 0) {
            return new BigInteger[] {hcf, number.divide(hcf)};
        }

        int result = exponent(base, number);
        if (result % 2 != 0) {
            return null;
        }

        BigInteger congruentResult = base.modPow(BigInteger.valueOf(result / 2), number);
        if (congruentResult.equals(number.subtract(BigInteger.ONE))) {
            return null;
        }

        BigInteger p = congruentResult.add(BigInteger.ONE).gcd(number);
        BigInteger q = congruentResult.subtract(BigInteger.ONE).gcd(number);

        if (!p.equals(BigInteger.ONE) && !q.equals(BigInteger.ONE)) {
            return new BigInteger[] {p, q};
        }
        return null;
    }
}
