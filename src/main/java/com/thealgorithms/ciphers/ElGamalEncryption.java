package com.thealgorithms.ciphers;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Implementation of the ElGamal Encryption Algorithm.
 *
 * <p>ElGamal is an asymmetric key encryption algorithm based on
 * the Diffie–Hellman key exchange. It uses randomization
 * for security and is widely used in cryptographic systems.</p>
 *
 * <p>Reference: Menezes, van Oorschot, and Vanstone, "Handbook of Applied Cryptography"</p>
 */
public final class ElGamalEncryption {

    private static final SecureRandom RANDOM = new SecureRandom();

    private ElGamalEncryption() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Runs the ElGamal encryption and decryption demonstration.
     *
     * @param message the plaintext message to encrypt
     * @param bitLength the bit length for prime generation
     */
    @SuppressWarnings({"PMD.SystemPrintln", "PMD.DataflowAnomalyAnalysis"})
    public static void runElGamal(final String message, final int bitLength) {
        final BigInteger p = BigInteger.probablePrime(bitLength, RANDOM);
        final BigInteger g = new BigInteger("2");
        final BigInteger x = new BigInteger(bitLength - 2, RANDOM);
        final BigInteger y = g.modPow(x, p);

        final BigInteger k = new BigInteger(bitLength - 2, RANDOM);
        final BigInteger a = g.modPow(k, p);
        final BigInteger m = new BigInteger(message.getBytes());
        final BigInteger b = (y.modPow(k, p).multiply(m)).mod(p);

        final BigInteger aInverse = a.modPow(p.subtract(BigInteger.ONE).subtract(x), p);
        final BigInteger decrypted = (b.multiply(aInverse)).mod(p);

        System.out.println("Prime (p): " + p);
        System.out.println("Generator (g): " + g);
        System.out.println("Private Key (x): " + x);
        System.out.println("Public Key (y): " + y);
        System.out.println("Ciphertext: (" + a + ", " + b + ")");
        System.out.println("Decrypted Message: " + new String(decrypted.toByteArray()));
    }
}
