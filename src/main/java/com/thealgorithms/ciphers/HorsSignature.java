package com.thealgorithms.ciphers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * HORS (Hash to Obtain Random Subset) is a hash-based few-time signature scheme by Reyzin and
 * Reyzin (2002).
 *
 * <p>The private key consists of {@code t = 2^τ} random 32-byte secrets and the public key contains
 * their SHA-256 hashes. To sign, the SHA-256 digest of the message is split into {@code k} chunks of
 * {@code τ} bits (most significant bit first); each chunk is an index into the key, and the secrets
 * at those {@code k} indices form the signature. The verifier derives the same indices and checks
 * that each revealed secret hashes to the matching public-key value. The same index may appear more
 * than once, as in the original scheme. Signing is deterministic.
 *
 * <p>HORS is a few-time scheme: each signature reveals {@code k} secrets, so after {@code r}
 * signatures an attacker knows at most {@code r·k} of them. If all indices of another message fall
 * into that set, its signature can be forged, so security decreases with every signature.
 *
 * <p>HORST compresses the large public key with a Merkle tree. FORS, used in SPHINCS+ and SLH-DSA
 * (FIPS 205), uses a forest of trees instead, which also removes the repeated-index weakness. This
 * implementation is educational and must not be used in production.
 *
 * <p>References: <a href="https://eprint.iacr.org/2002/014">Reyzin and Reyzin, Better than BiBa</a>,
 * <a href="https://en.wikipedia.org/wiki/Hash-based_cryptography">Wikipedia: Hash-based cryptography</a>
 *
 * @author dilaraacetin
 * @see LamportSignature
 * @see WinternitzSignature
 * @see MerkleSignatureScheme
 */
public final class HorsSignature {

    // SHA-256 output length in bytes
    private static final int HASH_BYTES = 32;
    private static final int DIGEST_BITS = 256;
    private static final int MIN_T = 16;
    private static final int MAX_T = 65536;
    private static final int DEFAULT_T = 1024;
    private static final int DEFAULT_K = 16;

    private final int k;
    private final int tau;
    private final byte[][] privateKey;
    private final byte[][] publicKey;

    /**
     * Generates a new key pair with {@code t = 1024} and {@code k = 16}.
     */
    public HorsSignature() {
        this(DEFAULT_T, DEFAULT_K);
    }

    /**
     * Generates a new key pair.
     *
     * @param t the number of secrets; a power of two between 16 and 65536
     * @param k the number of secrets revealed per signature; {@code k·log2(t)} must not exceed 256
     * @throws IllegalArgumentException if {@code t} or {@code k} is not supported
     */
    public HorsSignature(int t, int k) {
        this.tau = tauOf(t);
        validateK(k, tau);
        this.k = k;
        SecureRandom secureRandom = new SecureRandom();
        privateKey = new byte[t][HASH_BYTES];
        publicKey = new byte[t][];
        for (int i = 0; i < t; i++) {
            secureRandom.nextBytes(privateKey[i]);
            publicKey[i] = hash(privateKey[i]);
        }
    }

    /**
     * Returns a copy of the public key.
     *
     * @return {@code t} hash values, each 32 bytes long
     */
    public byte[][] getPublicKey() {
        return deepCopy(publicKey);
    }

    /**
     * Signs a message. Every signature reveals {@code k} secrets, so a key pair should only sign a
     * few messages.
     *
     * @param message the message to sign
     * @return the signature: {@code k} secrets of 32 bytes each
     * @throws IllegalArgumentException if the message is null
     */
    public byte[][] sign(byte[] message) {
        if (message == null) {
            throw new IllegalArgumentException("message must not be null");
        }
        int[] indices = messageIndices(hash(message), k, tau);
        byte[][] signature = new byte[k][];
        for (int j = 0; j < k; j++) {
            signature[j] = privateKey[indices[j]].clone();
        }
        return signature;
    }

    /**
     * Verifies a signature against a public key. {@code t} is taken from the public key length and
     * {@code k} from the signature length.
     *
     * @param message the signed message
     * @param signature the signature to check
     * @param publicKey the public key of the signer
     * @return true if the signature is valid for the message and public key, false otherwise
     * @throws IllegalArgumentException if an argument is null, the public key length is not a
     *     supported {@code t}, a value is not 32 bytes long, or the signature is empty or uses more
     *     than 256 digest bits
     */
    public static boolean verify(byte[] message, byte[][] signature, byte[][] publicKey) {
        if (message == null) {
            throw new IllegalArgumentException("message must not be null");
        }
        validateValues(publicKey, "publicKey");
        validateValues(signature, "signature");
        int tau = tauOf(publicKey.length);
        validateK(signature.length, tau);

        int[] indices = messageIndices(hash(message), signature.length, tau);
        for (int j = 0; j < signature.length; j++) {
            if (!MessageDigest.isEqual(hash(signature[j]), publicKey[indices[j]])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Splits the first {@code k·τ} bits of a digest into {@code k} unsigned {@code τ}-bit indices,
     * most significant bit first. Shared by sign and verify.
     */
    static int[] messageIndices(byte[] digest, int k, int tau) {
        if ((long) k * tau > digest.length * 8L) {
            throw new IllegalArgumentException("digest is too short for " + k + " indices of " + tau + " bits");
        }
        int[] indices = new int[k];
        for (int j = 0; j < k; j++) {
            int index = 0;
            for (int i = 0; i < tau; i++) {
                int bit = j * tau + i;
                // bit 0 is the most significant bit of digest[0]; & 0xFF reads the byte as unsigned
                index = (index << 1) | (((digest[bit / 8] & 0xFF) >> (7 - bit % 8)) & 1);
            }
            indices[j] = index;
        }
        return indices;
    }

    private static int tauOf(int t) {
        if (t < MIN_T || t > MAX_T || (t & (t - 1)) != 0) {
            throw new IllegalArgumentException("t must be a power of two between " + MIN_T + " and " + MAX_T + ", got " + t);
        }
        return Integer.numberOfTrailingZeros(t);
    }

    private static void validateK(int k, int tau) {
        // k > DIGEST_BITS / tau is the same as k * tau > DIGEST_BITS, but cannot overflow
        if (k < 1 || k > DIGEST_BITS / tau) {
            throw new IllegalArgumentException("k must be at least 1 and k * log2(t) at most " + DIGEST_BITS + ", got k = " + k);
        }
    }

    private static void validateValues(byte[][] values, String name) {
        if (values == null) {
            throw new IllegalArgumentException(name + " must not be null");
        }
        for (byte[] value : values) {
            if (value == null || value.length != HASH_BYTES) {
                throw new IllegalArgumentException(name + " values must be exactly " + HASH_BYTES + " bytes long");
            }
        }
    }

    private static byte[] hash(byte[] data) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("SHA-256 is required by the Java SE specification", e);
        }
    }

    private static byte[][] deepCopy(byte[][] values) {
        byte[][] copy = new byte[values.length][];
        for (int i = 0; i < values.length; i++) {
            copy[i] = values[i].clone();
        }
        return copy;
    }
}
