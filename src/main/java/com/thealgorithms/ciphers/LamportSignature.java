package com.thealgorithms.ciphers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Lamport one-time signatures (OTS) are a hash-based post-quantum signature scheme.
 *
 * <p>The scheme is based on a private key consisting of 256 pairs of random 32-byte secrets,
 * one pair per bit position of a SHA-256 digest. The public key contains the SHA-256 hashes of
 * those secrets.
 *
 * <p>To sign a message, the algorithm hashes the message and reveals the secret corresponding to
 * each bit value in the digest. Verification recomputes the digest and checks that each revealed
 * secret hashes to the expected public-key value.
 *
 * <p>This implementation is educational and intentionally keeps dependencies minimal. Each key pair
 * is strictly one-time: signing a second message with the same key is rejected.
 *
 * <p>Reference: <a href="https://en.wikipedia.org/wiki/Lamport_signature">Wikipedia: Lamport signature</a>
 */
public final class LamportSignature {

    private static final int DIGEST_BITS = 256;
    private static final int SECRET_BYTES = 32;
    private static final int NUM_VALUES = 2;

    private LamportSignature() {
    }

    /**
     * A Lamport key pair containing both the private and public material.
     */
    public static final class KeyPair {
        private final byte[][][] privateKey;
        private final byte[][][] publicKey;
        private boolean used;

        private KeyPair(byte[][][] privateKey, byte[][][] publicKey) {
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        }

        /**
         * Signs a message using this key pair.
         *
         * @param message the message to sign
         * @return the signature bytes; exactly 256 secret values ordered by digest bit positions
         * @throws IllegalArgumentException if the message is null
         * @throws IllegalStateException if this key pair has already been used to sign a message
         */
        public byte[] sign(byte[] message) {
            if (message == null) {
                throw new IllegalArgumentException("message must not be null");
            }
            if (used) {
                throw new IllegalStateException("This Lamport key pair can only sign one message");
            }
            used = true;

            byte[] digest = sha256(message);
            byte[] signature = new byte[DIGEST_BITS * SECRET_BYTES];
            for (int bit = 0; bit < DIGEST_BITS; bit++) {
                int value = (digest[bit / 8] >> (7 - (bit % 8))) & 0x01;
                byte[] secret = privateKey[bit][value];
                System.arraycopy(secret, 0, signature, bit * SECRET_BYTES, SECRET_BYTES);
            }
            return signature;
        }

        /**
         * Verifies a message signature against this public key.
         *
         * @param message the message to verify
         * @param signature the signature to verify
         * @return true if the signature is valid for the provided message and public key
         * @throws IllegalArgumentException if message or signature is null or malformed
         */
        public boolean verify(byte[] message, byte[] signature) {
            if (message == null) {
                throw new IllegalArgumentException("message must not be null");
            }
            if (signature == null) {
                throw new IllegalArgumentException("signature must not be null");
            }
            if (signature.length != DIGEST_BITS * SECRET_BYTES) {
                throw new IllegalArgumentException("signature length must be exactly 8192 bytes");
            }

            byte[] digest = sha256(message);
            for (int bit = 0; bit < DIGEST_BITS; bit++) {
                int value = (digest[bit / 8] >> (7 - (bit % 8))) & 0x01;
                byte[] revealedSecret = Arrays.copyOfRange(signature, bit * SECRET_BYTES, (bit + 1) * SECRET_BYTES);
                byte[] expectedHash = publicKey[bit][value];
                byte[] actualHash = sha256(revealedSecret);
                if (!Arrays.equals(actualHash, expectedHash)) {
                    return false;
                }
            }
            return true;
        }

        public byte[][][] getPrivateKey() {
            return privateKey;
        }

        public byte[][][] getPublicKey() {
            return publicKey;
        }
    }

    /**
     * Generates a new Lamport key pair.
     *
     * @return a fresh Lamport key pair
     */
    public static KeyPair generateKeyPair() {
        SecureRandom secureRandom = new SecureRandom();
        byte[][][] privateKey = new byte[DIGEST_BITS][NUM_VALUES][SECRET_BYTES];
        byte[][][] publicKey = new byte[DIGEST_BITS][NUM_VALUES][SECRET_BYTES];

        for (int bit = 0; bit < DIGEST_BITS; bit++) {
            for (int value = 0; value < NUM_VALUES; value++) {
                secureRandom.nextBytes(privateKey[bit][value]);
                publicKey[bit][value] = sha256(privateKey[bit][value]);
            }
        }
        return new KeyPair(privateKey, publicKey);
    }

    static byte[] sha256(byte[] input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 is not available on this platform", e);
        }
    }

    static byte[] sha256(byte[] message, int offset, int length) {
        return sha256(Arrays.copyOfRange(message, offset, offset + length));
    }
}
