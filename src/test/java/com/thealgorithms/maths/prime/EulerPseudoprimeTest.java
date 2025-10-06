package com.thealgorithms.maths.prime;

import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.maths.Prime.EulerPseudoprime;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class EulerPseudoprimeTest {

    @Test
    void testPrimeNumbers() {
        assertTrue(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(7), 5));
        assertTrue(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(13), 5));
        assertTrue(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(101), 5));
    }

    @Test
    void testCompositeNumbers() {
        assertFalse(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(9), 5));
        assertFalse(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(21), 5));
        assertFalse(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(221), 5));
    }

    @Test
    void testEvenNumbers() {
        assertFalse(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(4), 5));
        assertFalse(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(100), 5));
    }

    @Test
    void testEdgeCases() {
        assertFalse(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(0), 5));
        assertFalse(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(1), 5));
        assertTrue(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(2), 5));
        assertTrue(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(3), 5));
    }

    @Test
    void testSingleTrialConsistency() {
        BigInteger n = BigInteger.valueOf(97);
        boolean result1 = EulerPseudoprime.isProbablePrime(n, 1);
        boolean result2 = EulerPseudoprime.isProbablePrime(n, 1);
        assertEquals(result1, result2, "Results should be deterministic with constant seed");
    }

    @Test
    void testJacobiSymbolBasicCases() throws Exception {
        var method = EulerPseudoprime.class.getDeclaredMethod("jacobiSymbol", BigInteger.class, BigInteger.class);
        method.setAccessible(true);

        // (a/n) = (2/3) = -1
        assertEquals(-1, (int) method.invoke(null, BigInteger.valueOf(2), BigInteger.valueOf(3)));

        // (a/n) = (1/5) = 1
        assertEquals(1, (int) method.invoke(null, BigInteger.ONE, BigInteger.valueOf(5)));

        // (a/n) = (2/9) = 1
        assertEquals(1, (int) method.invoke(null, BigInteger.valueOf(2), BigInteger.valueOf(9)));

        // (a/n) = (3/9) = 0 since gcd(3,9)>1
        assertEquals(0, (int) method.invoke(null, BigInteger.valueOf(3), BigInteger.valueOf(9)));
    }

    @Test
    void testUniformRandomRange() throws Exception {
        var method = EulerPseudoprime.class.getDeclaredMethod("uniformRandom", BigInteger.class, BigInteger.class);
        method.setAccessible(true);

        BigInteger min = BigInteger.TWO;
        BigInteger max = BigInteger.valueOf(20);
        BigInteger value = (BigInteger) method.invoke(null, min, max);

        assertTrue(value.compareTo(min) >= 0 && value.compareTo(max) <= 0, "Random value should be within [min, max]");
    }

    @Test
    void testCompositeWithZeroJacobiSymbol() {
        // Choose n = 9, a = 3 → jacobi(3,9) = 0 should make it return false early
        assertFalse(EulerPseudoprime.isProbablePrime(BigInteger.valueOf(9), 1));
    }
}
