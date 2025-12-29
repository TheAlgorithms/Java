package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ExtendedEuclideanAlgorithmTest {

    /**
     * Verifies that the returned values satisfy Bézout's identity: a*x + b*y =
     * gcd(a, b)
     */
    private void verifyBezoutIdentity(long a, long b, long[] result) {
        long gcd = result[0];
        long x = result[1];
        long y = result[2];
        assertEquals(a * x + b * y, gcd, "Bézout's identity failed for gcd(" + a + ", " + b + ")");
    }

    @Test
    public void testExtendedGCD() {
        // Test case 1: General case gcd(30, 50) = 10
        long[] result1 = ExtendedEuclideanAlgorithm.extendedGCD(30, 50);
        assertEquals(10, result1[0], "Test Case 1 Failed: gcd(30, 50) should be 10");
        verifyBezoutIdentity(30, 50, result1);

        // Test case 2: Another general case gcd(240, 46) = 2
        long[] result2 = ExtendedEuclideanAlgorithm.extendedGCD(240, 46);
        assertEquals(2, result2[0], "Test Case 2 Failed: gcd(240, 46) should be 2");
        verifyBezoutIdentity(240, 46, result2);

        // Test case 3: Base case where b is 0, gcd(10, 0) = 10
        long[] result3 = ExtendedEuclideanAlgorithm.extendedGCD(10, 0);
        assertEquals(10, result3[0], "Test Case 3 Failed: gcd(10, 0) should be 10");
        verifyBezoutIdentity(10, 0, result3);

        // Test case 4: Numbers are co-prime gcd(17, 13) = 1
        long[] result4 = ExtendedEuclideanAlgorithm.extendedGCD(17, 13);
        assertEquals(1, result4[0], "Test Case 4 Failed: gcd(17, 13) should be 1");
        verifyBezoutIdentity(17, 13, result4);

        // Test case 5: One number is a multiple of the other gcd(100, 20) = 20
        long[] result5 = ExtendedEuclideanAlgorithm.extendedGCD(100, 20);
        assertEquals(20, result5[0], "Test Case 5 Failed: gcd(100, 20) should be 20");
        verifyBezoutIdentity(100, 20, result5);
    }
}
