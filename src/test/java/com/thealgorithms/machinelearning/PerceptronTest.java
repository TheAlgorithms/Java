package com.thealgorithms.machinelearning;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PerceptronTest {

    @Test
    void learnsAndFunction() {
        double[][] features = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] labels = {0, 0, 0, 1};

        Perceptron perceptron = new Perceptron(1.0, 20);
        perceptron.fit(features, labels);

        assertArrayEquals(labels, perceptron.predict(features));
        assertTrue(perceptron.hasConverged());
        assertTrue(perceptron.getEpochsRun() <= 20);
    }

    @Test
    void predictsUnseenSamples() {
        double[][] features = {{-2, -1}, {-1, -2}, {1, 2}, {2, 1}};
        int[] labels = {0, 0, 1, 1};

        Perceptron perceptron = new Perceptron(0.5, 20);
        perceptron.fit(features, labels);

        assertEquals(0, perceptron.predict(new double[] {-3, -1}));
        assertEquals(1, perceptron.predict(new double[] {3, 1}));
    }

    @Test
    void batchPredictionMatchesIndividualPredictions() {
        double[][] features = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] labels = {0, 0, 0, 1};
        double[][] samples = {{0, 0}, {1, 0}, {1, 1}};

        Perceptron perceptron = new Perceptron(1.0, 20);
        perceptron.fit(features, labels);

        assertArrayEquals(new int[] {0, 0, 1}, perceptron.predict(samples));
        int[] individualPredictions = {perceptron.predict(samples[0]), perceptron.predict(samples[1]), perceptron.predict(samples[2])};
        assertArrayEquals(individualPredictions, perceptron.predict(samples));
    }

    @Test
    void emptyBatchProducesEmptyPrediction() {
        Perceptron perceptron = new Perceptron(1.0, 10);
        perceptron.fit(new double[][] {{0}}, new int[] {0});

        assertArrayEquals(new int[] {}, perceptron.predict(new double[][] {}));
    }

    @Test
    void nonSeparableDataStopsAtEpochLimitWithoutConverging() {
        double[][] features = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] labels = {0, 1, 1, 0};

        Perceptron perceptron = new Perceptron(1.0, 8);
        perceptron.fit(features, labels);

        assertFalse(perceptron.hasConverged());
        assertEquals(8, perceptron.getEpochsRun());
    }

    @Test
    void fittingResetsPreviousModel() {
        Perceptron perceptron = new Perceptron(1.0, 20);
        perceptron.fit(new double[][] {{0}, {1}}, new int[] {0, 1});
        perceptron.fit(new double[][] {{0}, {1}}, new int[] {1, 0});

        assertArrayEquals(new int[] {1, 0}, perceptron.predict(new double[][] {{0}, {1}}));
    }

    @Test
    void weightsAreReturnedAsDefensiveCopy() {
        Perceptron perceptron = new Perceptron(1.0, 10);
        perceptron.fit(new double[][] {{0}, {1}}, new int[] {0, 1});

        double[] weights = perceptron.getWeights();
        weights[0] = 1000;

        assertEquals(1, perceptron.predict(new double[] {1}));
    }

    @Test
    void predictionBeforeFitThrows() {
        Perceptron perceptron = new Perceptron(1.0, 10);

        assertThrows(IllegalStateException.class, () -> perceptron.predict(new double[] {1}));
        assertThrows(IllegalStateException.class, () -> perceptron.predict(new double[][] {}));
        assertThrows(IllegalStateException.class, perceptron::getWeights);
        assertThrows(IllegalStateException.class, perceptron::getBias);
        assertThrows(IllegalStateException.class, perceptron::hasConverged);
        assertThrows(IllegalStateException.class, perceptron::getEpochsRun);
    }

    @Test
    void invalidHyperparametersThrow() {
        assertThrows(IllegalArgumentException.class, () -> new Perceptron(0.0, 10));
        assertThrows(IllegalArgumentException.class, () -> new Perceptron(-1.0, 10));
        assertThrows(IllegalArgumentException.class, () -> new Perceptron(Double.NaN, 10));
        assertThrows(IllegalArgumentException.class, () -> new Perceptron(Double.POSITIVE_INFINITY, 10));
        assertThrows(IllegalArgumentException.class, () -> new Perceptron(1.0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Perceptron(1.0, -1));
    }

    @Test
    void invalidTrainingDataThrows() {
        Perceptron perceptron = new Perceptron(1.0, 10);

        assertThrows(IllegalArgumentException.class, () -> perceptron.fit(null, new int[] {0}));
        assertThrows(IllegalArgumentException.class, () -> perceptron.fit(new double[][] {{0}}, null));
        assertThrows(IllegalArgumentException.class, () -> perceptron.fit(new double[][] {}, new int[] {}));
        assertThrows(IllegalArgumentException.class, () -> perceptron.fit(new double[][] {{0}}, new int[] {}));
        assertThrows(IllegalArgumentException.class, () -> perceptron.fit(new double[][] {{0}, {1, 2}}, new int[] {0, 1}));
        assertThrows(IllegalArgumentException.class, () -> perceptron.fit(new double[][] {null}, new int[] {0}));
        assertThrows(IllegalArgumentException.class, () -> perceptron.fit(new double[][] {{}}, new int[] {0}));
        assertThrows(IllegalArgumentException.class, () -> perceptron.fit(new double[][] {{0}}, new int[] {2}));
        assertThrows(IllegalArgumentException.class, () -> perceptron.fit(new double[][] {{Double.NaN}}, new int[] {0}));
        assertThrows(IllegalArgumentException.class, () -> perceptron.fit(new double[][] {{Double.POSITIVE_INFINITY}}, new int[] {0}));
    }

    @Test
    void invalidPredictionDataThrows() {
        Perceptron perceptron = new Perceptron(1.0, 10);
        perceptron.fit(new double[][] {{0, 0}}, new int[] {0});

        assertThrows(IllegalArgumentException.class, () -> perceptron.predict((double[]) null));
        assertThrows(IllegalArgumentException.class, () -> perceptron.predict(new double[] {0}));
        assertThrows(IllegalArgumentException.class, () -> perceptron.predict(new double[] {0, Double.NaN}));
        assertThrows(IllegalArgumentException.class, () -> perceptron.predict((double[][]) null));
        assertThrows(IllegalArgumentException.class, () -> perceptron.predict(new double[][] {{0, 0}, null}));
    }
}
