package com.thealgorithms.ciphers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

/**
 * A simple implementation of the XOR cipher that allows both encryption and decryption
 * using a given key. This cipher works by applying the XOR bitwise operation between
 * the bytes of the input text and the corresponding bytes of the key (repeating the key
 * if necessary).
 *
 * Usage:
 * - Encryption: Converts plaintext into a hexadecimal-encoded ciphertext.
 * - Decryption: Converts the hexadecimal ciphertext back into plaintext.
 *
 * Characteristics:
 * - Symmetric: The same key is used for both encryption and decryption.
 * - Simple but vulnerable: XOR encryption is insecure for real-world cryptography,
 *   especially when the same key is reused.
 *
 * Example:
 * Plaintext: "Hello!"
 * Key: "key"
 * Encrypted: "27090c03120b"
 * Decrypted: "Hello!"
 *
 * Reference: <a href="https://en.wikipedia.org/wiki/XOR_cipher">XOR Cipher - Wikipedia</a>
 *
 * @author <a href="https://github.com/lcsjunior">lcsjunior</a>
 */
public final class XORCipher {

    // Default character encoding for string conversion
    private static final Charset CS_DEFAULT = StandardCharsets.UTF_8;

    private XORCipher() {
    }

    /**
     * Applies the XOR operation between the input bytes and the key bytes.
     * If the key is shorter than the input, it wraps around (cyclically).
     *
     * @param inputBytes The input byte array (plaintext or ciphertext).
     * @param keyBytes The key byte array used for XOR operation.
     * @return A new byte array containing the XOR result.
     */
    public static byte[] xor(final byte[] inputBytes, final byte[] keyBytes) {
        byte[] outputBytes = new byte[inputBytes.length];
        for (int i = 0; i < inputBytes.length; ++i) {
            outputBytes[i] = (byte) (inputBytes[i] ^ keyBytes[i % keyBytes.length]);
        }
        return outputBytes;
    }

    /**
     * Encrypts the given plaintext using the XOR cipher with the specified key.
     * The result is a hexadecimal-encoded string representing the ciphertext.
     *
     * @param plainText The input plaintext to encrypt.
     * @param key The encryption key.
     * @throws IllegalArgumentException if the key is empty.
     * @return A hexadecimal string representing the encrypted text.
     */
    public static String encrypt(final String plainText, final String key) {
        if (key.isEmpty()) {
            throw new IllegalArgumentException("Key must not be empty");
        }

        byte[] plainTextBytes = plainText.getBytes(CS_DEFAULT);
        byte[] keyBytes = key.getBytes(CS_DEFAULT);
        byte[] xorResult = xor(plainTextBytes, keyBytes);
        return HexFormat.of().formatHex(xorResult);
    }

    /**
     * Decrypts the given ciphertext (in hexadecimal format) using the XOR cipher
     * with the specified key. The result is the original plaintext.
     *
     * @param cipherText The hexadecimal string representing the encrypted text.
     * @param key The decryption key (must be the same as the encryption key).
     * @throws IllegalArgumentException if the key is empty.
     * @return The decrypted plaintext.
     */
    public static String decrypt(final String cipherText, final String key) {
        if (key.isEmpty()) {
            throw new IllegalArgumentException("Key must not be empty");
        }

        byte[] cipherBytes = HexFormat.of().parseHex(cipherText);
        byte[] keyBytes = key.getBytes(CS_DEFAULT);
        byte[] xorResult = xor(cipherBytes, keyBytes);
        return new String(xorResult, CS_DEFAULT);
    }
}
