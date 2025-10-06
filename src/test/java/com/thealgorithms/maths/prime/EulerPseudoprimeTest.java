package com.thealgorithms.maths.prime;

import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.maths.Prime.EulerPseudoprime;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class EulerPseudoprimeTest {

    @Test
    void testPrimeNumbers() {
        assertTrue(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(7), 10));
        assertTrue(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(13), 10));
        assertTrue(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(101), 10));
    }

    @Test
    void testCompositeNumbers() {
        assertFalse(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(9), 10));
        assertFalse(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(21), 10));
        assertFalse(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(221), 10));
    }

    @Test
    void testEvenNumbers() {
        assertFalse(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(4), 10));
        assertFalse(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(100), 10));
    }
}
