package com.thealgorithms.ciphers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

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

    public static byte[] xor(final byte[] inputBytes, final byte[] keyBytes) {
        byte[] outputBytes = new byte[inputBytes.length];
        for (int i = 0; i < inputBytes.length; ++i) {
            outputBytes[i] = (byte) (inputBytes[i] ^ keyBytes[i % keyBytes.length]);
        }
        return outputBytes;
    }

    public static String encrypt(final String plainText, final String key) {
        byte[] plainTextBytes = plainText.getBytes(CS_DEFAULT);
        byte[] keyBytes = key.getBytes(CS_DEFAULT);
        byte[] xorResult = xor(plainTextBytes, keyBytes);
        return HexFormat.of().formatHex(xorResult);
    }

    public static String decrypt(final String cipherText, final String key) {
        byte[] cipherBytes = HexFormat.of().parseHex(cipherText);
        byte[] keyBytes = key.getBytes(CS_DEFAULT);
        byte[] xorResult = xor(cipherBytes, keyBytes);
        return new String(xorResult, CS_DEFAULT);
    }
}
