package com.thealgorithms.ciphers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Winternitz one-time signatures (WOTS) are a hash-based post-quantum signature scheme and a
 * size-optimized version of the Lamport signature.
 *
 * <p>The message digest is split into base-{@code w} digits and each digit is signed with a hash
 * chain: secret {@code sk[i]} is hashed {@code m[i]} times, while the public key is the chain end
 * after {@code w - 1} hashes. A larger {@code w} gives smaller signatures but more hashing
 * (133, 67 and 34 values for {@code w} = 4, 16 and 256).
 *
 * <p>Security relies only on the one-wayness of the hash function, which is why the scheme is
 * considered quantum-resistant. A checksum over the digits is signed too, so an attacker cannot
 * hash revealed values further to forge a signature. Each key pair can sign only one message,
 * since a second signature reveals enough chain values to forge new ones.
 *
 * <p>WOTS is the building block of XMSS and SPHINCS+, which use the WOTS+ variant with per-step
 * bitmasks. This implementation is educational and must not be used in production.
 *
 * <p>Reference: <a href="https://en.wikipedia.org/wiki/Hash-based_cryptography#One-time_signature_schemes">Wikipedia: Hash-based cryptography</a>
 *
 * @author dilaraacetin
 * @see LamportSignature
 */
public final class WinternitzSignature {

    // SHA-256 output length in bytes
    private static final int HASH_BYTES = 32;
    private static final int DEFAULT_W = 16;

    private final Parameters parameters;
    private final byte[][] privateKey;
    private final byte[][] publicKey;
    private boolean used;

    /**
     * Generates a new key pair with the default Winternitz parameter {@code w = 16}.
     */
    public WinternitzSignature() {
        this(DEFAULT_W);
    }

    /**
     * Generates a new key pair with the given Winternitz parameter.
     *
     * @param w the Winternitz parameter; must be 4, 16 or 256
     * @throws IllegalArgumentException if {@code w} is not a supported value
     */
    public WinternitzSignature(int w) {
        this.parameters = Parameters.forW(w);
        SecureRandom secureRandom = new SecureRandom();
        privateKey = new byte[parameters.len()][HASH_BYTES];
        publicKey = new byte[parameters.len()][];
        for (int i = 0; i < parameters.len(); i++) {
            secureRandom.nextBytes(privateKey[i]);
            publicKey[i] = chain(privateKey[i], w - 1);
        }
    }

    /**
     * Returns a copy of the public key.
     *
     * @return {@code len} hash chain end values, each 32 bytes long
     */
    public byte[][] getPublicKey() {
        return deepCopy(publicKey);
    }

    /**
     * Signs a message. A key pair can sign only one message.
     *
     * @param message the message to sign
     * @return the signature: {@code len} values of 32 bytes each (67 values for {@code w = 16})
     * @throws IllegalArgumentException if the message is null
     * @throws IllegalStateException if this key pair has already been used to sign a message
     */
    public byte[][] sign(byte[] message) {
        if (message == null) {
            throw new IllegalArgumentException("message must not be null");
        }
        if (used) {
            throw new IllegalStateException("This Winternitz key pair can only sign one message");
        }
        used = true;

        int[] digits = messageDigits(hash(message), parameters);
        byte[][] signature = new byte[parameters.len()][];
        for (int i = 0; i < parameters.len(); i++) {
            signature[i] = chain(privateKey[i], digits[i]);
        }
        return signature;
    }

    /**
     * Verifies a signature against a public key. Using a different {@code w} than the signer
     * causes a size mismatch and an {@link IllegalArgumentException}.
     *
     * @param message the signed message
     * @param signature the signature to check
     * @param publicKey the public key of the signer
     * @param w the Winternitz parameter used to create the key pair; must be 4, 16 or 256
     * @return true if the signature is valid for the message and public key, false otherwise
     * @throws IllegalArgumentException if an argument is null, {@code w} is unsupported, or the
     *     signature or public key is malformed
     */
    public static boolean verify(byte[] message, byte[][] signature, byte[][] publicKey, int w) {
        Parameters params = Parameters.forW(w);
        if (message == null) {
            throw new IllegalArgumentException("message must not be null");
        }
        validateShape(signature, params.len(), "signature");
        validateShape(publicKey, params.len(), "publicKey");

        int[] digits = messageDigits(hash(message), params);
        for (int i = 0; i < params.len(); i++) {
            // complete the chain: m[i] + (w - 1 - m[i]) = w - 1 steps
            byte[] chainEnd = chain(signature[i], w - 1 - digits[i]);
            if (!MessageDigest.isEqual(chainEnd, publicKey[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Parameters derived from {@code w}: {@code len1} message chains and {@code len2} checksum chains.
     */
    record Parameters(int w, int log2w, int len1, int len2) {

        static Parameters forW(int w) {
            if (w != 4 && w != 16 && w != 256) {
                throw new IllegalArgumentException("w must be 4, 16 or 256, got " + w);
            }
            int log2w = Integer.numberOfTrailingZeros(w);
            // len1 = ceil(8n / log2(w))
            int len1 = (8 * HASH_BYTES + log2w - 1) / log2w;
            // len2 = floor(log2(len1 * (w - 1)) / log2(w)) + 1
            int maxChecksumBits = 32 - Integer.numberOfLeadingZeros(len1 * (w - 1));
            int len2 = (maxChecksumBits - 1) / log2w + 1;
            return new Parameters(w, log2w, len1, len2);
        }

        int len() {
            return len1 + len2;
        }
    }

    /**
     * Converts a digest into {@code len1} base-{@code w} message digits followed by {@code len2}
     * checksum digits. Shared by sign and verify.
     */
    private static int[] messageDigits(byte[] digest, Parameters params) {
        int[] digits = new int[params.len()];
        int mask = params.w() - 1;

        // split the digest into log2(w)-bit digits
        int bitsLeft = 0;
        int currentByte = 0;
        int byteIndex = 0;
        for (int i = 0; i < params.len1(); i++) {
            if (bitsLeft == 0) {
                currentByte = digest[byteIndex++] & 0xFF; // unsigned byte
                bitsLeft = 8;
            }
            bitsLeft -= params.log2w();
            digits[i] = (currentByte >> bitsLeft) & mask;
        }

        // checksum C = sum(w - 1 - m[i])
        int checksum = 0;
        for (int i = 0; i < params.len1(); i++) {
            checksum += mask - digits[i];
        }

        // write C as len2 base-w digits, most significant first
        for (int i = params.len() - 1; i >= params.len1(); i--) {
            digits[i] = checksum & mask;
            checksum >>>= params.log2w();
        }
        return digits;
    }

    /**
     * Hashes {@code x} {@code iterations} times; returns a copy of {@code x} for zero iterations.
     */
    private static byte[] chain(byte[] x, int iterations) {
        byte[] result = x.clone();
        for (int i = 0; i < iterations; i++) {
            result = hash(result);
        }
        return result;
    }

    private static byte[] hash(byte[] data) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("SHA-256 is required by the Java SE specification", e);
        }
    }

    private static void validateShape(byte[][] values, int expectedLength, String name) {
        if (values == null) {
            throw new IllegalArgumentException(name + " must not be null");
        }
        if (values.length != expectedLength) {
            throw new IllegalArgumentException(name + " must contain exactly " + expectedLength + " values, got " + values.length);
        }
        for (byte[] value : values) {
            if (value == null || value.length != HASH_BYTES) {
                throw new IllegalArgumentException(name + " values must be exactly " + HASH_BYTES + " bytes long");
            }
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
