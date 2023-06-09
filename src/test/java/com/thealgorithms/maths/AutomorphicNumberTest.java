package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AutomorphicNumberTest {

    @Test
    void testAutomorphicNumber() {
        int[] trueTestCases = {0, 1, 25, 625, 12890625};
        int[] falseTestCases = {-5, 2, 26, 1234};
        for (Integer n : trueTestCases) {
            assertTrue(AutomorphicNumber.isAutomorphic(n));
            assertTrue(AutomorphicNumber.isAutomorphic2(n));
            assertTrue(AutomorphicNumber.isAutomorphic3(String.valueOf(n)));
        }
        for (Integer n : falseTestCases) {
            assertFalse(AutomorphicNumber.isAutomorphic(n));
            assertFalse(AutomorphicNumber.isAutomorphic2(n));
            assertFalse(AutomorphicNumber.isAutomorphic3(String.valueOf(n)));
        }
        assertTrue(AutomorphicNumber.isAutomorphic3("59918212890625")); // Special case for BigInteger
        assertFalse(AutomorphicNumber.isAutomorphic3("12345678912345")); // Special case for BigInteger
    }
}
