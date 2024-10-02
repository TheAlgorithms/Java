package com.thealgorithms.ciphers;

import java.math.BigInteger;

public final class DiffieHellman {

    private final BigInteger base;
    private final BigInteger secret;
    private final BigInteger prime;

    // Constructor to initialize base, secret, and prime
    public DiffieHellman(BigInteger base, BigInteger secret, BigInteger prime) {
        // Check for non-null and positive values
        if (base == null || secret == null || prime == null || base.signum() <= 0 || secret.signum() <= 0 || prime.signum() <= 0) {
            throw new IllegalArgumentException("Base, secret, and prime must be non-null and positive values.");
        }
        this.base = base;
        this.secret = secret;
        this.prime = prime;
    }

    // Method to calculate public value (g^x mod p)
    public BigInteger calculatePublicValue() {
        // Returns g^x mod p
        return base.modPow(secret, prime);
    }

    // Method to calculate the shared secret key (otherPublic^secret mod p)
    public BigInteger calculateSharedSecret(BigInteger otherPublicValue) {
        if (otherPublicValue == null || otherPublicValue.signum() <= 0) {
            throw new IllegalArgumentException("Other public value must be non-null and positive.");
        }
        // Returns b^x mod p or a^y mod p
        return otherPublicValue.modPow(secret, prime);
    }

    // Static utility methods for direct calculation (if needed)
    public static BigInteger calculatePublicValue(BigInteger base, BigInteger secret, BigInteger prime) {
        // Check for valid inputs
        if (base == null || secret == null || prime == null || base.signum() <= 0 || secret.signum() <= 0 || prime.signum() <= 0) {
            throw new IllegalArgumentException("Base, secret, and prime must be non-null and positive values.");
        }
        return base.modPow(secret, prime);
    }

    public static BigInteger calculateSharedSecret(BigInteger otherPublicValue, BigInteger secret, BigInteger prime) {
        // Check for valid inputs
        if (otherPublicValue == null || secret == null || prime == null || otherPublicValue.signum() <= 0 || secret.signum() <= 0 || prime.signum() <= 0) {
            throw new IllegalArgumentException("Other public value, secret, and prime must be non-null and positive values.");
        }
        return otherPublicValue.modPow(secret, prime);
    }
}
