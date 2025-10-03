package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Comprehensive test suite for ExtendedEuclideanAlgorithm.
 * Tests the correctness of extended GCD computation, modular inverse,
 * and Diophantine equation solving.
 * 
 * @author TheAlgorithms Contributors
 */
class ExtendedEuclideanAlgorithmTest {

    @Test
    void testBasicExtendedGcd() {
        ExtendedEuclideanAlgorithm.ExtendedGcdResult result = 
            ExtendedEuclideanAlgorithm.extendedGcd(30, 18);
        
        assertEquals(6, result.getGcd(), "GCD of 30 and 18 should be 6");
        assertTrue(result.verify(30, 18), "Result should satisfy the equation 30x + 18y = gcd");
        assertEquals(30 * result.getX() + 18 * result.getY(), result.getGcd(), 
                     "Bézout's identity should hold");
    }

    @Test
    void testExtendedGcdWithZero() {
        ExtendedEuclideanAlgorithm.ExtendedGcdResult result1 = 
            ExtendedEuclideanAlgorithm.extendedGcd(42, 0);
        assertEquals(42, result1.getGcd(), "GCD of 42 and 0 should be 42");
        assertEquals(1, result1.getX(), "Coefficient x should be 1");
        assertEquals(0, result1.getY(), "Coefficient y should be 0");
        assertTrue(result1.verify(42, 0), "Result should verify correctly");

        ExtendedEuclideanAlgorithm.ExtendedGcdResult result2 = 
            ExtendedEuclideanAlgorithm.extendedGcd(0, 42);
        assertEquals(42, result2.getGcd(), "GCD of 0 and 42 should be 42");
        assertEquals(0, result2.getX(), "Coefficient x should be 0");
        assertEquals(1, result2.getY(), "Coefficient y should be 1");
        assertTrue(result2.verify(0, 42), "Result should verify correctly");
    }

    @Test
    void testExtendedGcdWithNegativeNumbers() {
        ExtendedEuclideanAlgorithm.ExtendedGcdResult result = 
            ExtendedEuclideanAlgorithm.extendedGcd(-30, 18);
        
        assertEquals(6, result.getGcd(), "GCD should be positive regardless of input signs");
        assertTrue(result.verify(-30, 18), "Result should satisfy the equation -30x + 18y = gcd");
    }

    @Test
    void testExtendedGcdCoprimeNumbers() {
        ExtendedEuclideanAlgorithm.ExtendedGcdResult result = 
            ExtendedEuclideanAlgorithm.extendedGcd(17, 13);
        
        assertEquals(1, result.getGcd(), "GCD of coprime numbers should be 1");
        assertTrue(result.verify(17, 13), "Result should satisfy the equation 17x + 13y = 1");
    }

    @ParameterizedTest
    @CsvSource({
        "30, 18, 6",
        "48, 18, 6", 
        "17, 13, 1",
        "100, 25, 25",
        "1071, 462, 21",
        "7, 3, 1"
    })
    void testExtendedGcdParameterized(long a, long b, long expectedGcd) {
        ExtendedEuclideanAlgorithm.ExtendedGcdResult result = 
            ExtendedEuclideanAlgorithm.extendedGcd(a, b);
        
        assertEquals(expectedGcd, result.getGcd(), 
                     String.format("GCD of %d and %d should be %d", a, b, expectedGcd));
        assertTrue(result.verify(a, b), 
                   String.format("Result should satisfy %dx + %dy = %d", a, b, expectedGcd));
    }

    @Test
    void testExtendedGcdIterativeVsRecursive() {
        long[][] testCases = {
            {30, 18}, {48, 18}, {17, 13}, {100, 25}, {1071, 462}, {7, 3},
            {-30, 18}, {30, -18}, {-30, -18}, {0, 42}, {42, 0}
        };
        
        for (long[] testCase : testCases) {
            ExtendedEuclideanAlgorithm.ExtendedGcdResult recursive = 
                ExtendedEuclideanAlgorithm.extendedGcd(testCase[0], testCase[1]);
            ExtendedEuclideanAlgorithm.ExtendedGcdResult iterative = 
                ExtendedEuclideanAlgorithm.extendedGcdIterative(testCase[0], testCase[1]);
            
            assertEquals(recursive.getGcd(), iterative.getGcd(), 
                        "Recursive and iterative should produce same GCD");
            assertTrue(recursive.verify(testCase[0], testCase[1]), 
                      "Recursive result should verify");
            assertTrue(iterative.verify(testCase[0], testCase[1]), 
                      "Iterative result should verify");
        }
    }

