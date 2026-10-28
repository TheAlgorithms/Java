package com.thealgorithms.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class SylvesterSequenceTest {

    /**
     * Provides test cases for valid Sylvester sequence numbers.
     * Format: { n, expectedValue }
     */
    static Stream<Object[]> validSylvesterNumbers() {
        return Stream.of(new Object[] {1, BigInteger.valueOf(2)}, new Object[] {2, BigInteger.valueOf(3)}, new Object[] {3, BigInteger.valueOf(7)}, new Object[] {4, BigInteger.valueOf(43)}, new Object[] {5, BigInteger.valueOf(1807)}, new Object[] {6, new BigInteger("3263443")},
            new Object[] {7, new BigInteger("10650056950807")}, new Object[] {8, new BigInteger("113423713055421844361000443")});
    }

    @ParameterizedTest
    @MethodSource("validSylvesterNumbers")
    void testSylvesterValidNumbers(int n, BigInteger expected) {
        assertEquals(expected, SylvesterSequence.sylvester(n), "Sylvester sequence value mismatch for n=" + n);
    }

    /**
     * Test edge case for n <= 0 which should throw IllegalArgumentException
     */
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10, -100})
    void testSylvesterInvalidZero(int n) {
        assertThrows(IllegalArgumentException.class, () -> SylvesterSequence.sylvester(n));
    }

    /**
     * Test a larger number to ensure no overflow occurs.
     */
    @Test
    void testSylvesterLargeNumber() {
        int n = 10;
        BigInteger result = SylvesterSequence.sylvester(n);
        assertNotNull(result);
        assertTrue(result.compareTo(BigInteger.ZERO) > 0, "Result should be positive");
    }
}
