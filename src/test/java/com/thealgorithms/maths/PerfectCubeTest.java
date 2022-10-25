package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PerfectCubeTest {

    @Test
    public void perfectCube() {
        int trueTestCases[] = { -27, -1, 0, 1, 8, 27 };
        int falseTestCases[] = { -9, 2, 4, 30 };
        for (Integer n : trueTestCases) {
            Assertions.assertTrue(PerfectCube.isPerfectCube(n));
            Assertions.assertTrue(PerfectCube.isPerfectCubeMathCbrt(n));
        }
        for (Integer n : falseTestCases) {
            Assertions.assertFalse(PerfectCube.isPerfectCube(n));
            Assertions.assertFalse(PerfectCube.isPerfectCubeMathCbrt(n));
        }
    }
}
