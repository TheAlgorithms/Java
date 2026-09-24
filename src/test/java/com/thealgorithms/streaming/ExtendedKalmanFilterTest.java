package com.thealgorithms.streaming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExtendedKalmanFilterTest {

    private static final double OFFSET = 5.0;
    private static final double INTERVAL = 0.1;

    private static ExtendedKalmanFilter scalarFilter(double estimate, double variance) {
        return new ExtendedKalmanFilter(new double[] {estimate}, new double[][] {{variance}});
    }

    /** The range a sensor at the origin sees of a target passing at a fixed lateral offset. */
    private static double range(double position) {
        return Math.hypot(position, OFFSET);
    }

    @Test
    void rejectsAnEmptyOrMalformedStart() {
        assertThrows(IllegalArgumentException.class, () -> new ExtendedKalmanFilter(new double[0], new double[0][0]));
        assertThrows(IllegalArgumentException.class, () -> new ExtendedKalmanFilter(new double[] {1.0, 2.0}, new double[][] {{1.0}}));
        assertThrows(IllegalArgumentException.class, () -> new ExtendedKalmanFilter(new double[] {1.0}, new double[][] {{1.0, 0.0}}));
        assertThrows(IllegalArgumentException.class, () -> new ExtendedKalmanFilter(new double[] {Double.NaN}, new double[][] {{1.0}}));
        assertThrows(IllegalArgumentException.class, () -> new ExtendedKalmanFilter(new double[] {1.0}, new double[][] {{Double.POSITIVE_INFINITY}}));
    }

    @Test
    void rejectsMisshapenModels() {
        ExtendedKalmanFilter filter = scalarFilter(0.0, 1.0);

        assertThrows(IllegalArgumentException.class, () -> filter.predict(new double[][] {{1.0, 0.0}}, new double[][] {{1.0}}));
        assertThrows(IllegalArgumentException.class, () -> filter.predict(new double[][] {{1.0}}, new double[][] {{1.0, 0.0}}));
        assertThrows(IllegalArgumentException.class, () -> filter.update(new double[0], new double[0][0], new double[0][0]));
        assertThrows(IllegalArgumentException.class, () -> filter.update(new double[] {1.0}, new double[][] {{1.0}}, new double[][] {{1.0, 0.0}}));
        assertThrows(IllegalArgumentException.class, () -> filter.update(new double[] {1.0}, new double[][] {{1.0, 1.0}}, new double[][] {{1.0}}));
    }

    @Test
    @DisplayName("a model that returns the wrong shape or a non-finite value is caught")
    void rejectsAModelThatMisbehaves() {
        ExtendedKalmanFilter filter = scalarFilter(0.0, 1.0);

        assertThrows(IllegalArgumentException.class, () -> filter.predict(current -> new double[] {1.0, 2.0}, current -> new double[][] {{1.0}}, new double[][] {{1.0}}));
        assertThrows(IllegalArgumentException.class, () -> filter.predict(current -> new double[] {Double.NaN}, current -> new double[][] {{1.0}}, new double[][] {{1.0}}));
        assertThrows(IllegalArgumentException.class, () -> filter.update(new double[] {1.0}, current -> new double[] {1.0, 2.0}, current -> new double[][] {{1.0}}, new double[][] {{1.0}}));
    }

    @Test
    @DisplayName("a measurement that carries no information is reported rather than silently poisoning the state")
    void rejectsASingularInnovationCovariance() {
        ExtendedKalmanFilter filter = scalarFilter(0.0, 0.0);

        assertThrows(ArithmeticException.class, () -> filter.update(new double[] {1.0}, new double[][] {{1.0}}, new double[][] {{0.0}}));
    }

    @Test
    @DisplayName("one update of a one dimensional filter is the textbook half way step")
    void reproducesTheTextbookScalarUpdate() {
        ExtendedKalmanFilter filter = scalarFilter(0.0, 1.0);

        double[] corrected = filter.update(new double[] {1.0}, new double[][] {{1.0}}, new double[][] {{1.0}});

        assertEquals(0.5, corrected[0], 1e-12, "with equal confidence the estimate lands in the middle");
        assertEquals(0.5, filter.variance(0), 1e-12);
        assertEquals(0.5, filter.lastGain()[0][0], 1e-12);
        assertEquals(1.0, filter.lastInnovation()[0], 1e-12);
    }

    @Test
    @DisplayName("on a linear model the filter is the scalar Kalman recursion, to the last digit")
    void matchesTheScalarKalmanRecursion() {
        double processNoise = 0.01;
        double measurementNoise = 0.25;
        ExtendedKalmanFilter filter = scalarFilter(0.0, 1.0);
        double estimate = 0.0;
        double variance = 1.0;

        Random random = new Random(5L);
        for (int step = 0; step < 500; step++) {
            filter.predict(new double[][] {{1.0}}, new double[][] {{processNoise}});
            variance += processNoise;

            double measurement = random.nextGaussian();
            filter.update(new double[] {measurement}, new double[][] {{1.0}}, new double[][] {{measurementNoise}});
            double gain = variance / (variance + measurementNoise);
            estimate += gain * (measurement - estimate);
            variance = (1 - gain) * variance * (1 - gain) + gain * measurementNoise * gain;

            assertEquals(estimate, filter.state()[0], 1e-12, "step " + step);
            assertEquals(variance, filter.variance(0), 1e-12, "step " + step);
        }
    }

    @Test
    @DisplayName("the Joseph form keeps the covariance symmetric over a long run")
    void keepsTheCovarianceSymmetric() {
        Random random = new Random(11L);
        ExtendedKalmanFilter filter = new ExtendedKalmanFilter(new double[] {0.0, 0.0, 0.0}, new double[][] {{1.0, 0.0, 0.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 1.0}});
        double[][] transition = {{1.0, INTERVAL, 0.0}, {0.0, 1.0, INTERVAL}, {0.0, 0.0, 1.0}};
        double[][] processNoise = {{1e-4, 0.0, 0.0}, {0.0, 1e-4, 0.0}, {0.0, 0.0, 1e-4}};
        double[][] model = {{1.0, 0.0, 0.0}};
        double[][] measurementNoise = {{0.5}};

        for (int step = 0; step < 500; step++) {
            filter.predict(transition, processNoise);
            filter.update(new double[] {random.nextGaussian()}, model, measurementNoise);

            double[][] covariance = filter.covariance();
            for (int i = 0; i < covariance.length; i++) {
                assertTrue(covariance[i][i] >= 0.0, "a variance went negative at step " + step);
                for (int j = 0; j < i; j++) {
                    assertEquals(covariance[i][j], covariance[j][i], 1e-12, "the covariance lost its symmetry at step " + step);
                }
            }
        }
    }

    @Test
    @DisplayName("repeated measurements of something that does not move shrink the uncertainty towards zero")
    void convergesOnAConstant() {
        Random random = new Random(3L);
        ExtendedKalmanFilter filter = scalarFilter(0.0, 100.0);
        double truth = 7.0;
        double previous = Double.MAX_VALUE;

        for (int step = 0; step < 2000; step++) {
            filter.update(new double[] {truth + random.nextGaussian()}, new double[][] {{1.0}}, new double[][] {{1.0}});
            double variance = filter.variance(0);
            assertTrue(variance < previous, "the uncertainty must never grow without process noise, step " + step);
            previous = variance;
        }

        assertEquals(truth, filter.state()[0], 0.1);
        assertTrue(filter.variance(0) < 0.001, "after 2000 measurements the variance was still " + filter.variance(0));
    }

    @Test
    @DisplayName("a target seen only as a range is tracked through the nonlinear measurement")
    void tracksATargetThroughARangeOnlySensor() {
        Random random = new Random(17L);
        double[][] transition = {{1.0, INTERVAL}, {0.0, 1.0}};
        double[][] processNoise = {{1e-6, 0.0}, {0.0, 1e-4}};
        double[][] measurementNoise = {{0.04}};
        ExtendedKalmanFilter filter = new ExtendedKalmanFilter(new double[] {1.0, 0.5}, new double[][] {{4.0, 0.0}, {0.0, 4.0}});

        double position = 2.0;
        double velocity = 1.0;
        double filterError = 0.0;
        double rawError = 0.0;
        int counted = 0;

        for (int step = 0; step < 400; step++) {
            position += velocity * INTERVAL;
            double measurement = range(position) + 0.2 * random.nextGaussian();

            filter.predict(transition, processNoise);
            filter.update(new double[] {measurement}, state -> new double[] {range(state[0])}, state -> new double[][] {{state[0] / range(state[0]), 0.0}}, measurementNoise);

            if (step > 200) {
                filterError += Math.abs(filter.state()[0] - position);
                rawError += Math.abs(Math.sqrt(Math.max(0.0, measurement * measurement - OFFSET * OFFSET)) - position);
                counted++;
            }
        }

        double filtered = filterError / counted;
        double raw = rawError / counted;
        assertTrue(filtered < 0.2, "the filtered position was off by " + filtered);
        assertTrue(filtered < raw / 2, "filtering should beat inverting each measurement on its own, " + filtered + " against " + raw);
        assertEquals(velocity, filter.state()[1], 0.2, "the velocity is never measured, only inferred");
    }

    @Test
    @DisplayName("the linear overloads are the nonlinear ones with a matrix for a model")
    void theLinearOverloadsAgreeWithTheFunctionalOnes() {
        double[][] transition = {{1.0, INTERVAL}, {0.0, 1.0}};
        double[][] processNoise = {{0.01, 0.0}, {0.0, 0.01}};
        double[][] model = {{1.0, 0.0}};
        double[][] measurementNoise = {{0.5}};
        double[] start = {1.0, 2.0};
        double[][] startCovariance = {{1.0, 0.0}, {0.0, 1.0}};

        ExtendedKalmanFilter linear = new ExtendedKalmanFilter(start, startCovariance);
        ExtendedKalmanFilter functional = new ExtendedKalmanFilter(start, startCovariance);

        for (int step = 0; step < 50; step++) {
            linear.predict(transition, processNoise);
            functional.predict(state -> new double[] {state[0] + INTERVAL * state[1], state[1]}, state -> transition, processNoise);

            double measurement = 0.1 * step;
            linear.update(new double[] {measurement}, model, measurementNoise);
            functional.update(new double[] {measurement}, state -> new double[] {state[0]}, state -> model, measurementNoise);

            Assertions.assertArrayEquals(linear.state(), functional.state(), 1e-12, "step " + step);
        }
    }

    @Test
    @DisplayName("prediction without measurements only widens the uncertainty")
    void predictionAloneAddsUncertainty() {
        ExtendedKalmanFilter filter = scalarFilter(3.0, 1.0);

        for (int step = 1; step <= 10; step++) {
            filter.predict(new double[][] {{1.0}}, new double[][] {{0.5}});

            assertEquals(3.0, filter.state()[0], 1e-12, "a unit transition must not move the estimate");
            assertEquals(1.0 + 0.5 * step, filter.variance(0), 1e-12);
        }
        assertEquals(10, filter.predictionCount());
        assertEquals(0, filter.updateCount());
    }

    @Test
    void theStateAndCovarianceAreCopiedOut() {
        ExtendedKalmanFilter filter = scalarFilter(1.0, 2.0);

        filter.state()[0] = 99.0;
        filter.covariance()[0][0] = 99.0;
        filter.update(new double[] {1.0}, new double[][] {{1.0}}, new double[][] {{1.0}});
        filter.lastGain()[0][0] = 99.0;
        filter.lastInnovation()[0] = 99.0;

        assertTrue(filter.state()[0] < 2.0);
        assertTrue(filter.covariance()[0][0] < 2.0);
        assertTrue(filter.lastGain()[0][0] < 1.0);
    }

    @Test
    void exposesItsShapeAndCounters() {
        ExtendedKalmanFilter filter = new ExtendedKalmanFilter(new double[] {0.0, 0.0}, new double[][] {{1.0, 0.0}, {0.0, 1.0}});

        assertEquals(2, filter.stateSize());
        assertEquals(0, filter.predictionCount());
        assertEquals(0, filter.updateCount());
        assertEquals(0, filter.lastInnovation().length);
        assertThrows(IllegalArgumentException.class, () -> filter.variance(2));
        assertThrows(IllegalArgumentException.class, () -> filter.variance(-1));
    }

    @Test
    void resetRestartsFromAKnownEstimate() {
        ExtendedKalmanFilter filter = scalarFilter(0.0, 1.0);
        filter.update(new double[] {5.0}, new double[][] {{1.0}}, new double[][] {{1.0}});

        filter.reset(new double[] {-2.0}, new double[][] {{9.0}});

        assertEquals(-2.0, filter.state()[0], 1e-12);
        assertEquals(9.0, filter.variance(0), 1e-12);
        assertEquals(0, filter.updateCount());
        assertEquals(0, filter.lastInnovation().length);
        assertThrows(IllegalArgumentException.class, () -> filter.reset(new double[] {1.0, 2.0}, new double[][] {{1.0}}));
    }

    @Test
    void toStringMentionsTheState() {
        ExtendedKalmanFilter filter = scalarFilter(1.5, 1.0);
        filter.predict(new double[][] {{1.0}}, new double[][] {{0.1}});

        String text = filter.toString();

        assertTrue(text.contains("ExtendedKalmanFilter"));
        assertTrue(text.contains("predictions=1"));
        assertTrue(text.contains("1.5"));
    }
}
