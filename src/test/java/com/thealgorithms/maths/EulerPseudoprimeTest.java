package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

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
    void testIsProbablePrimeWhenJacobiSymbolIsZero() {
        try (MockedStatic<EulerPseudoprime> mockedPrimality = Mockito.mockStatic(EulerPseudoprime.class, Mockito.CALLS_REAL_METHODS)) {

            // Mock jacobiSymbol to return 0 to test the branch
            mockedPrimality.when(() -> EulerPseudoprime.jacobiSymbol(any(BigInteger.class), any(BigInteger.class))).thenReturn(0);

            boolean result = EulerPseudoprime.isProbablePrime(BigInteger.valueOf(15), 1);

            assertFalse(result);
        }
    }

    @Test
    void testJacobiSymbolThrowsForEvenOrNonPositiveN() throws Exception {
        var method = EulerPseudoprime.class.getDeclaredMethod("jacobiSymbol", BigInteger.class, BigInteger.class);

        // Helper lambda to unwrap InvocationTargetException
        Runnable invokeJacobi = () -> {
            try {
                method.invoke(null, BigInteger.valueOf(2), BigInteger.valueOf(8));
            } catch (Exception e) {
                // unwrap
                Throwable cause = e.getCause();
                if (cause instanceof IllegalArgumentException) {
                    throw (IllegalArgumentException) cause;
                } else {
                    throw new RuntimeException(e);
                }
            }
        };

        // Now check that it actually throws
        assertThrows(IllegalArgumentException.class, invokeJacobi::run);

        // Another case: non-positive n
        Runnable invokeJacobi2 = () -> {
            try {
                method.invoke(null, BigInteger.valueOf(5), BigInteger.valueOf(-3));
            } catch (Exception e) {
                Throwable cause = e.getCause();
                if (cause instanceof IllegalArgumentException) {
                    throw (IllegalArgumentException) cause;
                } else {
                    throw new RuntimeException(e);
                }
            }
        };
        assertThrows(IllegalArgumentException.class, invokeJacobi2::run);
    }
}
