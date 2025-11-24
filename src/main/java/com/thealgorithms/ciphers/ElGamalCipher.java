package com.thealgorithms.ciphers;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Simple ElGamal implementation for educational purposes.
 *
 * <p>Note: This implementation is intended for educational / illustrative use in
 * algorithm libraries. Real-world cryptographic usage requires careful review,
 * authenticated encryption, parameter validation, constant-time implementations,
 * and using well-reviewed libraries.
 *
 * <p>References:
 * https://en.wikipedia.org/wiki/ElGamal_encryption
 *
 * Author: TheAlgorithms
 */
public final class ElGamalCipher {

    private static final SecureRandom RANDOM = new SecureRandom();

    private ElGamalCipher() {
        // Utility class
    }

    /** Public key container. */
    public static final class PublicKey {
        public final BigInteger p; // prime modulus
        public final BigInteger g; // generator
        public final BigInteger h; // h = g^x mod p

        public PublicKey(final BigInteger p, final BigInteger g, final BigInteger h) {
            this.p = p;
            this.g = g;
            this.h = h;
        }
    }

    /** Private key container. */
    public static final class PrivateKey {
        public final BigInteger p;
        public final BigInteger x; // secret exponent

        public PrivateKey(final BigInteger p, final BigInteger x) {
            this.p = p;
            this.x = x;
        }
    }

    /** Ciphertext pair (c1, c2). */
    public static final class CipherText {
        public final BigInteger c1;
        public final BigInteger c2;

        public CipherText(final BigInteger c1, final BigInteger c2) {
            this.c1 = c1;
            this.c2 = c2;
        }
    }

    /**
     * Generates an ElGamal keypair.
     *
     * @param bitLength size of prime modulus (e.g., 2048)
     * @return an array where [0]=PublicKey and [1]=PrivateKey
     */
    public static Object[] generateKeyPair(final int bitLength) {
        final BigInteger p = BigInteger.probablePrime(bitLength, RANDOM);
        // find a generator g in [2, p-2] (this simple approach picks a candidate; for
        // production use proper safe-prime/generator selection)
        final BigInteger g = BigInteger.valueOf(2);
        final BigInteger x = new BigInteger(bitLength - 2, RANDOM).mod(p.subtract(BigInteger.TWO)).add(BigInteger.ONE);
        final BigInteger h = g.modPow(x, p);
        final PublicKey pub = new PublicKey(p, g, h);
        final PrivateKey priv = new PrivateKey(p, x);
        return new Object[] {pub, priv};
    }

    /**
     * Encrypts a message m (0 < m < p).
     *
     * @param m   message as BigInteger (must be less than p)
     * @param pub the public key
     * @return ciphertext pair
     */
    public static CipherText encrypt(final BigInteger m, final PublicKey pub) {
        if (m.compareTo(BigInteger.ZERO) <= 0 || m.compareTo(pub.p) >= 0) {
            throw new IllegalArgumentException("Message out of range");
        }
        final BigInteger y = new BigInteger(pub.p.bitLength() - 1, RANDOM).mod(pub.p.subtract(BigInteger.TWO)).add(BigInteger.ONE);
        final BigInteger c1 = pub.g.modPow(y, pub.p);
        final BigInteger s = pub.h.modPow(y, pub.p); // shared secret
        final BigInteger c2 = s.multiply(m).mod(pub.p);
        return new CipherText(c1, c2);
    }

    /**
     * Decrypts a ciphertext using the private key.
     *
     * @param ct   ciphertext
     * @param priv private key
     * @return decrypted message as BigInteger
     */
    public static BigInteger decrypt(final CipherText ct, final PrivateKey priv) {
        final BigInteger s = ct.c1.modPow(priv.x, priv.p);
        final BigInteger sInv = s.modInverse(priv.p);
        return ct.c2.multiply(sInv).mod(priv.p);
    }
}
