package com.thealgorithms.ciphers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * Implements the ChaCha20 stream cipher algorithm as specified in RFC 8439.
 * ChaCha20 is a refinement of the Salsa20 algorithm and is known for its
 * speed and security on modern CPUs.
 *
 * <p>Wikipedia: https://en.wikipedia.org/wiki/ChaCha20
 * <p>RFC 8439: https://tools.ietf.org/html/rfc8439
 *
 * @author Mitrajit Ghorui(KeyKyrios)
 */
public final class ChaCha20 {

    private ChaCha20() {
    } // Static class

    private static final int KEY_SIZE_BYTES = 32; // 256 bits
    private static final int NONCE_SIZE_BYTES = 12; // 96 bits
    private static final int BLOCK_SIZE_BYTES = 64; // 512 bits

    // ChaCha20 constants "expand 32-byte k"
    private static final int[] CONSTANTS = {0x61707865, 0x3320646e, 0x79622d32, 0x6b206574};

    /**
     * Encrypts the given plaintext using ChaCha20.
     * Since ChaCha20 is a stream cipher, encryption and decryption are the same
     * operation (XOR with keystream).
     *
     * @param key       The 256-bit (32-byte) secret key.
     * @param nonce     The 96-bit (12-byte) nonce. Must be unique for each
     * encryption with the same key.
     * @param plaintext The data to encrypt.
     * @return The resulting ciphertext.
     * @throws IllegalArgumentException if the key or nonce has an invalid length,
     * or if any input is null.
     */
    public static byte[] encrypt(final byte[] key, final byte[] nonce, final byte[] plaintext) {
        validateInputs(key, nonce, plaintext);
        return process(key, nonce, plaintext, 1); // Start with block counter 1 as per RFC 8439
    }

    /**
     * Decrypts the given ciphertext using ChaCha20.
     * Since ChaCha20 is a stream cipher, encryption and decryption are the same
     * operation (XOR with keystream).
     *
     * @param key        The 256-bit (32-byte) secret key.
     * @param nonce      The 96-bit (12-byte) nonce used during encryption.
     * @param ciphertext The data to decrypt.
     * @return The resulting plaintext.
     * @throws IllegalArgumentException if the key or nonce has an invalid length,
     * or if any input is null.
     */
    public static byte[] decrypt(final byte[] key, final byte[] nonce, final byte[] ciphertext) {
        validateInputs(key, nonce, ciphertext);
        return process(key, nonce, ciphertext, 1); // Start with block counter 1
    }

    /**
     * Performs the core ChaCha20 processing (XOR with keystream).
     *
     * @param key     The 32-byte key.
     * @param nonce   The 12-byte nonce.
     * @param data    Plaintext or Ciphertext.
     * @param counter The initial block counter.
     * @return The result of XORing data with the generated keystream.
     */
    private static byte[] process(final byte[] key, final byte[] nonce, final byte[] data, final int counter) {
        byte[] output = new byte[data.length];
        ByteBuffer keyStreamBlock = ByteBuffer.allocate(BLOCK_SIZE_BYTES).order(ByteOrder.LITTLE_ENDIAN);
        int offset = 0;
        int blockCounter = counter;

        while (offset < data.length) {
            keyStreamBlock.clear();
            generateChaCha20Block(key, nonce, blockCounter++, keyStreamBlock.array());

            int length = Math.min(BLOCK_SIZE_BYTES, data.length - offset);
            for (int i = 0; i < length; i++) {
                output[offset + i] = (byte) (data[offset + i] ^ keyStreamBlock.get(i));
            }
            offset += length;
        }
        return output;
    }

    /**
     * Generates a 64-byte ChaCha20 keystream block.
     *
     * @param key     The 32-byte key.
     * @param nonce   The 12-byte nonce.
     * @param counter The block counter.
     * @param output  The 64-byte array to store the generated block.
     */
    private static void generateChaCha20Block(final byte[] key, final byte[] nonce, final int counter, final byte[] output) {
        int[] state = initializeState(key, nonce, counter);
        int[] workingState = Arrays.copyOf(state, state.length);

        // 20 rounds (10 double rounds)
        for (int i = 0; i < 10; i++) {
            // Column rounds
            quarterRound(workingState, 0, 4, 8, 12);
            quarterRound(workingState, 1, 5, 9, 13);
            quarterRound(workingState, 2, 6, 10, 14);
            quarterRound(workingState, 3, 7, 11, 15);
            // Diagonal rounds
            quarterRound(workingState, 0, 5, 10, 15);
            quarterRound(workingState, 1, 6, 11, 12);
            quarterRound(workingState, 2, 7, 8, 13);
            quarterRound(workingState, 3, 4, 9, 14);
        }

        // Add initial state to the final state
        for (int i = 0; i < state.length; i++) {
            workingState[i] += state[i];
        }

        // Serialize state to output bytes (Little Endian)
        ByteBuffer buffer = ByteBuffer.wrap(output).order(ByteOrder.LITTLE_ENDIAN);
        for (int val : workingState) {
            buffer.putInt(val);
        }
    }

    /**
     * Initializes the 16-word (512-bit) ChaCha20 state.
     */
    private static int[] initializeState(final byte[] key, final byte[] nonce, final int counter) {
        int[] state = new int[16];
        ByteBuffer keyBuffer = ByteBuffer.wrap(key).order(ByteOrder.LITTLE_ENDIAN);
        ByteBuffer nonceBuffer = ByteBuffer.wrap(nonce).order(ByteOrder.LITTLE_ENDIAN);

        // Constants
        state[0] = CONSTANTS[0];
        state[1] = CONSTANTS[1];
        state[2] = CONSTANTS[2];
        state[3] = CONSTANTS[3];

        // Key (8 words)
        for (int i = 0; i < 8; i++) {
            state[4 + i] = keyBuffer.getInt(i * 4);
        }

        // Counter (1 word)
        state[12] = counter;

        // Nonce (3 words)
        for (int i = 0; i < 3; i++) {
            state[13 + i] = nonceBuffer.getInt(i * 4);
        }

        return state;
    }

    /**
     * The ChaCha20 quarter round function. Modifies the state array in place.
     */
    private static void quarterRound(final int[] state, final int a, final int b, final int c, final int d) {
        state[a] += state[b];
        state[d] = rotl(state[d] ^ state[a], 16);
        state[c] += state[d];
        state[b] = rotl(state[b] ^ state[c], 12);
        state[a] += state[b];
        state[d] = rotl(state[d] ^ state[a], 8);
        state[c] += state[d];
        state[b] = rotl(state[b] ^ state[c], 7);
    }

    /**
     * Rotates the bits of an integer to the left.
     */
    private static int rotl(final int value, final int shift) {
        return (value << shift) | (value >>> (32 - shift));
    }

    /**
     * Validates key, nonce, and data inputs.
     */
    private static void validateInputs(final byte[] key, final byte[] nonce, final byte[] data) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        if (key.length != KEY_SIZE_BYTES) {
            throw new IllegalArgumentException("Invalid key size. Key must be " + KEY_SIZE_BYTES + " bytes (256 bits).");
        }
        if (nonce == null) {
            throw new IllegalArgumentException("Nonce cannot be null.");
        }
        if (nonce.length != NONCE_SIZE_BYTES) {
            throw new IllegalArgumentException("Invalid nonce size. Nonce must be " + NONCE_SIZE_BYTES + " bytes (96 bits).");
        }
        if (data == null) {
            throw new IllegalArgumentException("Plaintext/Ciphertext cannot be null.");
        }
    }
}