    @Test
    void testModularInverse() {
        long inverse = ExtendedEuclideanAlgorithm.modularInverse(3, 7);
        assertEquals(5, inverse, "Modular inverse of 3 mod 7 should be 5");
        assertEquals(1, (3 * inverse) % 7, "3 * 5 ≡ 1 (mod 7)");
        
        inverse = ExtendedEuclideanAlgorithm.modularInverse(17, 43);
        assertEquals(1, (17 * inverse) % 43, "17 * inverse ≡ 1 (mod 43)");
    }

    @Test
    void testModularInverseNonExistent() {
        assertThrows(IllegalArgumentException.class, 
                     () -> ExtendedEuclideanAlgorithm.modularInverse(4, 8),
                     "Should throw exception when gcd(4, 8) ≠ 1");
        
        assertThrows(IllegalArgumentException.class, 
                     () -> ExtendedEuclideanAlgorithm.modularInverse(6, 9),
                     "Should throw exception when gcd(6, 9) ≠ 1");
    }

    @Test
    void testModularInverseWithNegativeModulus() {
        assertThrows(IllegalArgumentException.class, 
                     () -> ExtendedEuclideanAlgorithm.modularInverse(3, -7),
                     "Should throw exception for negative modulus");
        
        assertThrows(IllegalArgumentException.class, 
                     () -> ExtendedEuclideanAlgorithm.modularInverse(3, 0),
                     "Should throw exception for zero modulus");
    }

    @Test
    void testModularInverseWithNegativeNumber() {
        long inverse = ExtendedEuclideanAlgorithm.modularInverse(-3, 7);
        assertEquals(1, ((-3) * inverse) % 7, "(-3) * inverse ≡ 1 (mod 7)");
        assertTrue(inverse >= 0 && inverse < 7, "Inverse should be in range [0, 7)");
    }

    @Test
    void testSolveDiophantine() {
        // Test case: 6x + 9y = 21, has solutions since gcd(6, 9) = 3 divides 21
        ExtendedEuclideanAlgorithm.ExtendedGcdResult solution = 
            ExtendedEuclideanAlgorithm.solveDiophantine(6, 9, 21);
        
        assertNotNull(solution, "Solution should exist for 6x + 9y = 21");
        assertEquals(6 * solution.getX() + 9 * solution.getY(), 21, 
                     "Solution should satisfy the equation");
    }

    @Test
    void testSolveDiophantineNoSolution() {
        // Test case: 6x + 9y = 20, no solution since gcd(6, 9) = 3 doesn't divide 20
        ExtendedEuclideanAlgorithm.ExtendedGcdResult solution = 
            ExtendedEuclideanAlgorithm.solveDiophantine(6, 9, 20);
        
        assertNull(solution, "No solution should exist for 6x + 9y = 20");
    }

    @Test
    void testSolveDiophantineCoprimeCoefficients() {
        // Test case: 3x + 5y = 1, should have solutions since gcd(3, 5) = 1
        ExtendedEuclideanAlgorithm.ExtendedGcdResult solution = 
            ExtendedEuclideanAlgorithm.solveDiophantine(3, 5, 1);
        
        assertNotNull(solution, "Solution should exist for 3x + 5y = 1");
        assertEquals(3 * solution.getX() + 5 * solution.getY(), 1, 
                     "Solution should satisfy the equation");
    }

    @Test
    void testSolveDiophantineWithZero() {
        // Test case: 4x + 0y = 8, should have solutions
        ExtendedEuclideanAlgorithm.ExtendedGcdResult solution = 
            ExtendedEuclideanAlgorithm.solveDiophantine(4, 0, 8);
        
        assertNotNull(solution, "Solution should exist for 4x + 0y = 8");
        assertEquals(4 * solution.getX(), 8, "Solution should satisfy the equation");
    }

