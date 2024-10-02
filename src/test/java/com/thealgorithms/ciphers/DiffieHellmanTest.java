package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiffieHellmanTest {

    // Test for public value calculation
    @ParameterizedTest
    @MethodSource("providePublicKeyData")
    public void testCalculatePublicValue(BigInteger base, BigInteger secret, BigInteger prime, BigInteger expected) {
        assertEquals(expected, DiffieHellman.calculatePublicValue(base, secret, prime));
    }

    // Test for shared secret calculation
    @ParameterizedTest
    @MethodSource("provideSharedSecretData")
    public void testCalculateSharedSecret(BigInteger otherPublicValue, BigInteger secret, BigInteger prime, BigInteger expected) {
        assertEquals(expected, DiffieHellman.calculateSharedSecret(otherPublicValue, secret, prime));
    }

    // Provide test data for public key calculation
    private static Stream<Arguments> providePublicKeyData() {
        // Corrected test cases
        return Stream.of(
            // g^x mod p, e.g., 5^6 mod 23 = 8
            Arguments.of(new BigInteger("5"), new BigInteger("6"), new BigInteger("23"), new BigInteger("8")),
            // 2^5 mod 13 = 6
            Arguments.of(new BigInteger("2"), new BigInteger("5"), new BigInteger("13"), new BigInteger("6"))
        );
    }

    // Provide test data for shared secret calculation
    private static Stream<Arguments> provideSharedSecretData() {
        // Corrected test cases
        return Stream.of(
            // b^x mod p, e.g., 8^6 mod 23 = 13
            Arguments.of(new BigInteger("8"), new BigInteger("6"), new BigInteger("23"), new BigInteger("13")),
            // 6^5 mod 13 = 2
            Arguments.of(new BigInteger("6"), new BigInteger("5"), new BigInteger("13"), new BigInteger("2"))
        );
    }
}
