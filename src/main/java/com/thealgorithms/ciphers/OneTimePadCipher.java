package com.thealgorithms.ciphers;

import java.security.SecureRandom;
import java.util.Objects;

/**
 * One-Time Pad (OTP) cipher implementation.
 *
 * <p>The One-Time Pad is information-theoretically secure if:
 * <ul>
 *   <li>The key is truly random.</li>
 *   <li>The key length is at least as long as the plaintext.</li>
 *   <li>The key is used only once and kept secret.</li>
 * </ul>
 *
 * <p>This implementation is for <b>educational purposes only</b> and should not be
 * used in production systems.
 */
public final class OneTimePadCipher {

    private static final SecureRandom RANDOM = new SecureRandom();

    private OneTimePadCipher() {
        // utility class
    }

    /**
     * Generates a random key of the given length in bytes.
     *
     * @param length the length of the key in bytes, must be non-negative
     * @return a new random key
     * @throws IllegalArgumentException if length is negative
     */
    public static byte[] generateKey(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("length must be non-negative");
        }
        byte[] key = new byte[length];
        RANDOM.nextBytes(key);
        return key;
    }

    /**
     * Encrypts the given plaintext bytes using the provided key.
     * <p>The key length must be exactly the same as the plaintext length.
     *
     * @param plaintext the plaintext bytes, must not be {@code null}
     * @param key the one-time pad key bytes, must not be {@code null}
     * @return the ciphertext bytes
     * @throws IllegalArgumentException if the key length does not match plaintext length
     * @throws NullPointerException if plaintext or key is {@code null}
     */
    public static byte[] encrypt(byte[] plaintext, byte[] key) {
        validateInputs(plaintext, key);
        return xor(plaintext, key);
    }

    /**
     * Decrypts the given ciphertext bytes using the provided key.
     * <p>For a One-Time Pad, decryption is identical to encryption:
     * {@code plaintext = ciphertext XOR key}.
     *
     * @param ciphertext the ciphertext bytes, must not be {@code null}
     * @param key the one-time pad key bytes, must not be {@code null}
     * @return the decrypted plaintext bytes
     * @throws IllegalArgumentException if the key length does not match ciphertext length
     * @throws NullPointerException if ciphertext or key is {@code null}
     */
    public static byte[] decrypt(byte[] ciphertext, byte[] key) {
        validateInputs(ciphertext, key);
        return xor(ciphertext, key);
    }

    private static void validateInputs(byte[] input, byte[] key) {
        Objects.requireNonNull(input, "input must not be null");
        Objects.requireNonNull(key, "key must not be null");
        if (input.length != key.length) {
            throw new IllegalArgumentException("Key length must match input length");
        }
    }

    private static byte[] xor(byte[] data, byte[] key) {
        byte[] result = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = (byte) (data[i] ^ key[i]);
        }
        return result;
    }
}
