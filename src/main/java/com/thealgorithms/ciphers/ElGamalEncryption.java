package com.thealgorithms.cryptography;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Implementation of the ElGamal Encryption Algorithm.
 *
 * <p>This algorithm is based on the Diffie–Hellman key exchange and uses modular arithmetic
 * for secure public-key encryption and decryption.
 *
 * <p>Reference:
 * https://www.geeksforgeeks.org/elgamal-encryption-algorithm/
 */
public class ElGamalEncryption {

    private static final SecureRandom random = new SecureRandom();

    /**
     * Encrypts and decrypts a given message using ElGamal encryption.
     *
     * @param message The message to encrypt.
     * @param bitLength The bit length of the prime modulus.
     */
    public static void runElGamal(String message, int bitLength) {
        BigInteger p = BigInteger.probablePrime(bitLength, random); // large prime
        BigInteger g = new BigInteger("2"); // primitive root
        BigInteger x = new BigInteger(bitLength - 2, random); // private key
        BigInteger y = g.modPow(x, p); // public key

        // Encryption
        BigInteger k = new BigInteger(bitLength - 2, random);
        BigInteger a = g.modPow(k, p);
        BigInteger M = new BigInteger(message.getBytes());
        BigInteger b = (y.modPow(k, p).multiply(M)).mod(p);

        // Decryption
        BigInteger aInverse = a.modPow(p.subtract(BigInteger.ONE).subtract(x), p);
        BigInteger decrypted = (b.multiply(aInverse)).mod(p);

        System.out.println("Prime (p): " + p);
        System.out.println("Public Key (y): " + y);
        System.out.println("Ciphertext: (" + a + ", " + b + ")");
        System.out.println("Decrypted Message: " + new String(decrypted.toByteArray()));
    }
}
