package com.thealgorithms.ciphers;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * ElGamal Encryption Algorithm Implementation
 *
 * ElGamal is an asymmetric key encryption algorithm for public-key cryptography
 * based on the Discrete Logarithm Problem (DLP). It provides semantic security
 * through randomization in the encryption process.
 *
 * Key Components:
 * - p: Large prime number (modulus)
 * - g: Generator of the multiplicative group modulo p
 * - x: Private key (random integer)
 * - y: Public key where y = g^x mod p
 *
 * Encryption: For message m, choose random k and compute:
 *   c1 = g^k mod p
 *   c2 = m * y^k mod p
 *   Ciphertext = (c1, c2)
 *
 * Decryption: Recover m using:
 *   m = c2 * (c1^x)^-1 mod p
 *   where (c1^x)^-1 is the modular multiplicative inverse
 *
 * Wikipedia: https://en.wikipedia.org/wiki/ElGamal_encryption
 *
 * @author TheAlgorithms
 */
public final class ElGamalCipher {

    private ElGamalCipher() {
        // Utility class - prevent instantiation
    }

    /**
     * Represents an ElGamal key pair containing public and private keys
     */
    public static class KeyPair {
        private final BigInteger p;
        private final BigInteger g;
        private final BigInteger x;
        private final BigInteger y;

        public KeyPair(BigInteger p, BigInteger g, BigInteger x, BigInteger y) {
            this.p = p;
            this.g = g;
            this.x = x;
            this.y = y;
        }

        public BigInteger getP() {
            return p;
        }

        public BigInteger getG() {
            return g;
        }

        public BigInteger getPrivateKey() {
            return x;
        }

        public BigInteger getPublicKey() {
            return y;
        }
    }

    /**
     * Represents an ElGamal ciphertext as a pair (c1, c2)
     */
    public static class Ciphertext {
        private final BigInteger c1;
        private final BigInteger c2;

        public Ciphertext(BigInteger c1, BigInteger c2) {
            this.c1 = c1;
            this.c2 = c2;
        }

        public BigInteger getC1() {
            return c1;
        }

        public BigInteger getC2() {
            return c2;
        }

        @Override
        public String toString() {
            return "Ciphertext{c1=" + c1 + ", c2=" + c2 + "}";
        }
    }

    /**
     * Generates ElGamal key pair with specified bit length
     *
     * Steps:
     * 1. Generate a large prime p
     * 2. Find a generator g of the multiplicative group mod p
     * 3. Choose random private key x in range [2, p-2]
     * 4. Compute public key y = g^x mod p
     *
     * @param bitLength The bit length for the prime (e.g., 512, 1024, 2048)
     * @return KeyPair containing (p, g, x, y)
     */
    public static KeyPair generateKeys(int bitLength) {
        SecureRandom random = new SecureRandom();

        BigInteger p = BigInteger.probablePrime(bitLength, random);
        BigInteger g = findGenerator(p);

        BigInteger x;
        do {
            x = new BigInteger(bitLength - 1, random);
        } while (x.compareTo(BigInteger.TWO) < 0 || x.compareTo(p.subtract(BigInteger.TWO)) > 0);

        BigInteger y = g.modPow(x, p);

        return new KeyPair(p, g, x, y);
    }

    /**
     * Finds a generator for the multiplicative group modulo p
     *
     * @param p The prime modulus
     * @return A generator g
     */
    private static BigInteger findGenerator(BigInteger p) {
        BigInteger g = BigInteger.valueOf(2);

        while (g.compareTo(p) < 0) {
            if (g.modPow(p.subtract(BigInteger.ONE), p).equals(BigInteger.ONE)) {
                return g;
            }
            g = g.add(BigInteger.ONE);
        }

        return BigInteger.valueOf(2);
    }

    /**
     * Encrypts a message using ElGamal encryption
     *
     * Process:
     * 1. Choose random k in range [2, p-2]
     * 2. Compute c1 = g^k mod p
     * 3. Compute c2 = m * y^k mod p
     * 4. Return ciphertext (c1, c2)
     *
     * @param message The plaintext message as BigInteger (must be < p)
     * @param keyPair The key pair containing public parameters
     * @return Ciphertext (c1, c2)
     */
    public static Ciphertext encrypt(BigInteger message, KeyPair keyPair) {
        if (message.compareTo(keyPair.getP()) >= 0) {
            throw new IllegalArgumentException("Message must be less than modulus p");
        }

        SecureRandom random = new SecureRandom();
        BigInteger p = keyPair.getP();
        BigInteger g = keyPair.getG();
        BigInteger y = keyPair.getPublicKey();

        BigInteger k;
        do {
            k = new BigInteger(p.bitLength() - 1, random);
        } while (k.compareTo(BigInteger.TWO) < 0 || k.compareTo(p.subtract(BigInteger.TWO)) > 0);

        BigInteger c1 = g.modPow(k, p);
        BigInteger c2 = message.multiply(y.modPow(k, p)).mod(p);

        return new Ciphertext(c1, c2);
    }

    /**
     * Decrypts a ciphertext using ElGamal decryption
     *
     * Process:
     * 1. Compute s = c1^x mod p (shared secret)
     * 2. Compute s^-1 (modular multiplicative inverse of s)
     * 3. Recover m = c2 * s^-1 mod p
     *
     * @param ciphertext The ciphertext (c1, c2)
     * @param keyPair The key pair containing private key
     * @return Decrypted plaintext message
     */
    public static BigInteger decrypt(Ciphertext ciphertext, KeyPair keyPair) {
        BigInteger c1 = ciphertext.getC1();
        BigInteger c2 = ciphertext.getC2();
        BigInteger x = keyPair.getPrivateKey();
        BigInteger p = keyPair.getP();

        BigInteger s = c1.modPow(x, p);
        BigInteger sInverse = s.modInverse(p);
        BigInteger message = c2.multiply(sInverse).mod(p);

        return message;
    }

    /**
     * Converts a string to BigInteger for encryption
     *
     * @param text The input string
     * @return BigInteger representation
     */
    public static BigInteger stringToBigInteger(String text) {
        byte[] bytes = text.getBytes();
        return new BigInteger(1, bytes);
    }

    /**
     * Converts BigInteger back to string after decryption
     *
     * @param number The BigInteger to convert
     * @return Original string
     */
    public static String bigIntegerToString(BigInteger number) {
        byte[] bytes = number.toByteArray();
        if (bytes[0] == 0 && bytes.length > 1) {
            byte[] tmp = new byte[bytes.length - 1];
            System.arraycopy(bytes, 1, tmp, 0, tmp.length);
            bytes = tmp;
        }
        return new String(bytes);
    }
}