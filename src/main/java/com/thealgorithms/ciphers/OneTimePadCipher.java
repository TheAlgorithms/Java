package com.thealgorithms.ciphers;

import java.security.SecureRandom;

/**
 * The {@code OneTimePadCipher} class provides a simple implementation of the
 * One-Time Pad (OTP) cipher — a theoretically unbreakable symmetric encryption
 * technique when used correctly.
 * <p>
 * This cipher works by generating a truly random key of the same length as the
 * plaintext, and then performing a bitwise XOR (exclusive OR) operation between
 * the plaintext bytes and the key bytes. The resulting ciphertext can be
 * decrypted by applying the same XOR operation again using the same key.
 * <p>
 * <strong>Important:</strong> For the OTP cipher to be perfectly secure, the
 * following conditions must be met:
 * <ul>
 *   <li>The key must be truly random.</li>
 *   <li>The key must be at least as long as the message.</li>
 *   <li>The key must never be reused (used only once).</li>
 *   <li>The key must be kept completely secret.</li>
 * </ul>
 * <p>
 * Example usage:
 * <pre>{@code
 * String message = "HELLO";
 * byte[] plaintext = message.getBytes(StandardCharsets.UTF_8);
 * byte[] key = OneTimePadCipher.generateKey(plaintext.length);
 *
 * // Encrypt
 * byte[] ciphertext = OneTimePadCipher.applyOTP(plaintext, key);
 *
 * // Decrypt (same method)
 * byte[] decrypted = OneTimePadCipher.applyOTP(ciphertext, key);
 * System.out.println(new String(decrypted, StandardCharsets.UTF_8)); // "HELLO"
 * }</pre>
 *
 * @see <a href="https://en.wikipedia.org/wiki/One-time_pad">One-Time Pad Cipher - Wikipedia</a>
 * @author <a href="https://github.com/newtownboy">newtownboy</a>
 */
public final class OneTimePadCipher {
    private static final SecureRandom random = new SecureRandom();

    private OneTimePadCipher() {
    }

    /**
     * Generate a truly random key of the same length as the plaintext.
     *
     * @param length length of the key
     * @return random key as byte array
     */
    public static byte[] generateKey(int length) {
        byte[] key = new byte[length];
        random.nextBytes(key);
        return key;
    }

    /**
     * Encrypt or decrypt using XOR operation.
     * Since XOR is symmetric, the same method works for both.
     *
     * @param input plaintext or ciphertext
     * @param key   key of the same length as input
     * @return result after XOR
     */
    public static byte[] applyOTP(byte[] input, byte[] key) {
        if (input.length != key.length) {
            throw new IllegalArgumentException("Input and key must be the same length");
        }

        byte[] output = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = (byte) (input[i] ^ key[i]);
        }
        return output;
    }
}
