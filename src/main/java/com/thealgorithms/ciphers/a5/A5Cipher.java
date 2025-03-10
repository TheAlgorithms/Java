package com.thealgorithms.ciphers.a5;

import java.util.BitSet;

/**
 * The A5Cipher class implements the A5/1 stream cipher, which is a widely used
 * encryption algorithm, particularly in mobile communications.
 *
 * This implementation uses a key stream generator to produce a stream of bits
 * that are XORed with the plaintext bits to produce the ciphertext.
 *
 * <p>
 * For more details about the A5/1 algorithm, refer to
 * <a href="https://en.wikipedia.org/wiki/A5/1">Wikipedia</a>.
 * </p>
 */
public class A5Cipher {

    private final A5KeyStreamGenerator keyStreamGenerator;
    private static final int KEY_STREAM_LENGTH = 228; // Length of the key stream in bits (28.5 bytes)

    /**
     * Constructs an A5Cipher instance with the specified session key and frame counter.
     *
     * @param sessionKey a BitSet representing the session key used for encryption.
     * @param frameCounter a BitSet representing the frame counter that helps in key stream generation.
     */
    public A5Cipher(BitSet sessionKey, BitSet frameCounter) {
        keyStreamGenerator = new A5KeyStreamGenerator();
        keyStreamGenerator.initialize(sessionKey, frameCounter);
    }

    /**
     * Encrypts the given plaintext bits using the A5/1 cipher algorithm.
     *
     * This method generates a key stream and XORs it with the provided plaintext
     * bits to produce the ciphertext.
     *
     * @param plainTextBits a BitSet representing the plaintext bits to be encrypted.
     * @return a BitSet containing the encrypted ciphertext bits.
     */
    public BitSet encrypt(BitSet plainTextBits) {
        // create a copy
        var result = new BitSet(KEY_STREAM_LENGTH);
        result.xor(plainTextBits);

        var key = keyStreamGenerator.getNextKeyStream();
        result.xor(key);

        return result;
    }

    /**
     * Resets the internal counter of the key stream generator.
     *
     * This method can be called to re-initialize the state of the key stream
     * generator, allowing for new key streams to be generated for subsequent
     * encryptions.
     */
    public void resetCounter() {
        keyStreamGenerator.reInitialize();
    }
}
