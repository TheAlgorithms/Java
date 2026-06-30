package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SquareRootWithBabylonianMethodTest {

    private static final float DELTA = 1e-3f;

    @Test
    void testPerfectSquares() {
        assertEquals(1.0f, SquareRootWithBabylonianMethod.squareRoot(1.0f), DELTA);
        assertEquals(2.0f, SquareRootWithBabylonianMethod.squareRoot(4.0f), DELTA);
        assertEquals(3.0f, SquareRootWithBabylonianMethod.squareRoot(9.0f), DELTA);
        assertEquals(5.0f, SquareRootWithBabylonianMethod.squareRoot(25.0f), DELTA);
        assertEquals(12.0f, SquareRootWithBabylonianMethod.squareRoot(144.0f), DELTA);
    }

    @Test
    void testNonPerfectSquares() {
        assertEquals((float) Math.sqrt(2.0), SquareRootWithBabylonianMethod.squareRoot(2.0f), DELTA);
        assertEquals((float) Math.sqrt(50.0), SquareRootWithBabylonianMethod.squareRoot(50.0f), DELTA);
        assertEquals((float) Math.sqrt(99.0), SquareRootWithBabylonianMethod.squareRoot(99.0f), DELTA);
    }

    @Test
    void testMatchesMathSqrtForRangeOfValues() {
        for (float num = 1.0f; num <= 1000.0f; num += 1.0f) {
            assertEquals((float) Math.sqrt(num), SquareRootWithBabylonianMethod.squareRoot(num), DELTA);
        }
    }
}
