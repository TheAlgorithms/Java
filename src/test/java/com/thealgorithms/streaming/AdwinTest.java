package com.thealgorithms.streaming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AdwinTest {

    private static double mean(double[] values) {
        double sum = 0.0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.length;
    }

    private static double variance(double[] values) {
        double mean = mean(values);
        double sum = 0.0;
        for (double value : values) {
            sum += (value - mean) * (value - mean);
        }
        return sum / values.length;
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 1.0, -0.5, 2.0, Double.NaN})
    void rejectsInvalidDeltas(double delta) {
        assertThrows(IllegalArgumentException.class, () -> new Adwin(delta));
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsNonFiniteSamples(double value) {
        Adwin window = new Adwin();
        assertThrows(IllegalArgumentException.class, () -> window.accept(value));
    }

    @Test
    void startsEmpty() {
        Adwin window = new Adwin();

        assertEquals(0, window.width());
        assertEquals(0.0, window.estimate());
        assertEquals(0.0, window.variance());
        assertEquals(0, window.bucketCount());
        assertEquals(0, window.changeCount());
        assertEquals(0.002, window.delta());
    }

    @Test
    @DisplayName("a constant stream is never cut and the window holds all of it")
    void keepsEverythingOnAConstantStream() {
        Adwin window = new Adwin();

        for (int i = 0; i < 1000; i++) {
            assertFalse(window.accept(3.0));
        }

        assertEquals(1000, window.width());
        assertEquals(1000, window.count());
        assertEquals(3.0, window.estimate());
        assertEquals(0.0, window.variance());
        assertEquals(0, window.changeCount());
    }

    @Test
    @DisplayName("the histogram reproduces the mean and the variance of the window exactly")
    void agreesWithADirectComputation() {
        Adwin window = new Adwin();
        Random random = new Random(11L);
        double[] data = new double[2000];

        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextGaussian();
            assertFalse(window.accept(data[i]), "stationary noise must not be cut, sample " + i);
        }

        assertEquals(data.length, window.width());
        assertEquals(mean(data), window.estimate(), 1e-9);
        assertEquals(variance(data), window.variance(), 1e-9);
    }

    @Test
    @DisplayName("a step in the level cuts the window quickly")
    void findsAStep() {
        Adwin window = new Adwin();
        Random random = new Random(5L);

        int firstCut = -1;
        for (int i = 0; i < 1200; i++) {
            double sample = 0.1 * random.nextGaussian() + (i < 1000 ? 0.0 : 1.0);
            boolean cut = window.accept(sample);
            if (cut && firstCut < 0) {
                firstCut = i;
            }
        }

        assertTrue(firstCut >= 1000, "the window must not be cut before the step, cut at " + firstCut);
        assertTrue(firstCut < 1050, "the step should be found quickly, cut at " + firstCut);
        assertEquals(200, window.width(), "the window should hold exactly the samples after the step");
        assertEquals(1.0, window.estimate(), 0.05);
    }

    @Test
    @DisplayName("the window follows a level that changes several times")
    void tracksSeveralRegimes() {
        Adwin window = new Adwin();
        Random random = new Random(23L);

        double[] levels = {0.0, 5.0, -3.0, 10.0};
        for (double level : levels) {
            for (int i = 0; i < 400; i++) {
                window.accept(level + 0.2 * random.nextGaussian());
            }
            assertEquals(level, window.estimate(), 0.1, "the estimate should have caught up with " + level);
        }

        assertTrue(window.changeCount() > 0);
    }

    @Test
    @DisplayName("the window never holds more than the stream")
    void theWidthNeverExceedsTheCount() {
        Adwin window = new Adwin();
        Random random = new Random(31L);

        for (int i = 0; i < 500; i++) {
            window.accept(random.nextGaussian() + i / 100.0);
            assertTrue(window.width() <= window.count());
            assertTrue(window.width() >= 1);
        }
    }

    @Test
    @DisplayName("the number of buckets grows like the logarithm of the width")
    void keepsALogarithmicNumberOfBuckets() {
        Adwin window = new Adwin();
        for (int i = 0; i < 5000; i++) {
            window.accept(1.0);
        }

        int levels = 64 - Long.numberOfLeadingZeros(window.width());
        int bound = (Adwin.MAX_BUCKETS + 1) * (levels + 1);

        assertEquals(5000, window.width());
        assertTrue(window.bucketCount() <= bound, "held " + window.bucketCount() + " buckets, bound was " + bound);
    }

    @Test
    @DisplayName("a smaller delta cuts less eagerly")
    void deltaControlsHowEagerlyTheWindowIsCut() {
        Adwin eager = new Adwin(0.5);
        Adwin careful = new Adwin(1e-9);
        Random random = new Random(41L);

        for (int i = 0; i < 600; i++) {
            double sample = random.nextGaussian() + (i < 300 ? 0.0 : 0.3);
            eager.accept(sample);
            careful.accept(sample);
        }

        assertTrue(eager.changeCount() >= careful.changeCount(), "eager cut " + eager.changeCount() + ", careful cut " + careful.changeCount());
    }

    @Test
    void scanReportsOneVerdictPerSample() {
        Adwin window = new Adwin();

        boolean[] cuts = window.scan(new double[] {1.0, 1.0, 1.0, 1.0});

        assertEquals(4, cuts.length);
        assertEquals(4, window.count());
    }

    @Test
    void resetEmptiesTheWindow() {
        Adwin window = new Adwin();
        Random random = new Random(3L);
        for (int i = 0; i < 300; i++) {
            window.accept(random.nextGaussian());
        }

        window.reset();

        assertEquals(0, window.width());
        assertEquals(0, window.count());
        assertEquals(0, window.changeCount());
        assertEquals(0, window.bucketCount());
        assertEquals(0.0, window.estimate());
        assertFalse(window.accept(1.0));
        assertEquals(1.0, window.estimate());
    }

    @Test
    void toStringMentionsTheState() {
        Adwin window = new Adwin();
        window.accept(2.0);

        String text = window.toString();

        assertTrue(text.contains("Adwin"));
        assertTrue(text.contains("width=1"));
    }
}
