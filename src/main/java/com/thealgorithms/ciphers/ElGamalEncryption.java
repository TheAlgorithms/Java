/*
 * TheAlgorithms (https://github.com/TheAlgorithms/Java)
 * Author: Shewale41
 * This file is licensed under the MIT License.
 */

package com.thealgorithms.ciphers;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Implementation of the ElGamal Encryption Algorithm.
 *
 * <p>This algorithm is based on the Diffie–Hellman key exchange and provides secure
 * public-key encryption and decryption using modular arithmetic.
 *
 * <p>Reference:
 * https://en.wikipedia.org/wiki/ElGamal_encryption
 */
public final class ElGamalEncryption {

    private static final SecureRandom RANDOM = new SecureRandom();

    /** Private constructor to prevent instantiation of utility class. */
    private ElGamalEncryption() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Demonstrates ElGamal encryption and decryption for a given message.
     *
     * @param message the message to encrypt
     * @param bitLength the bit length for the prime number used
     */
    public static void runElGamal(String message, int bitLength) {
        BigInteger p = BigInteger.probablePrime(bitLength, RANDOM); // prime modulus
        BigInteger g = new BigInteger("2"); // primitive root
        BigInteger x = new BigInteger(bitLength - 2, RANDOM); // private key
        BigInteger y = g.modPow(x, p); // public key

        // Encryption
        BigInteger k = new BigInteger(bitLength - 2, RANDOM);
        BigInteger a = g.modPow(k, p);
        BigInteger m = new BigInteger(message.getBytes());
        BigInteger b = (y.modPow(k, p).multiply(m)).mod(p);

        // Decryption
        BigInteger aInverse = a.modPow(p.subtract(BigInteger.ONE).subtract(x), p);
        BigInteger decrypted = (b.multiply(aInverse)).mod(p);

        System.out.println("Prime (p): " + p);
        System.out.println("Public Key (y): " + y);
        System.out.println("Ciphertext: (" + a + ", " + b + ")");
        System.out.println("Decrypted Message: " + new String(decrypted.toByteArray()));
    }
}
