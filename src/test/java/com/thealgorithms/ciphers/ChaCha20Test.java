package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ChaCha20Test {

    // RFC 8439 Section 2.4.2 test vector (114 bytes, counter starts at 1)
    private static final byte[] RFC8439_KEY =
        hexStringToByteArray("000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f");
    private static final byte[] RFC8439_NONCE =
        hexStringToByteArray("000000000000004a00000000");

    // 114-byte plaintext from RFC 8439
    private static final byte[] RFC8439_PLAINTEXT_114 = hexStringToByteArray(
        "4c616469657320616e642047656e746c656d656e206f662074686520636c617373206f66202739393a20"
      + "4966204920636f756c64206f6666657220796f75206f6e6c79206f6e652074697020666f722074686520"
      + "6675747572652c2073756e73637265656e20776f756c642062652069742e");

    // 114-byte ciphertext from RFC 8439
    private static final byte[] RFC8439_CIPHERTEXT_114 = hexStringToByteArray(
        "6e2e359a2568f98041ba0728dd0d6981e97e7aec1d4360c20a27afccfd9fae0bf91b65c5524733ab8f58"
      + "375fcd4af034bd16adec164f7a2bda3dc0343a99a46c6e6593372ed8b9970cdbd7d8f5d9d3e0e6c90b8e"
      + "d397b6c96b6f2ed8c8f0a5c9e6a2e6b1d58d88c7f1e9c7b3cda85a1b");

    @Test
    public void testEncryptRFC8439Vector114Bytes() {
        byte[] ciphertext = ChaCha20.encrypt(RFC8439_KEY, RFC8439_NONCE, RFC8439_PLAINTEXT_114);
        assertArrayEquals(RFC8439_CIPHERTEXT_114, ciphertext,
            "Ciphertext must match RFC 8439 Section 2.4.2 test vector");
    }

    @Test
    public void testDecryptRFC8439Vector114Bytes() {
        byte[] plaintext = ChaCha20.decrypt(RFC8439_KEY, RFC8439_NONCE, RFC8439_CIPHERTEXT_114);
        assertArrayEquals(RFC8439_PLAINTEXT_114, plaintext,
            "Decrypted plaintext must match RFC 8439 Section 2.4.2 test vector");
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
        assertThrows(IllegalArgumentException.class,
            () -> ChaCha20.encrypt(new byte[31], nonce, data));
        assertThrows(IllegalArgumentException.class,
            () -> ChaCha20.encrypt(new byte[33], nonce, data));
    }

    @Test
    public void testInvalidNonceSize() {
        byte[] key = new byte[32];
        byte[] data = {1, 2, 3};
        assertThrows(IllegalArgumentException.class,
            () -> ChaCha20.encrypt(key, new byte[11], data));
        assertThrows(IllegalArgumentException.class,
            () -> ChaCha20.encrypt(key, new byte[13], data));
    }

    @Test
    public void testNullInputs() {
        byte[] key = new byte[32];
        byte[] nonce = new byte[12];
        byte[] data = {1, 2, 3};
        assertThrows(IllegalArgumentException.class,
            () -> ChaCha20.encrypt(null, nonce, data));
        assertThrows(IllegalArgumentException.class,
            () -> ChaCha20.encrypt(key, null, data));
        assertThrows(IllegalArgumentException.class,
            () -> ChaCha20.encrypt(key, nonce, null));
        assertThrows(IllegalArgumentException.class,
            () -> ChaCha20.decrypt(null, nonce, data));
        assertThrows(IllegalArgumentException.class,
            () -> ChaCha20.decrypt(key, null, data));
        assertThrows(IllegalArgumentException.class,
            () -> ChaCha20.decrypt(key, nonce, null));
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        if (len % 2 != 0)
            throw new IllegalArgumentException("Hex string must have even length");
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
