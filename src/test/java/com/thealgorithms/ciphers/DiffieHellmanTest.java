package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiffieHellmanTest {

    // Test for public value calculation using instance methods
    @ParameterizedTest
    @MethodSource("providePublicKeyData")
    public void testCalculatePublicValue(BigInteger base, BigInteger secret, BigInteger prime, BigInteger expected) {
        DiffieHellman dh = new DiffieHellman(base, secret, prime); // Create an instance of DiffieHellman
        assertEquals(expected, dh.calculatePublicValue()); // Call instance method
    }

    // Test for shared secret calculation using instance methods
    @ParameterizedTest
    @MethodSource("provideSharedSecretData")
    public void testCalculateSharedSecret(BigInteger base, BigInteger secret, BigInteger prime, BigInteger otherPublicValue, BigInteger expected) {
        DiffieHellman dh = new DiffieHellman(base, secret, prime); // Create an instance of DiffieHellman
        assertEquals(expected, dh.calculateSharedSecret(otherPublicValue)); // Call instance method
    }

    // Provide test data for public key calculation
    private static Stream<Arguments> providePublicKeyData() {
        return Stream.of(Arguments.of(new BigInteger("5"), new BigInteger("6"), new BigInteger("23"), new BigInteger("8")), Arguments.of(new BigInteger("2"), new BigInteger("5"), new BigInteger("13"), new BigInteger("6")));
    }

    // Provide test data for shared secret calculation
    private static Stream<Arguments> provideSharedSecretData() {
        return Stream.of(Arguments.of(new BigInteger("5"), new BigInteger("6"), new BigInteger("23"), new BigInteger("8"), new BigInteger("13")), Arguments.of(new BigInteger("2"), new BigInteger("5"), new BigInteger("13"), new BigInteger("6"), new BigInteger("2")));
    }
}
