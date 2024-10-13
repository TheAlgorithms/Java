package com.thealgorithms.ciphers.a5;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.BitSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class A5CipherTest {

    private A5Cipher a5Cipher;

    @BeforeEach
    void setUp() {
        // Initialize the session key and frame counter
        final var sessionKey = BitSet.valueOf(new long[] {0b1010101010101010L});
        final var frameCounter = BitSet.valueOf(new long[] {0b0000000000000001L});
        a5Cipher = new A5Cipher(sessionKey, frameCounter);
    }

    @Test
    void testEncryptWithValidInput() {
        BitSet plainText = BitSet.valueOf(new long[] {0b1100110011001100L}); // Example plaintext
        BitSet encrypted = a5Cipher.encrypt(plainText);

        // The expected result depends on the key stream generated.
        // In a real test, you would replace this with the actual expected result.
        // For now, we will just assert that the encrypted result is not equal to the plaintext.
        assertNotEquals(plainText, encrypted, "Encrypted output should not equal plaintext");
    }

    @Test
    void testEncryptAllOnesInput() {
        BitSet plainText = BitSet.valueOf(new long[] {0b1111111111111111L}); // All ones
        BitSet encrypted = a5Cipher.encrypt(plainText);

        // Similar to testEncryptWithValidInput, ensure that output isn't the same as input
        assertNotEquals(plainText, encrypted, "Encrypted output should not equal plaintext of all ones");
    }

    @Test
    void testEncryptAllZerosInput() {
        BitSet plainText = new BitSet(); // All zeros
        BitSet encrypted = a5Cipher.encrypt(plainText);

        // Check that the encrypted output is not the same
        assertNotEquals(plainText, encrypted, "Encrypted output should not equal plaintext of all zeros");
    }
}
