package com.thealgorithms.ciphers;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * ElGamal Encryption Algorithm Implementation.
 *
 * <p>
 * ElGamal is an asymmetric key encryption algorithm for public-key cryptography
 * based on the Diffie–Hellman key exchange. It relies on the difficulty
 * of computing discrete logarithms in a cyclic group.
 * </p>
 *
 * <p>
 * <strong>Key Features:</strong>
 * <ul>
 * <li>Uses Safe Primes (p = 2q + 1) to ensure group security.</li>
 * <li>Verifies the generator is a primitive root modulo p.</li>
 * <li>Stateless design using Java Records.</li>
 * <li>SecureRandom for all cryptographic operations.</li>
 * </ul>
 * </p>
 *
 * @author Chahat Sandhu, <a href="https://github.com/singhc7">singhc7</a>
 * @see <a href="https://en.wikipedia.org/wiki/ElGamal_encryption">ElGamal Encryption (Wikipedia)</a>
 * @see <a href="https://en.wikipedia.org/wiki/Safe_and_Sophie_Germain_primes">Safe Primes</a>
 */
public final class ElGamalCipher {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int PRIME_CERTAINTY = 40;
    private static final int MIN_BIT_LENGTH = 256;

    private ElGamalCipher() {
    }

    /**
     * A container for the Public and Private keys.
     *
     * @param p The prime modulus.
     * @param g The generator (primitive root).
     * @param y The public key component (g^x mod p).
     * @param x The private key.
     */
    public record KeyPair(BigInteger p, BigInteger g, BigInteger y, BigInteger x) {
    }

    /**
     * Container for the encryption result.
     *
     * @param a The first component (g^k mod p).
     * @param b The second component (y^k * m mod p).
     */
    public record CipherText(BigInteger a, BigInteger b) {
    }

    /**
     * Generates a valid ElGamal KeyPair using a Safe Prime.
     *
     * @param bitLength The bit length of the prime modulus p. Must be at least 256.
     * @return A valid KeyPair (p, g, y, x).
     * @throws IllegalArgumentException if bitLength is too small.
     */
    public static KeyPair generateKeys(int bitLength) {
        if (bitLength < MIN_BIT_LENGTH) {
            throw new IllegalArgumentException("Bit length must be at least " + MIN_BIT_LENGTH + " for security.");
        }

        BigInteger p;
        BigInteger q;
        BigInteger g;
        BigInteger x;
        BigInteger y;

        // Generate Safe Prime p = 2q + 1
        do {
            q = new BigInteger(bitLength - 1, PRIME_CERTAINTY, RANDOM);
            p = q.multiply(BigInteger.TWO).add(BigInteger.ONE);
        } while (!p.isProbablePrime(PRIME_CERTAINTY));

        // Find a Generator g (Primitive Root modulo p)
        do {
            g = new BigInteger(bitLength, RANDOM).mod(p.subtract(BigInteger.TWO)).add(BigInteger.TWO);
        } while (!isValidGenerator(g, p, q));

        // Generate Private Key x in range [2, p-2]
        do {
            x = new BigInteger(bitLength, RANDOM);
        } while (x.compareTo(BigInteger.TWO) < 0 || x.compareTo(p.subtract(BigInteger.TWO)) > 0);

        // Compute Public Key y = g^x mod p
        y = g.modPow(x, p);

        return new KeyPair(p, g, y, x);
    }

    /**
     * Encrypts a message using the public key.
     *
     * @param message The message converted to BigInteger.
     * @param p       The prime modulus.
     * @param g       The generator.
     * @param y       The public key component.
     * @return The CipherText pair (a, b).
     * @throws IllegalArgumentException if inputs are null, negative, or message >= p.
     */
    public static CipherText encrypt(BigInteger message, BigInteger p, BigInteger g, BigInteger y) {
        if (message == null || p == null || g == null || y == null) {
            throw new IllegalArgumentException("Inputs cannot be null.");
        }
        if (message.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Message must be non-negative.");
        }
        if (message.compareTo(p) >= 0) {
            throw new IllegalArgumentException("Message must be smaller than the prime modulus p.");
        }

        BigInteger k;
        BigInteger pMinus1 = p.subtract(BigInteger.ONE);

        // Select ephemeral key k such that 1 < k < p-1 and gcd(k, p-1) = 1
        do {
            k = new BigInteger(p.bitLength(), RANDOM);
        } while (k.compareTo(BigInteger.ONE) <= 0 || k.compareTo(pMinus1) >= 0 || !k.gcd(pMinus1).equals(BigInteger.ONE));

        BigInteger a = g.modPow(k, p);
        BigInteger b = y.modPow(k, p).multiply(message).mod(p);

        return new CipherText(a, b);
    }

    /**
     * Decrypts a ciphertext using the private key.
     *
     * @param cipher The CipherText (a, b).
     * @param x      The private key.
     * @param p      The prime modulus.
     * @return The decrypted message as BigInteger.
     * @throws IllegalArgumentException if inputs are null.
     */
    public static BigInteger decrypt(CipherText cipher, BigInteger x, BigInteger p) {
        if (cipher == null || x == null || p == null) {
            throw new IllegalArgumentException("Inputs cannot be null.");
        }

        BigInteger a = cipher.a();
        BigInteger b = cipher.b();

        BigInteger s = a.modPow(x, p);
        BigInteger sInverse = s.modInverse(p);

        return b.multiply(sInverse).mod(p);
    }

    /**
     * Verifies if g is a valid generator for safe prime p = 2q + 1.
     *
     * @param g The candidate generator.
     * @param p The safe prime.
     * @param q The Sophie Germain prime (p-1)/2.
     * @return True if g is a primitive root, False otherwise.
     */
    private static boolean isValidGenerator(BigInteger g, BigInteger p, BigInteger q) {
        // Fix: Must use braces {} for all if statements
        if (g.equals(BigInteger.ONE)) {
            return false;
        }
        if (g.modPow(BigInteger.TWO, p).equals(BigInteger.ONE)) {
            return false;
        }
        return !g.modPow(q, p).equals(BigInteger.ONE);
    }
}