    @Test
    void testGcdStandalone() {
        assertEquals(6, ExtendedEuclideanAlgorithm.gcd(30, 18), "GCD of 30 and 18 should be 6");
        assertEquals(1, ExtendedEuclideanAlgorithm.gcd(17, 13), "GCD of 17 and 13 should be 1");
        assertEquals(25, ExtendedEuclideanAlgorithm.gcd(100, 25), "GCD of 100 and 25 should be 25");
        assertEquals(42, ExtendedEuclideanAlgorithm.gcd(42, 0), "GCD of 42 and 0 should be 42");
        assertEquals(42, ExtendedEuclideanAlgorithm.gcd(0, 42), "GCD of 0 and 42 should be 42");
    }

    @Test
    void testGcdWithNegativeNumbers() {
        assertEquals(6, ExtendedEuclideanAlgorithm.gcd(-30, 18), "GCD should handle negative numbers");
        assertEquals(6, ExtendedEuclideanAlgorithm.gcd(30, -18), "GCD should handle negative numbers");
        assertEquals(6, ExtendedEuclideanAlgorithm.gcd(-30, -18), "GCD should handle negative numbers");
    }

    @Test
    void testExtendedGcdResultEquality() {
        ExtendedEuclideanAlgorithm.ExtendedGcdResult result1 = 
            new ExtendedEuclideanAlgorithm.ExtendedGcdResult(6, -1, 2);
        ExtendedEuclideanAlgorithm.ExtendedGcdResult result2 = 
            new ExtendedEuclideanAlgorithm.ExtendedGcdResult(6, -1, 2);
        ExtendedEuclideanAlgorithm.ExtendedGcdResult result3 = 
            new ExtendedEuclideanAlgorithm.ExtendedGcdResult(6, 1, 2);
        
        assertEquals(result1, result2, "Equal results should be equal");
        assertEquals(result1.hashCode(), result2.hashCode(), "Equal results should have same hash code");
        assertEquals(result1, result1, "Result should equal itself");
        assertTrue(!result1.equals(result3), "Different results should not be equal");
        assertTrue(!result1.equals(null), "Result should not equal null");
        assertTrue(!result1.equals("string"), "Result should not equal different type");
    }

    @Test
    void testExtendedGcdResultToString() {
        ExtendedEuclideanAlgorithm.ExtendedGcdResult result = 
            new ExtendedEuclideanAlgorithm.ExtendedGcdResult(6, -1, 2);
        
        String expected = "ExtendedGcdResult{gcd=6, x=-1, y=2}";
        assertEquals(expected, result.toString(), "toString should format correctly");
    }

    @Test
    void testLargeNumbers() {
        long a = 1234567890L;
        long b = 987654321L;
        
        ExtendedEuclideanAlgorithm.ExtendedGcdResult result = 
            ExtendedEuclideanAlgorithm.extendedGcd(a, b);
        
        assertTrue(result.verify(a, b), "Large number calculation should verify correctly");
        assertEquals(ExtendedEuclideanAlgorithm.gcd(a, b), result.getGcd(), 
                     "Extended GCD should match regular GCD for large numbers");
    }

    @Test
    void testPerformanceConsistency() {
        // Test that both recursive and iterative versions produce consistent results
        // for a variety of inputs including edge cases
        long[][] testCases = {
            {1, 1}, {1, 2}, {2, 1}, {13, 8}, {21, 13}, {34, 21},
            {Long.MAX_VALUE / 2, Long.MAX_VALUE / 3}
        };
        
        for (long[] testCase : testCases) {
            ExtendedEuclideanAlgorithm.ExtendedGcdResult recursive = 
                ExtendedEuclideanAlgorithm.extendedGcd(testCase[0], testCase[1]);
            ExtendedEuclideanAlgorithm.ExtendedGcdResult iterative = 
                ExtendedEuclideanAlgorithm.extendedGcdIterative(testCase[0], testCase[1]);
            
            assertEquals(recursive.getGcd(), iterative.getGcd(), 
                        "Both methods should produce same GCD");
            assertTrue(recursive.verify(testCase[0], testCase[1]), 
                      "Recursive result should verify");
            assertTrue(iterative.verify(testCase[0], testCase[1]), 
                      "Iterative result should verify");
        }
    }
}