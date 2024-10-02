package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public final class DiffieHellman {
    
    // Private constructor to prevent instantiation of utility class
    private DiffieHellman() {
        throw new UnsupportedOperationException("Utility class");
    }

    // Method to calculate public value (g^x mod p)
    public static BigInteger calculatePublicValue(BigInteger base, BigInteger secret, BigInteger prime) {
        // Returns g^x mod p
        return base.modPow(secret, prime);
    }

    // Method to calculate the shared secret key (otherPublic^secret mod p)
    public static BigInteger calculateSharedSecret(BigInteger otherPublicValue, BigInteger secret, BigInteger prime) {
        // Returns b^x mod p or a^y mod p
        return otherPublicValue.modPow(secret, prime);
    }

    // *********** Unit Test Section **************

    // Method to provide test data for public key calculation
    private static Stream<Arguments> providePublicKeyData() {
        return Stream.of(
            // base, secret, prime, expected public value
            Arguments.of(new BigInteger("5"), new BigInteger("6"), new BigInteger("23"), new BigInteger("8")),
            Arguments.of(new BigInteger("2"), new BigInteger("5"), new BigInteger("13"), new BigInteger("6"))
        );
    }

    // Test for public key calculation
    @ParameterizedTest
    @MethodSource("providePublicKeyData")
    public void testCalculatePublicValue(BigInteger base, BigInteger secret, BigInteger prime, BigInteger expected) {
        assertEquals(expected, DiffieHellman.calculatePublicValue(base, secret, prime));
    }

    // Method to provide test data for shared secret calculation
    private static Stream<Arguments> provideSharedSecretData() {
        return Stream.of(
            // otherPublic, secret, prime, expected shared secret
            Arguments.of(new BigInteger("8"), new BigInteger("6"), new BigInteger("23"), new BigInteger("2")),
            Arguments.of(new BigInteger("6"), new BigInteger("5"), new BigInteger("13"), new BigInteger("12"))
        );
    }

    // Test for shared secret calculation
    @ParameterizedTest
    @MethodSource("provideSharedSecretData")
    public void testCalculateSharedSecret(BigInteger otherPublicValue, BigInteger secret, BigInteger prime, BigInteger expected) {
        assertEquals(expected, DiffieHellman.calculateSharedSecret(otherPublicValue, secret, prime));
    }
}
