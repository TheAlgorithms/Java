package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PerfectCubeTest {

    @Test
    public void perfectCube() {

        Assertions.assertTrue(PerfectCube.isPerfectCube(-27));
        Assertions.assertTrue(PerfectCube.isPerfectCubeMathCbrt(-27));
        Assertions.assertTrue(PerfectCube.isPerfectCube(-1));
        Assertions.assertTrue(PerfectCube.isPerfectCubeMathCbrt(-1));
        Assertions.assertTrue(PerfectCube.isPerfectCube(0));
        Assertions.assertTrue(PerfectCube.isPerfectCubeMathCbrt(0));
        Assertions.assertTrue(PerfectCube.isPerfectCube(1));
        Assertions.assertTrue(PerfectCube.isPerfectCubeMathCbrt(1));
        Assertions.assertTrue(PerfectCube.isPerfectCube(8));
        Assertions.assertTrue(PerfectCube.isPerfectCubeMathCbrt(8));
        Assertions.assertTrue(PerfectCube.isPerfectCube(27));
        Assertions.assertTrue(PerfectCube.isPerfectCubeMathCbrt(27));

        Assertions.assertFalse(PerfectCube.isPerfectCube(-9));
        Assertions.assertFalse(PerfectCube.isPerfectCubeMathCbrt(-9));
        Assertions.assertFalse(PerfectCube.isPerfectCube(2));
        Assertions.assertFalse(PerfectCube.isPerfectCubeMathCbrt(2));
        Assertions.assertFalse(PerfectCube.isPerfectCube(4));
        Assertions.assertFalse(PerfectCube.isPerfectCubeMathCbrt(4));
        Assertions.assertFalse(PerfectCube.isPerfectCube(30));
        Assertions.assertFalse(PerfectCube.isPerfectCubeMathCbrt(30));
    }
}
