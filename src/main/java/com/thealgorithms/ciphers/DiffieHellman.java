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
}
