package com.thealgorithms.ciphers;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamalCipher {
    private BigInteger p;
    private BigInteger g;
    private BigInteger x;
    private BigInteger y;

    private SecureRandom random = new SecureRandom();

    // Key generation
    public void generateKeys(int bitLength) {
        p = BigInteger.probablePrime(bitLength, random);
        g = new BigInteger(bitLength - 1, random).mod(p);
        x = new BigInteger(bitLength - 2, random); // Private key
        y = g.modPow(x, p); // Public key
    }

    // Encryption: returns [c1, c2]
    public BigInteger[] encrypt(BigInteger message) {
        BigInteger k = new BigInteger(p.bitLength() - 1, random);
        BigInteger c1 = g.modPow(k, p);
        BigInteger s = y.modPow(k, p);
        BigInteger c2 = s.multiply(message).mod(p);
        return new BigInteger[] {c1, c2};
    }

    // Decryption: m = c2 * (c1^x)^-1 mod p
    public BigInteger decrypt(BigInteger c1, BigInteger c2) {
        BigInteger s = c1.modPow(x, p);
        BigInteger sInv = s.modInverse(p);
        return c2.multiply(sInv).mod(p);
    }
