/*
 * TheAlgorithms (https://github.com/TheAlgorithms/Java)
 * Author: Shewale41
 * This file is licensed under the MIT License.
 */

package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link ElGamalEncryption}.
 */
public class ElGamalEncryptionTest {

    @Test
    void testEncryptionDecryption() {
        String message = "Hello";
        assertDoesNotThrow(() -> ElGamalEncryption.runElGamal(message, 32));
    }

    @Test
    void testWithDifferentBitLengths() {
        assertDoesNotThrow(() -> ElGamalEncryption.runElGamal("Test", 16));
        assertDoesNotThrow(() -> ElGamalEncryption.runElGamal("Secure", 64));
    }
}
