package com.thealgorithms.ciphers;

import java.security.SecureRandom;

/**
 * Public-key encryption based on the Learning With Errors (LWE) problem, as proposed by Regev (2005).
 *
 * <p><b>Toy parameters for educational purposes only — NOT secure for real-world use.</b>
 *
 * <p>The LWE problem: given many noisy samples {@code b = A·s + e (mod q)} with a random matrix
 * {@code A} and small random errors {@code e}, it is hard to recover the secret vector {@code s}.
 * Without the errors, {@code s} could be found with Gaussian elimination.
 *
 * <p>The public key is {@code (A, b)} and the private key is {@code s}. To encrypt a bit, a random
 * subset {@code r} of the samples is summed: {@code u = rᵀ·A} and {@code v = r·b + bit·⌊q/2⌋}.
 * Decryption computes {@code v - u·s = r·e + bit·⌊q/2⌋}: a value close to 0 means bit 0, a value
 * close to {@code q/2} means bit 1.
 *
 * <p>Decryption is always correct because the remaining noise is bounded: {@code r} is a 0/1 vector
 * and {@code |e_i| ≤ B}, so {@code |r·e| ≤ m·B = 384 < q/4 ≈ 832}.
 *
 * <p>The modulus {@code q = 3329} is the one used by Kyber / ML-KEM (FIPS 203), which uses the
 * module-lattice version of this construction with discrete Gaussian-like noise and much larger
 * parameters. For simplicity, one instance holds both the public and the private key; in real use
 * they are kept separately.
 *
 * <p>Reference: <a href="https://en.wikipedia.org/wiki/Learning_with_errors">Wikipedia: Learning with errors</a>
 *
 * @author dilaraacetin
 */
public final class LWEEncryption {

    // dimension of the secret vector
    private static final int N = 32;
    // modulus, same as Kyber / ML-KEM
    private static final int Q = 3329;
    // number of samples in the public key
    private static final int M = 128;
    // errors are uniform in [-B, B]
    private static final int B = 3;
    private static final int BITS_PER_BYTE = 8;

    private final SecureRandom random = new SecureRandom();
    private final int[][] a;
    private final int[] b;
    private final int[] s;

    /**
     * Generates a new key pair.
     *
     * @throws IllegalStateException if the parameters do not guarantee correct decryption
     */
    public LWEEncryption() {
        checkDecryptionBound(M, B, Q);
        s = new int[N];
        for (int j = 0; j < N; j++) {
            s[j] = random.nextInt(Q);
        }
        a = new int[M][N];
        b = new int[M];
        for (int i = 0; i < M; i++) {
            long sum = 0;
            for (int j = 0; j < N; j++) {
                a[i][j] = random.nextInt(Q);
                sum += (long) a[i][j] * s[j];
            }
            int error = random.nextInt(2 * B + 1) - B;
            // b_i = A_i·s + e_i (mod q)
            b[i] = (int) Math.floorMod(sum + error, (long) Q);
        }
    }

    /**
     * Encrypts a single bit. Encryption is randomized, so the same bit gives different ciphertexts.
     *
     * @param bit the bit to encrypt, 0 or 1
     * @return the ciphertext {@code (u, v)}
     * @throws IllegalArgumentException if {@code bit} is not 0 or 1
     */
    public Ciphertext encryptBit(int bit) {
        if (bit != 0 && bit != 1) {
            throw new IllegalArgumentException("bit must be 0 or 1, got " + bit);
        }
        long[] u = new long[N];
        long v = (long) bit * (Q / 2);
        for (int i = 0; i < M; i++) {
            // r_i is 0 or 1: add sample i or skip it
            if (random.nextBoolean()) {
                for (int j = 0; j < N; j++) {
                    u[j] += a[i][j];
                }
                v += b[i];
            }
        }
        int[] reduced = new int[N];
        for (int j = 0; j < N; j++) {
            reduced[j] = (int) Math.floorMod(u[j], (long) Q);
        }
        return new Ciphertext(reduced, (int) Math.floorMod(v, (long) Q));
    }

