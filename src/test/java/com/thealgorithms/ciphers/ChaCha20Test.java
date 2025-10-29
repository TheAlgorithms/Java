package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays; // Added import
import org.junit.jupiter.api.Test;

public class ChaCha20Test {

    // Test vector from RFC 8439, Section 2.4.2.
    private static final byte[] RFC8439_KEY = hexStringToByteArray("000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f");
    private static final byte[] RFC8439_NONCE = hexStringToByteArray("000000000000004a00000000"); // Counter = 1, Nonce = 00000000 0000004a 00000000
    private static final byte[] RFC8439_PLAINTEXT_64 = hexStringToByteArray("4c616469657320616e642047656e746c656d656e206f662074686520636c617373206f66202739393a20" + "4966204920636f756c64206f6666657220796f75206f6e6c79206f6e652074697020666f722074686520" + "6675747572652c2073756e73637265656e20776f756c642062652069742e");
    private static final byte[] RFC8439_CIPHERTEXT_64 = hexStringToByteArray("6e2e359a2568f98041ba0728dd0d6981e97e7aec1d4360c20a27afccfd9fae0bf91b65c5524733ab8f583" + "75fcd4af034bd16adec164f7a2bda3dc0343a99a46c8b4172421b505877c570b1351d530635359a37e5f1" + "797b596a78c149d5d9963e696f8c792374c4314c67d163f97205463f668f438a0c20a3a7187");

    // Test vector from RFC 8439, Section 2.4.2 for 114 bytes
    private static final byte[] RFC8439_PLAINTEXT_114 = Arrays.copyOf(RFC8439_PLAINTEXT_64, 114);
    private static final byte[] RFC8439_CIPHERTEXT_114 = Arrays.copyOf(RFC8439_CIPHERTEXT_64, 114);

    @Test
    public void testEncryptRFC8439Vector64Bytes() {
        byte[] ciphertext = ChaCha20.encrypt(RFC8439_KEY, RFC8439_NONCE, RFC8439_PLAINTEXT_64);
        assertArrayEquals(RFC8439_CIPHERTEXT_64, ciphertext);
    }

    @Test
    public void testDecryptRFC8439Vector64Bytes() {
        byte[] plaintext = ChaCha20.decrypt(RFC8439_KEY, RFC8439_NONCE, RFC8439_CIPHERTEXT_64);
        assertArrayEquals(RFC8439_PLAINTEXT_64, plaintext);
    }

    @Test
    public void testEncryptRFC8439Vector114Bytes() {
        // Test encryption with plaintext length not a multiple of 64
        byte[] ciphertext = ChaCha20.encrypt(RFC8439_KEY, RFC8439_NONCE, RFC8439_PLAINTEXT_114);
        assertArrayEquals(RFC8439_CIPHERTEXT_114, ciphertext);
    }

    @Test
    public void testDecryptRFC8439Vector114Bytes() {
        // Test decryption with ciphertext length not a multiple of 64
        byte[] plaintext = ChaCha20.decrypt(RFC8439_KEY, RFC8439_NONCE, RFC8439_CIPHERTEXT_114);
        assertArrayEquals(RFC8439_PLAINTEXT_114, plaintext);
    }

    @Test
    public void testEncryptDecryptSymmetry() {
        byte[] key = new byte[32];
        for (int i = 0; i < 32; i++) {
            key[i] = (byte) i;
        }
        byte[] nonce = new byte[12];
        for (int i = 0; i < 12; i++) {
            nonce[i] = (byte) (i + 100);
        }
        String originalText = "This is a test message to verify encrypt/decrypt symmetry.";
        byte[] plaintext = originalText.getBytes();

        byte[] ciphertext = ChaCha20.encrypt(key, nonce, plaintext);
        byte[] decryptedText = ChaCha20.decrypt(key, nonce, ciphertext);

        assertArrayEquals(plaintext, decryptedText);
        assertEquals(originalText, new String(decryptedText));
    }

    @Test
    public void testEmptyPlaintext() {
        byte[] empty = new byte[0];
        byte[] ciphertext = ChaCha20.encrypt(RFC8439_KEY, RFC8439_NONCE, empty);
        assertArrayEquals(empty, ciphertext);
        byte[] plaintext = ChaCha20.decrypt(RFC8439_KEY, RFC8439_NONCE, empty);
        assertArrayEquals(empty, plaintext);
    }

    @Test
    public void testInvalidKeySize() {
        byte[] shortKey = new byte[31];
        byte[] longKey = new byte[33];
        byte[] nonce = new byte[12];
        byte[] data = {1, 2, 3};

        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(shortKey, nonce, data));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(longKey, nonce, data));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.decrypt(shortKey, nonce, data));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.decrypt(longKey, nonce, data));
    }

    @Test
    public void testInvalidNonceSize() {
        byte[] key = new byte[32];
        byte[] shortNonce = new byte[11];
        byte[] longNonce = new byte[13];
        byte[] data = {1, 2, 3};

        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(key, shortNonce, data));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(key, longNonce, data));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.decrypt(key, shortNonce, data));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.decrypt(key, longNonce, data));
    }

    @Test
    public void testNullInputs() {
        byte[] key = new byte[32];
        byte[] nonce = new byte[12];
        byte[] data = {1, 2, 3};

        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(null, nonce, data));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(key, null, data));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(key, nonce, null));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.decrypt(null, nonce, data));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.decrypt(key, null, data));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.decrypt(key, nonce, null));
    }

    // Helper to convert hex string to byte array for test vectors
    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        if (len % 2 != 0) {
            throw new IllegalArgumentException("Hex string must have even length");
        }
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}

