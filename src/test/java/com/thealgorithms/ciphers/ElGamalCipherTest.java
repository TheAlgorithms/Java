package com.thealgorithms.ciphers;

import java.math.BigInteger;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for ElGamalCipher.
 * Includes property-based testing (homomorphism), probabilistic checks,
 * and boundary validation.
 */
class ElGamalCipherTest {

    private static ElGamalCipher.KeyPair sharedKeys;

    @BeforeAll
    static void setup() {
        // Generate 256-bit keys for efficient unit testing
        sharedKeys = ElGamalCipher.generateKeys(256);
    }

    @Test
    @DisplayName("Test Key Generation Validity")
    void testKeyGeneration() {
        Assertions.assertNotNull(sharedKeys.p());
        Assertions.assertNotNull(sharedKeys.g());
        Assertions.assertNotNull(sharedKeys.x());
        Assertions.assertNotNull(sharedKeys.y());

        // Verify generator bounds: 1 < g < p
        Assertions.assertTrue(sharedKeys.g().compareTo(BigInteger.ONE) > 0);
        Assertions.assertTrue(sharedKeys.g().compareTo(sharedKeys.p()) < 0);

        // Verify private key bounds: 1 < x < p-1
        Assertions.assertTrue(sharedKeys.x().compareTo(BigInteger.ONE) > 0);
        Assertions.assertTrue(sharedKeys.x().compareTo(sharedKeys.p().subtract(BigInteger.ONE)) < 0);
    }

    @Test
    @DisplayName("Security Check: Probabilistic Encryption")
    void testSemanticSecurity() {
        // Encrypting the same message twice MUST yield different ciphertexts
        // due to the random ephemeral key 'k'.
        BigInteger message = new BigInteger("123456789");

        ElGamalCipher.CipherText c1 = ElGamalCipher.encrypt(message, sharedKeys.p(), sharedKeys.g(), sharedKeys.y());
        ElGamalCipher.CipherText c2 = ElGamalCipher.encrypt(message, sharedKeys.p(), sharedKeys.g(), sharedKeys.y());

        // Check that the ephemeral keys (and thus 'a' components) were different
        Assertions.assertNotEquals(c1.a(), c2.a(), "Ciphertexts must be randomized (Semantic Security violation)");
        Assertions.assertNotEquals(c1.b(), c2.b());

        // But both must decrypt to the original message
        Assertions.assertEquals(ElGamalCipher.decrypt(c1, sharedKeys.x(), sharedKeys.p()), message);
        Assertions.assertEquals(ElGamalCipher.decrypt(c2, sharedKeys.x(), sharedKeys.p()), message);
    }

    @ParameterizedTest
    @MethodSource("provideMessages")
    @DisplayName("Parameterized Test: Encrypt and Decrypt various messages")
    void testEncryptDecrypt(String messageStr) {
        BigInteger message = new BigInteger(messageStr.getBytes());

        // Skip if message exceeds the test key size (256 bits)
        if (message.compareTo(sharedKeys.p()) >= 0) {
            return;
        }

        ElGamalCipher.CipherText ciphertext = ElGamalCipher.encrypt(message, sharedKeys.p(), sharedKeys.g(), sharedKeys.y());
        BigInteger decrypted = ElGamalCipher.decrypt(ciphertext, sharedKeys.x(), sharedKeys.p());

        Assertions.assertEquals(message, decrypted, "Decrypted BigInteger must match original");
        Assertions.assertEquals(messageStr, new String(decrypted.toByteArray()), "Decrypted string must match original");
    }

    static Stream<String> provideMessages() {
        return Stream.of("Hello World", "TheAlgorithms", "A", "1234567890", "!@#$%^&*()");
    }

    @Test
    @DisplayName("Edge Case: Message equals 0")
    void testMessageZero() {
        BigInteger zero = BigInteger.ZERO;
        ElGamalCipher.CipherText ciphertext = ElGamalCipher.encrypt(zero, sharedKeys.p(), sharedKeys.g(), sharedKeys.y());
        BigInteger decrypted = ElGamalCipher.decrypt(ciphertext, sharedKeys.x(), sharedKeys.p());

        Assertions.assertEquals(zero, decrypted, "Should successfully encrypt/decrypt zero");
    }

    @Test
    @DisplayName("Edge Case: Message equals p-1")
    void testMessageMaxBound() {
        BigInteger pMinus1 = sharedKeys.p().subtract(BigInteger.ONE);
        ElGamalCipher.CipherText ciphertext = ElGamalCipher.encrypt(pMinus1, sharedKeys.p(), sharedKeys.g(), sharedKeys.y());
        BigInteger decrypted = ElGamalCipher.decrypt(ciphertext, sharedKeys.x(), sharedKeys.p());

        Assertions.assertEquals(pMinus1, decrypted, "Should successfully encrypt/decrypt p-1");
    }

    @Test
    @DisplayName("Negative Test: Message >= p should fail")
    void testMessageTooLarge() {
        BigInteger tooLarge = sharedKeys.p();
        Assertions.assertThrows(IllegalArgumentException.class, () -> ElGamalCipher.encrypt(tooLarge, sharedKeys.p(), sharedKeys.g(), sharedKeys.y()));
    }

    @Test
    @DisplayName("Negative Test: Decrypt with wrong private key")
    void testWrongKeyDecryption() {
        BigInteger message = new BigInteger("99999");
        ElGamalCipher.CipherText ciphertext = ElGamalCipher.encrypt(message, sharedKeys.p(), sharedKeys.g(), sharedKeys.y());

        // Generate a fake private key
        BigInteger wrongX = sharedKeys.x().add(BigInteger.ONE);

        BigInteger decrypted = ElGamalCipher.decrypt(ciphertext, wrongX, sharedKeys.p());

        Assertions.assertNotEquals(message, decrypted, "Decryption with wrong key must yield incorrect result");
    }

    @Test
    @DisplayName("Property Test: Multiplicative Homomorphism")
    void testHomomorphism() {
        BigInteger m1 = new BigInteger("50");
        BigInteger m2 = BigInteger.TEN; // Fix: Replaced new BigInteger("10") with BigInteger.TEN

        ElGamalCipher.CipherText c1 = ElGamalCipher.encrypt(m1, sharedKeys.p(), sharedKeys.g(), sharedKeys.y());
        ElGamalCipher.CipherText c2 = ElGamalCipher.encrypt(m2, sharedKeys.p(), sharedKeys.g(), sharedKeys.y());

        // Multiply ciphertexts component-wise: (a1*a2, b1*b2)
        BigInteger aNew = c1.a().multiply(c2.a()).mod(sharedKeys.p());
        BigInteger bNew = c1.b().multiply(c2.b()).mod(sharedKeys.p());
        ElGamalCipher.CipherText cCombined = new ElGamalCipher.CipherText(aNew, bNew);

        BigInteger decrypted = ElGamalCipher.decrypt(cCombined, sharedKeys.x(), sharedKeys.p());
        BigInteger expected = m1.multiply(m2).mod(sharedKeys.p());

        Assertions.assertEquals(expected, decrypted, "Cipher must satisfy multiplicative homomorphism");
    }
}
