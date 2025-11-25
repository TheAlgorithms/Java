package com.thealgorithms.ciphers;

import java.security.SecureRandom;
import java.util.Objects;

public final class OneTimePadCipher {

    private static final SecureRandom RANDOM = new SecureRandom();

    private OneTimePadCipher() {}

    public static byte[] generateKey(final int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Key length must be positive");
        }
        byte[] key = new byte[length];
        RANDOM.nextBytes(key);
        return key;
    }

    public static byte[] encrypt(final byte[] plaintext, final byte[] key) {
        Objects.requireNonNull(plaintext, "plaintext");
        Objects.requireNonNull(key, "key");

        if (plaintext.length != key.length) {
            throw new IllegalArgumentException("Plaintext and key must have the same length");
        }

        byte[] ciphertext = new byte[plaintext.length];
        for (int i = 0; i < plaintext.length; i++) {
            ciphertext[i] = (byte) (plaintext[i] ^ key[i]);
        }
        return ciphertext;
    }

    public static byte[] decrypt(final byte[] ciphertext, final byte[] key) {
        return encrypt(ciphertext, key);
    }
}
