package com.thealgorithms.ciphers;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link ElGamalEncryption}.
 */
public class ElGamalEncryptionTest {

    @Test
    void testEncryptionDecryption() {
        try {
            ElGamalEncryption.runElGamal("Hello", 64);
        } catch (Exception e) {
            throw new AssertionError("ElGamalEncryption failed with exception: " + e.getMessage());
        }
    }

    @Test
    void testUtilityConstructor() throws NoSuchMethodException {
        Constructor<ElGamalEncryption> constructor = ElGamalEncryption.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()),
                "Utility class constructor should be private");
    }
}
