package com.thealgorithms.streaming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UnscentedKalmanFilterTest {

    private static final double OFFSET = 5.0;
    private static final double INTERVAL = 0.1;
    private static final double[][] IDENTITY_2 = {{1.0, 0.0}, {0.0, 1.0}};

    private static double range(double position) {
        return Math.hypot(position, OFFSET);
    }

    @Test
    void rejectsAnEmptyOrMalformedStart() {
        assertThrows(IllegalArgumentException.class, () -> new UnscentedKalmanFilter(new double[0], new double[0][0]));
        assertThrows(IllegalArgumentException.class, () -> new UnscentedKalmanFilter(new double[] {1.0, 2.0}, new double[][] {{1.0}}));
        assertThrows(IllegalArgumentException.class, () -> new UnscentedKalmanFilter(new double[] {Double.NaN}, new double[][] {{1.0}}));
        assertThrows(IllegalArgumentException.class, () -> new UnscentedKalmanFilter(new double[] {1.0}, new double[][] {{Double.POSITIVE_INFINITY}}));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -1.0, Double.NaN, Double.POSITIVE_INFINITY})
    void rejectsAnInvalidAlpha(double alpha) {
        assertThrows(IllegalArgumentException.class, () -> new UnscentedKalmanFilter(new double[] {0.0}, new double[][] {{1.0}}, alpha, 2.0, 0.0));
    }

    @Test
    void rejectsAnInvalidBetaOrKappa() {
        assertThrows(IllegalArgumentException.class, () -> new UnscentedKalmanFilter(new double[] {0.0}, new double[][] {{1.0}}, 1.0, -0.5, 0.0));
        assertThrows(IllegalArgumentException.class, () -> new UnscentedKalmanFilter(new double[] {0.0}, new double[][] {{1.0}}, 1.0, 2.0, Double.NaN));
    }

    @Test
    @DisplayName("parameters that collapse the sigma points onto the mean are refused")
    void rejectsParametersThatLeaveNoSpread() {
        assertThrows(IllegalArgumentException.class, () -> new UnscentedKalmanFilter(new double[] {0.0}, new double[][] {{1.0}}, 1.0, 2.0, -1.0));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.001, 0.5, 1.0, 2.0})
    @DisplayName("the mean weights sum to one for any spread")
    void theMeanWeightsSumToOne(double alpha) {
        UnscentedKalmanFilter filter = new UnscentedKalmanFilter(new double[] {0.0, 0.0, 0.0}, new double[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}, alpha, 2.0, 0.0);

        double sum = 0.0;
        for (double weight : filter.meanWeights()) {
            sum += weight;
        }

        assertEquals(1.0, sum, 1e-9);
        assertEquals(7, filter.meanWeights().length);
        assertEquals(7, filter.covarianceWeights().length);
    }

    @Test
    @DisplayName("the sigma points carry exactly the mean and the covariance of the estimate")
    void theSigmaPointsReproduceTheEstimate() {
        double[] mean = {1.0, -2.0};
        double[][] covariance = {{2.0, 0.6}, {0.6, 1.0}};
        UnscentedKalmanFilter filter = new UnscentedKalmanFilter(mean, covariance);

        double[][] points = filter.sigmaPoints();
        double[] meanWeights = filter.meanWeights();
        double[] covarianceWeights = filter.covarianceWeights();

        double[] recovered = new double[2];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < 2; j++) {
                recovered[j] += meanWeights[i] * points[i][j];
            }
        }
        double[][] spread = new double[2][2];
        for (int i = 0; i < points.length; i++) {
            for (int r = 0; r < 2; r++) {
                for (int c = 0; c < 2; c++) {
                    spread[r][c] += covarianceWeights[i] * (points[i][r] - mean[r]) * (points[i][c] - mean[c]);
                }
            }
        }

        assertEquals(5, points.length);
        for (int r = 0; r < 2; r++) {
            assertEquals(mean[r], recovered[r], 1e-12);
            for (int c = 0; c < 2; c++) {
                assertEquals(covariance[r][c], spread[r][c], 1e-12);
            }
        }
    }

    @Test
    @DisplayName("the mean and the variance of x squared come out exact, where the extended filter misses both")
    void isExactOnAQuadratic() {
        double mu = 3.0;
        double sigma = 0.5;
        double trueMean = mu * mu + sigma * sigma;
        double trueVariance = 4 * mu * mu * sigma * sigma + 2 * Math.pow(sigma, 4);

        UnscentedKalmanFilter unscented = new UnscentedKalmanFilter(new double[] {mu}, new double[][] {{sigma * sigma}});
        unscented.predict(state -> new double[] {state[0] * state[0]}, new double[][] {{0.0}});

        ExtendedKalmanFilter extended = new ExtendedKalmanFilter(new double[] {mu}, new double[][] {{sigma * sigma}});
        extended.predict(state -> new double[] {state[0] * state[0]}, state -> new double[][] {{2 * state[0]}}, new double[][] {{0.0}});

        assertEquals(trueMean, unscented.state()[0], 1e-12);
        assertEquals(trueVariance, unscented.variance(0), 1e-12);
        assertEquals(mu * mu, extended.state()[0], 1e-12, "the extended filter drops the sigma squared the spread contributes");
        assertEquals(4 * mu * mu * sigma * sigma, extended.variance(0), 1e-12, "and the 2 sigma^4 of the variance");
    }

    @Test
    @DisplayName("on a linear model it is the Kalman filter, step for step")
    void agreesWithTheKalmanFilterOnALinearModel() {
        double[][] transition = {{1.0, INTERVAL}, {0.0, 1.0}};
        double[][] processNoise = {{1e-3, 0.0}, {0.0, 1e-3}};
        double[][] measurementNoise = {{0.25}};
        UnscentedKalmanFilter unscented = new UnscentedKalmanFilter(new double[] {0.0, 1.0}, IDENTITY_2);
        ExtendedKalmanFilter extended = new ExtendedKalmanFilter(new double[] {0.0, 1.0}, IDENTITY_2);
        Random random = new Random(3L);

        for (int step = 0; step < 500; step++) {
            unscented.predict(state -> new double[] {state[0] + INTERVAL * state[1], state[1]}, processNoise);
            extended.predict(transition, processNoise);

            double measurement = 0.1 * step + 0.5 * random.nextGaussian();
            unscented.update(new double[] {measurement}, state -> new double[] {state[0]}, measurementNoise);
            extended.update(new double[] {measurement}, new double[][] {{1.0, 0.0}}, measurementNoise);

            for (int i = 0; i < 2; i++) {
                assertEquals(extended.state()[i], unscented.state()[i], 1e-9, "state at step " + step);
                for (int j = 0; j < 2; j++) {
                    assertEquals(extended.covariance()[i][j], unscented.covariance()[i][j], 1e-9, "covariance at step " + step);
                }
            }
        }
    }

    @Test
    @DisplayName("a target seen only as a range is tracked without writing a single derivative")
    void tracksATargetThroughARangeOnlySensor() {
        Random random = new Random(17L);
        double[][] processNoise = {{1e-6, 0.0}, {0.0, 1e-4}};
        double[][] measurementNoise = {{0.04}};
        UnscentedKalmanFilter filter = new UnscentedKalmanFilter(new double[] {1.0, 0.5}, new double[][] {{4.0, 0.0}, {0.0, 4.0}});

        double position = 2.0;
        double velocity = 1.0;
        double error = 0.0;
        int counted = 0;

        for (int step = 0; step < 400; step++) {
            position += velocity * INTERVAL;
            double measurement = range(position) + 0.2 * random.nextGaussian();

            filter.predict(state -> new double[] {state[0] + INTERVAL * state[1], state[1]}, processNoise);
            filter.update(new double[] {measurement}, state -> new double[] {range(state[0])}, measurementNoise);

            if (step > 200) {
                error += Math.abs(filter.state()[0] - position);
                counted++;
            }
        }

        assertTrue(error / counted < 0.2, "the filtered position was off by " + error / counted);
        assertEquals(velocity, filter.state()[1], 0.2, "the velocity is never measured, only inferred");
    }

    @Test
    @DisplayName("the covariance stays symmetric and its variances positive over a long nonlinear run")
    void keepsTheCovarianceHealthy() {
        Random random = new Random(29L);
        UnscentedKalmanFilter filter = new UnscentedKalmanFilter(new double[] {2.0, 1.0}, IDENTITY_2);
        double[][] processNoise = {{1e-4, 0.0}, {0.0, 1e-4}};
        double[][] measurementNoise = {{0.1}};

        for (int step = 0; step < 500; step++) {
            filter.predict(state -> new double[] {state[0] + INTERVAL * Math.sin(state[1]), state[1]}, processNoise);
            filter.update(new double[] {range(2.0 + 0.01 * step) + 0.3 * random.nextGaussian()}, state -> new double[] {range(state[0])}, measurementNoise);

            double[][] covariance = filter.covariance();
            assertEquals(covariance[0][1], covariance[1][0], 1e-15, "the covariance lost its symmetry at step " + step);
            assertTrue(covariance[0][0] > 0.0 && covariance[1][1] > 0.0, "a variance stopped being positive at step " + step);
        }
    }

    @Test
    @DisplayName("prediction through the identity only adds the process noise")
    void predictionAloneAddsTheProcessNoise() {
        UnscentedKalmanFilter filter = new UnscentedKalmanFilter(new double[] {3.0}, new double[][] {{1.0}});

        for (int step = 1; step <= 10; step++) {
            filter.predict(state -> state.clone(), new double[][] {{0.5}});

            assertEquals(3.0, filter.state()[0], 1e-12);
            assertEquals(1.0 + 0.5 * step, filter.variance(0), 1e-12);
        }
        assertEquals(10, filter.predictionCount());
        assertEquals(0, filter.updateCount());
    }

    @Test
    @DisplayName("a covariance that is not positive definite has no square root, and the step is refused")
    void refusesACovarianceThatIsNotPositiveDefinite() {
        UnscentedKalmanFilter filter = new UnscentedKalmanFilter(new double[] {0.0, 0.0}, new double[][] {{1.0, 2.0}, {2.0, 1.0}});

        assertThrows(ArithmeticException.class, filter::sigmaPoints);
        assertThrows(ArithmeticException.class, () -> filter.predict(state -> state, IDENTITY_2));
    }

    @Test
    @DisplayName("a measurement that carries no information is reported rather than dividing by zero")
    void refusesASingularInnovationCovariance() {
        UnscentedKalmanFilter filter = new UnscentedKalmanFilter(new double[] {0.0}, new double[][] {{1.0}});

        assertThrows(ArithmeticException.class, () -> filter.update(new double[] {1.0}, state -> new double[] {1.0}, new double[][] {{0.0}}));
    }

    @Test
    void rejectsAModelThatMisbehaves() {
        UnscentedKalmanFilter filter = new UnscentedKalmanFilter(new double[] {0.0}, new double[][] {{1.0}});

        assertThrows(IllegalArgumentException.class, () -> filter.predict(state -> new double[] {1.0, 2.0}, new double[][] {{1.0}}));
        assertThrows(IllegalArgumentException.class, () -> filter.predict(state -> new double[] {Double.NaN}, new double[][] {{1.0}}));
        assertThrows(IllegalArgumentException.class, () -> filter.predict(state -> state, new double[][] {{1.0, 0.0}}));
        assertThrows(IllegalArgumentException.class, () -> filter.update(new double[0], state -> state, new double[0][0]));
        assertThrows(IllegalArgumentException.class, () -> filter.update(new double[] {1.0}, state -> new double[] {1.0, 2.0}, new double[][] {{1.0}}));
    }

    @Test
    void theStateAndCovarianceAreCopiedOut() {
        UnscentedKalmanFilter filter = new UnscentedKalmanFilter(new double[] {1.0}, new double[][] {{2.0}});

        filter.state()[0] = 99.0;
        filter.covariance()[0][0] = 99.0;
        filter.meanWeights()[0] = 99.0;

        assertEquals(1.0, filter.state()[0], 1e-12);
        assertEquals(2.0, filter.variance(0), 1e-12);
        assertTrue(filter.meanWeights()[0] < 1.0);
    }

    @Test
    void exposesItsConfiguration() {
        UnscentedKalmanFilter filter = new UnscentedKalmanFilter(new double[] {0.0, 0.0}, IDENTITY_2, 0.5, 2.0, 1.0);

        assertEquals(2, filter.stateSize());
        assertEquals(0.5, filter.alpha());
        assertEquals(2.0, filter.beta());
        assertEquals(1.0, filter.kappa());
        assertEquals(0, filter.lastInnovation().length);
        assertThrows(IllegalArgumentException.class, () -> filter.variance(2));
    }

    @Test
    void resetRestartsFromAKnownEstimate() {
        UnscentedKalmanFilter filter = new UnscentedKalmanFilter(new double[] {0.0}, new double[][] {{1.0}});
        filter.update(new double[] {5.0}, state -> state.clone(), new double[][] {{1.0}});

        filter.reset(new double[] {-2.0}, new double[][] {{9.0}});

        assertEquals(-2.0, filter.state()[0], 1e-12);
        assertEquals(9.0, filter.variance(0), 1e-12);
        assertEquals(0, filter.updateCount());
        assertEquals(0, filter.lastInnovation().length);
    }

    @Test
    void toStringMentionsTheState() {
        UnscentedKalmanFilter filter = new UnscentedKalmanFilter(new double[] {1.5}, new double[][] {{1.0}});

        String text = filter.toString();

        assertTrue(text.contains("UnscentedKalmanFilter"));
        assertTrue(text.contains("1.5"));
        assertTrue(text.contains("beta=2.0"));
    }
}
