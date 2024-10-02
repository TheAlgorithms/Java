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
        return Stream.of(
            Arguments.of(new BigInteger("5"), new BigInteger("6"), new BigInteger("23"), new BigInteger("8")),
            Arguments.of(new BigInteger("2"), new BigInteger("5"), new BigInteger("13"), new BigInteger("6"))
        );
    }

    // Provide test data for shared secret calculation
    private static Stream<Arguments> provideSharedSecretData() {
        return Stream.of(
            Arguments.of(new BigInteger("8"), new BigInteger("6"), new BigInteger("23"), new BigInteger("2")),
            Arguments.of(new BigInteger("6"), new BigInteger("5"), new BigInteger("13"), new BigInteger("12"))
        );
    }
}
