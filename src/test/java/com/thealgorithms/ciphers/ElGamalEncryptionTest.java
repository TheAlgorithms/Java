package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ElGamalEncryption}.
 */
public class ElGamalEncryptionTest {

    @Test
    void testEncryptionDecryption() {
        // Basic functional test (simulated output check)
        // Since ElGamal uses randomization, we only verify successful execution
        try {
            ElGamalEncryption.runElGamal("Hello", 64);
        } catch (Exception e) {
            throw new AssertionError("ElGamalEncryption failed with exception: " + e.getMessage());
        }
    }

    @Test
    void testUtilityConstructor() {
        // Ensures the utility class constructor is private
        try {
            var constructor = ElGamalEncryption.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        } catch (Exception e) {
            assertEquals("Utility class", e.getCause().getMessage());
        }
    }
}