    /**
     * Decrypts a single bit.
     *
     * @param ciphertext the ciphertext to decrypt
     * @return the decrypted bit, 0 or 1
     * @throws IllegalArgumentException if the ciphertext is null
     */
    public int decryptBit(Ciphertext ciphertext) {
        if (ciphertext == null) {
            throw new IllegalArgumentException("ciphertext must not be null");
        }
        long dot = 0;
        for (int j = 0; j < N; j++) {
            dot += (long) ciphertext.u[j] * s[j];
        }
        // d = r·e + bit·⌊q/2⌋ (mod q); floorMod keeps it non-negative
        int d = (int) Math.floorMod(ciphertext.v - dot, (long) Q);
        return d >= Q / 4 && d < 3 * Q / 4 ? 1 : 0;
    }

    /**
     * Encrypts a message bit by bit, most significant bit of each byte first.
     *
     * @param message the message to encrypt
     * @return eight ciphertexts per message byte
     * @throws IllegalArgumentException if the message is null or empty
     */
    public Ciphertext[] encrypt(byte[] message) {
        if (message == null || message.length == 0) {
            throw new IllegalArgumentException("message must not be null or empty");
        }
        Ciphertext[] ciphertexts = new Ciphertext[message.length * BITS_PER_BYTE];
        for (int i = 0; i < message.length; i++) {
            for (int k = 0; k < BITS_PER_BYTE; k++) {
                ciphertexts[i * BITS_PER_BYTE + k] = encryptBit((message[i] >> (7 - k)) & 1);
            }
        }
        return ciphertexts;
    }

    /**
     * Decrypts a message encrypted with {@link #encrypt(byte[])}.
     *
     * @param ciphertexts eight ciphertexts per message byte
     * @return the decrypted message
     * @throws IllegalArgumentException if the array is null, empty, contains null, or its length is
     *     not a multiple of 8
     */
    public byte[] decrypt(Ciphertext[] ciphertexts) {
        if (ciphertexts == null || ciphertexts.length == 0) {
            throw new IllegalArgumentException("ciphertexts must not be null or empty");
        }
        if (ciphertexts.length % BITS_PER_BYTE != 0) {
            throw new IllegalArgumentException("ciphertexts length must be a multiple of 8, got " + ciphertexts.length);
        }
        byte[] message = new byte[ciphertexts.length / BITS_PER_BYTE];
        for (int i = 0; i < message.length; i++) {
            int value = 0;
            for (int k = 0; k < BITS_PER_BYTE; k++) {
                value = (value << 1) | decryptBit(ciphertexts[i * BITS_PER_BYTE + k]);
            }
            message[i] = (byte) value;
        }
        return message;
    }

    /**
     * Checks that the maximum decryption noise {@code m·B} stays below {@code q/4}.
     *
     * @throws IllegalStateException if decryption could fail with these parameters
     */
    static void checkDecryptionBound(int samples, int errorBound, int modulus) {
        if (samples * errorBound >= modulus / 4) {
            throw new IllegalStateException("m * B must be less than q / 4 for correct decryption");
        }
    }

    /**
     * An LWE ciphertext {@code (u, v)} for one bit. Immutable: the array is copied on construction
     * and on access.
     */
    public static final class Ciphertext {
        private final int[] u;
        private final int v;

        /**
         * Creates a ciphertext.
         *
         * @param u the vector part, of length 32
         * @param v the scalar part
         * @throws IllegalArgumentException if {@code u} is null or does not have length 32
         */
        public Ciphertext(int[] u, int v) {
            if (u == null || u.length != N) {
                throw new IllegalArgumentException("u must contain exactly " + N + " values");
            }
            this.u = u.clone();
            this.v = v;
        }

        /**
         * @return a copy of the vector part
         */
        public int[] getU() {
            return u.clone();
        }

        /**
         * @return the scalar part
         */
        public int getV() {
            return v;
        }
    }
}
