package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class DiffieHellmanTest {

    @Test
    void testDiffieHellmanSharedKey() {
        BigInteger p = new BigInteger("23");
        BigInteger g = new BigInteger("5");
        BigInteger a = new BigInteger("6"); // Private key for Alice
        BigInteger b = new BigInteger("15"); // Private key for Bob

        DiffieHellman alice = new DiffieHellman(p, g, a);
        DiffieHellman bob = new DiffieHellman(p, g, b);

        BigInteger A = alice.getPublicKey();
        BigInteger B = bob.getPublicKey();

        BigInteger aliceSharedKey = alice.computeSharedKey(B);
        BigInteger bobSharedKey = bob.computeSharedKey(A);

        assertEquals(aliceSharedKey, bobSharedKey, "Shared keys do not match!");
    }
}
