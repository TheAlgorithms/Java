package com.thealgorithms.ciphers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * A simple implementation of XOR cipher that, given a key, allows to encrypt and decrypt a plaintext.
 *
 * @author <a href="https://github.com/lcsjunior">lcsjunior</a>
 *
 */
public final class XORCipher {

    private static final Charset CS_DEFAULT = StandardCharsets.UTF_8;

    private XORCipher() {
    }

    private static byte[] xor(final byte[] inputBytes, final byte[] keyBytes) {
        byte[] outputBytes = new byte[inputBytes.length];
        for (int i = 0; i < inputBytes.length; ++i) {
            outputBytes[i] = (byte) (inputBytes[i] ^ keyBytes[i % keyBytes.length]);
        }
        return outputBytes;
    }

    public static String encrypt(final String plaintext, final String key) {
        byte[] plaintextBytes = plaintext.getBytes(CS_DEFAULT);
        byte[] keyBytes = key.getBytes(CS_DEFAULT);
        return Base64.getEncoder().encodeToString(xor(plaintextBytes, keyBytes));
    }

    public static String decrypt(final String cipher, final String key) {
        byte[] cipherBytes = Base64.getDecoder().decode(cipher);
        byte[] keyBytes = key.getBytes(CS_DEFAULT);
        return new String(xor(cipherBytes, keyBytes), CS_DEFAULT);
    }
}
