package com.thealgorithms.ciphers;

import java.math.BigInteger;

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
}
