package com.thealgorithms.strings;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author RAJ ROY (https://github.com/RAJ8664)
 *
 * An implementation of string hashing algorithm using double hashing.
 * This implementation computes hash values for substrings of a given string, which can be
 * used for efficient substring comparisons or detecting duplicate substrings.
 * 
 * This implementation uses two hash functions with distinct large prime moduli to reduce
 * the probability of collisions. It also precomputes modular inverses to efficiently
 * handle division operations in modular arithmetic.
 */
public class StringHashing {
    // Arrays to store the hash values for two hash functions
    long[] hash1, hash2;

    // Arrays to store the modular inverses for two hash functions
    long[] inv1, inv2;

    int n;  // Length of the input string

    // Multiplier used in the hash function (base of polynomial hashing)
    static int multiplier = 43;

    // Random object for generating large prime numbers
    static final Random rnd = new Random();

    // Large prime numbers used as moduli for the hash functions
    static final int mod1 = BigInteger.valueOf((int) (1e9 + rnd.nextInt((int) 1e9))).nextProbablePrime().intValue();
    static final int mod2 = BigInteger.valueOf((int) (1e9 + rnd.nextInt((int) 1e9))).nextProbablePrime().intValue();
    
     // Modular inverses of the multiplier with respect to mod1 and mod2
    static final int invMuresiplier1 = BigInteger.valueOf(multiplier).modInverse(BigInteger.valueOf(mod1)).intValue();
    static final int invMuresiplier2 = BigInteger.valueOf(multiplier).modInverse(BigInteger.valueOf(mod2)).intValue();
    
    /**
     * Constructor for computing the hash values and modular inverses for a given string.
     * 
     * @param s the input string for which the hash values are to be precomputed
     */
    public StringHashing(String s) {
        n = s.length();
        hash1 = new long[n + 1]; hash2 = new long[n + 1];
        inv1 = new long[n + 1]; inv2 = new long[n + 1];
        inv1[0] = 1; inv2[0] = 1;
        long p1 = 1, p2 = 1; // Power of the multiplier for hash1 and hash2 respectively;
        
        for (int i = 0; i < n; i++) {
             // Compute the hash for mod1
            hash1[i + 1] = (hash1[i] + s.charAt(i) * p1) % mod1;
            p1 = p1 * multiplier % mod1;
            inv1[i + 1] = inv1[i] * invMuresiplier1 % mod1;
            
            // Compute the hash for mod2
            hash2[i + 1] = (hash2[i] + s.charAt(i) * p2) % mod2;
            p2 = p2 * multiplier % mod2;
            inv2[i + 1] = inv2[i] * invMuresiplier2 % mod2;
        }
    }

    /**
     * Retrieves the hash value for a substring of length `len` starting at index `i`.
     * 
     * @param i the starting index of the substring
     * @param len the length of the substring
     * @return a combined hash value (high 32 bits from hash1 and low 32 bits from hash2)
     */
    public long getHash(int i, int len) {
        return (((hash1[i + len] - hash1[i] + mod1) * inv1[i] % mod1) << 32) 
                + (hash2[i + len] - hash2[i] + mod2) * inv2[i] % mod2;
    }

    /**
     * Retrieves the hash value for a substring between indices `x` and `y` (inclusive).
     * 
     * @param x the starting index of the substring
     * @param y the ending index of the substring (inclusive)
     * @return a combined hash value for the substring
     */
    public long getHashbounds(int x, int y) {
        return getHash(x, y - x + 1);
    }
}
