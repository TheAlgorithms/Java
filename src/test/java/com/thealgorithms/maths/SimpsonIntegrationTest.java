package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SimpsonIntegrationTest {
    private final SimpsonIntegration simpson = new SimpsonIntegration();
    private static final double DELTA = 1e-9;

    @Test
    void shouldCalculateCorrectFunction() {
        assertAll(() -> assertEquals(-0.24893534183931973, simpson.f(3)), () -> assertEquals(0.0, simpson.f(2)), () -> assertEquals(4.0, simpson.f(0)), () -> assertEquals(8.154845485377136, simpson.f(-1)));
    }

    @Test
    void shouldCalculateCorrectMethod() {
        int n = 4;
        double a = -1.0;
        double b = 1.0;
        double h = (b - a) / n;

        double result = simpson.simpsonsMethod(n, h, a);
        double expected = 8.51454379418048;

        assertEquals(expected, result, DELTA);
    }

    @Test
    void shouldIncreaseAccuracy() {
        int n1 = 10;
        int n2 = 20;
        double a = 1.0;
        double b = 3.0;
        double h1 = (b - a) / n1;
        double h2 = (b - a) / n2;

        double result1 = simpson.simpsonsMethod(n1, h1, a);
        double result2 = simpson.simpsonsMethod(n2, h2, a);

        assertTrue(Math.abs(result2 - result1) < Math.abs(result1), "Accuracy should improve with more intervals.");
    }

    @Test
    void shouldNotFailOnOddNButTestResultSanity() {
        int n = 3;
        double a = -1.0;
        double b = 1.0;
        double h = (b - a) / n;

        double result = simpson.simpsonsMethod(n, h, a);

        assertTrue(Double.isFinite(result), "Result should be finite even with odd n (though it may be inaccurate).");
    }

    @Test
    void shouldReturnZeroWhenAEqualsB() {
        int n = 2;
        double a = 2.0;
        double b = 2.0;
        double h = (b - a) / n;

        double expected = 0.0;
        double result = simpson.simpsonsMethod(n, h, a);

        assertEquals(expected, result, DELTA, "Integral over zero-width interval should be zero.");
    }

    @Test
    void shouldHandleMinimalEvenN() {
        int n = 2;
        double a = 0.0;
        double b = 1.0;
        double h = (b - a) / n;

        double result = simpson.simpsonsMethod(n, h, a);

        assertTrue(Double.isFinite(result), "Result should be finite with minimal even n=2.");
    }
}
