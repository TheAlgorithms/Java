package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ElGamalCipherTest {

    @Test
    void testEncryptThenDecrypt_returnsOriginal() {
        ElGamalCipher elg = new ElGamalCipher();

        String original = "HelloElGamal123";
        String encrypted = elg.encrypt(original);
        assertNotNull(encrypted);

        String decrypted = elg.decrypt(encrypted);
        assertEquals(original, decrypted);
    }

    @Test
    void testEncryptedIsDifferentFromOriginal() {
        ElGamalCipher elg = new ElGamalCipher();
        String encrypted = elg.encrypt("TestData");
        assertNotEquals("TestData", encrypted);
    }

    @Test
    void testEmptyString() {
        ElGamalCipher elg = new ElGamalCipher();
        String encrypted = elg.encrypt("");
        String decrypted = elg.decrypt(encrypted);
        assertEquals("", decrypted);
    }

    @Test
    void testNullInputs() {
        ElGamalCipher elg = new ElGamalCipher();
        assertThrows(IllegalArgumentException.class, () -> elg.encrypt(null));
        assertThrows(IllegalArgumentException.class, () -> elg.decrypt(null));
    }
}
