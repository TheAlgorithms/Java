package com.thealgorithms.ciphers;

import java.util.Objects;

/**
 * Implements the ChaCha20 stream cipher described in RFC 8439.
 *
 * <p>This implementation is intended for educational purposes and should not
 * be used as a replacement for a vetted cryptographic library in production.
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc8439.html">RFC 8439</a>
 */
public final class ChaCha20 {

    private static final int BLOCK_SIZE = 64;
    private static final long MAX_COUNTER = 0xFFFF_FFFFL;
    private static final int[] CONSTANTS = {0x61707865, 0x3320646e, 0x79622d32, 0x6b206574};

    private ChaCha20() {
        // Utility class
    }

    /**
     * Encrypts bytes with ChaCha20. Decryption uses the same operation.
     *
     * @param input plaintext bytes; must not be {@code null}
     * @param key 32-byte key
     * @param nonce 12-byte nonce
     * @param initialCounter unsigned 32-bit initial block counter
     * @return encrypted bytes
     * @throws NullPointerException if an argument is {@code null}
     * @throws IllegalArgumentException if a key, nonce, or counter is invalid,
     *     or the counter would overflow while processing the input
     */
    public static byte[] encrypt(byte[] input, byte[] key, byte[] nonce, long initialCounter) {
        validateInputs(input, key, nonce, initialCounter);

        long blockCount = (input.length + (long) BLOCK_SIZE - 1) / BLOCK_SIZE;
        if (blockCount > 0 && initialCounter > MAX_COUNTER - (blockCount - 1)) {
            throw new IllegalArgumentException("Counter would overflow");
        }

        byte[] output = new byte[input.length];
        for (int offset = 0; offset < input.length; offset += BLOCK_SIZE) {
            long counter = initialCounter + offset / BLOCK_SIZE;
            byte[] keyStream = block(key, nonce, counter);
            int bytesToProcess = Math.min(BLOCK_SIZE, input.length - offset);

            for (int i = 0; i < bytesToProcess; i++) {
                output[offset + i] = (byte) (input[offset + i] ^ keyStream[i]);
            }
        }
        return output;
    }

    /**
     * Decrypts bytes with ChaCha20. This is identical to encryption.
     *
     * @param input ciphertext bytes; must not be {@code null}
     * @param key 32-byte key
     * @param nonce 12-byte nonce
     * @param initialCounter unsigned 32-bit initial block counter
     * @return decrypted bytes
     */
    public static byte[] decrypt(byte[] input, byte[] key, byte[] nonce, long initialCounter) {
        return encrypt(input, key, nonce, initialCounter);
    }

    private static void validateInputs(byte[] input, byte[] key, byte[] nonce, long counter) {
        Objects.requireNonNull(input, "input must not be null");
        Objects.requireNonNull(key, "key must not be null");
        Objects.requireNonNull(nonce, "nonce must not be null");

        if (key.length != 32) {
            throw new IllegalArgumentException("Key must be 32 bytes");
        }
        if (nonce.length != 12) {
            throw new IllegalArgumentException("Nonce must be 12 bytes");
        }
        if (counter < 0 || counter > MAX_COUNTER) {
            throw new IllegalArgumentException("Counter must be an unsigned 32-bit value");
        }
    }

    private static byte[] block(byte[] key, byte[] nonce, long counter) {
        int[] state = new int[16];
        System.arraycopy(CONSTANTS, 0, state, 0, CONSTANTS.length);

        for (int i = 0; i < 8; i++) {
            state[4 + i] = littleEndianInt(key, i * 4);
        }

        state[12] = (int) counter;
        state[13] = littleEndianInt(nonce, 0);
        state[14] = littleEndianInt(nonce, 4);
        state[15] = littleEndianInt(nonce, 8);

        int[] workingState = state.clone();
        for (int i = 0; i < 10; i++) {
            quarterRound(workingState, 0, 4, 8, 12);
            quarterRound(workingState, 1, 5, 9, 13);
            quarterRound(workingState, 2, 6, 10, 14);
            quarterRound(workingState, 3, 7, 11, 15);

            quarterRound(workingState, 0, 5, 10, 15);
            quarterRound(workingState, 1, 6, 11, 12);
            quarterRound(workingState, 2, 7, 8, 13);
            quarterRound(workingState, 3, 4, 9, 14);
        }

        byte[] output = new byte[BLOCK_SIZE];
        for (int i = 0; i < workingState.length; i++) {
            int word = workingState[i] + state[i];
            output[i * 4] = (byte) word;
            output[i * 4 + 1] = (byte) (word >>> 8);
            output[i * 4 + 2] = (byte) (word >>> 16);
            output[i * 4 + 3] = (byte) (word >>> 24);
        }
        return output;
    }

    static void quarterRound(int[] state, int a, int b, int c, int d) {
        state[a] += state[b];
        state[d] = Integer.rotateLeft(state[d] ^ state[a], 16);
        state[c] += state[d];
        state[b] = Integer.rotateLeft(state[b] ^ state[c], 12);
        state[a] += state[b];
        state[d] = Integer.rotateLeft(state[d] ^ state[a], 8);
        state[c] += state[d];
        state[b] = Integer.rotateLeft(state[b] ^ state[c], 7);
    }

    private static int littleEndianInt(byte[] bytes, int offset) {
        return (bytes[offset] & 0xFF) | ((bytes[offset + 1] & 0xFF) << 8) | ((bytes[offset + 2] & 0xFF) << 16) | ((bytes[offset + 3] & 0xFF) << 24);
    }
}
