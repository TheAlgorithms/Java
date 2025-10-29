package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ChaCha20Test {

    // RFC 8439 test vector
    private static final byte[] RFC8439_KEY =
            hexStringToByteArray("000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f");
    private static final byte[] RFC8439_NONCE =
            hexStringToByteArray("000000000000004a00000000");

    // RFC 8439 Section 2.4.2 plaintext and ciphertext (64 bytes)
    private static final byte[] RFC8439_PLAINTEXT_64 = hexStringToByteArray(
            "4c616469657320616e642047656e746c656d656e206f662074686520636c617373206f66202739393a204966204920636f756c64206f6666657220796f75206f6e6c79206f6e652074697020666f7220746865206675747572652c2073756e73637265656e20776f756c642062652069742e");
    private static final byte[] RFC8439_CIPHERTEXT_64 = hexStringToByteArray(
            "6e2e359a2568f98041ba0728dd0d6981e97e7aec1d4360c20a27afccfd9fae0bf91b65c5524733ab8f58375fcd4af034bd16adec164f7a2bda3dc0343a99a46c");

    // For 114 bytes (from RFC)
    private static final byte[] RFC8439_PLAINTEXT_114 = hexStringToByteArray(
            "4c616469657320616e642047656e746c656d656e206f662074686520636c617373206f66202739393a204966204920636f756c64206f6666657220796f75206f6e6c79206f6e652074697020666f7220746865206675747572652c2073756e73637265656e20776f756c642062652069742e");
    private static final byte[] RFC8439_CIPHERTEXT_114 = hexStringToByteArray(
            "6e2e359a2568f98041ba0728dd0d6981e97e7aec1d4360c20a27afccfd9fae0bf91b65c5524733ab8f58375fcd4af034bd16adec164f7a2bda3dc0343a99a46c");

    @Test
    public void testEncryptRFC8439Vector64Bytes() {
        assertArrayEquals(RFC8439_CIPHERTEXT_64,
                ChaCha20.encrypt(RFC8439_KEY, RFC8439_NONCE, RFC8439_PLAINTEXT_64));
    }

    @Test
    public void testDecryptRFC8439Vector64Bytes() {
        assertArrayEquals(RFC8439_PLAINTEXT_64,
                ChaCha20.decrypt(RFC8439_KEY, RFC8439_NONCE, RFC8439_CIPHERTEXT_64));
    }

    @Test
    public void testEncryptDecryptSymmetry() {
        byte[] key = new byte[32];
        byte[] nonce = new byte[12];
        String message = "This is a test message to verify encrypt/decrypt symmetry.";
        byte[] plaintext = message.getBytes();
        byte[] ciphertext = ChaCha20.encrypt(key, nonce, plaintext);
        byte[] decrypted = ChaCha20.decrypt(key, nonce, ciphertext);
        assertArrayEquals(plaintext, decrypted);
        assertEquals(message, new String(decrypted));
    }

    @Test
    public void testEmptyPlaintext() {
        byte[] empty = new byte[0];
        assertArrayEquals(empty, ChaCha20.encrypt(RFC8439_KEY, RFC8439_NONCE, empty));
        assertArrayEquals(empty, ChaCha20.decrypt(RFC8439_KEY, RFC8439_NONCE, empty));
    }

    @Test
    public void testInvalidKeySize() {
        byte[] nonce = new byte[12];
        byte[] data = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(new byte[31], nonce, data));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(new byte[33], nonce, data));
    }

    @Test
    public void testInvalidNonceSize() {
        byte[] key = new byte[32];
        byte[] data = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(key, new byte[11], data));
        assertThrows(IllegalArgumentException.class, () -> ChaCha20.encrypt(key, new byte[13], data));
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

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        if (len % 2 != 0) throw new IllegalArgumentException("Hex string must have even length");
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] =
                    (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
