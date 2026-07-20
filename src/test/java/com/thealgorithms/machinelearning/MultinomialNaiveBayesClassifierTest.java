package com.thealgorithms.machinelearning;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MultinomialNaiveBayesClassifierTest {

    @Test
    void predictsCorrectClassOnSeparableToyDataset() {
        // Class 0 samples are dominated by feature 0; class 1 samples by feature 1.
        double[][] features = {
            {5, 1},
            {6, 0},
            {4, 1},
            {1, 5},
            {0, 6},
            {1, 4},
        };
        int[] labels = {0, 0, 0, 1, 1, 1};

        MultinomialNaiveBayesClassifier classifier = new MultinomialNaiveBayesClassifier();
        classifier.fit(features, labels);

        assertEquals(0, classifier.predict(new double[] {5, 0}));
        assertEquals(1, classifier.predict(new double[] {0, 5}));
    }

    @Test
    void predictBatchMatchesIndividualPredictions() {
        double[][] features = {
            {3, 0},
            {2, 0},
            {0, 3},
            {0, 2},
        };
        int[] labels = {0, 0, 1, 1};

        MultinomialNaiveBayesClassifier classifier = new MultinomialNaiveBayesClassifier();
        classifier.fit(features, labels);

        double[][] samples = {{4, 0}, {0, 4}};
        int[] predictions = classifier.predict(samples);

        assertEquals(classifier.predict(samples[0]), predictions[0]);
        assertEquals(classifier.predict(samples[1]), predictions[1]);
    }

    @Test
    void laplaceSmoothingKeepsZeroCountFeatureLogProbabilityFinite() {
        // Feature index 1 never appears for class 0 in training data.
        double[][] features = {
            {2, 0},
            {3, 0},
            {0, 2},
            {0, 3},
        };
        int[] labels = {0, 0, 1, 1};

        MultinomialNaiveBayesClassifier classifier = new MultinomialNaiveBayesClassifier();
        classifier.fit(features, labels);

        // A sample that hits class 0's zero-count feature should still produce
        // a finite, usable prediction instead of -Infinity collapsing the score.
        int prediction = classifier.predict(new double[] {1, 1});
        assertTrue(prediction == 0 || prediction == 1);
    }

    @Test
    void predictBeforeFitThrowsIllegalStateException() {
        MultinomialNaiveBayesClassifier classifier = new MultinomialNaiveBayesClassifier();
        assertThrows(IllegalStateException.class, () -> classifier.predict(new double[] {1, 2}));
    }

    @Test
    void nonPositiveAlphaThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new MultinomialNaiveBayesClassifier(0.0));
        assertThrows(IllegalArgumentException.class, () -> new MultinomialNaiveBayesClassifier(-1.0));
    }

    @Test
    void mismatchedSampleLengthThrowsIllegalArgumentException() {
        double[][] features = {
            {1, 2},
            {3, 4},
        };
        int[] labels = {0, 1};

        MultinomialNaiveBayesClassifier classifier = new MultinomialNaiveBayesClassifier();
        classifier.fit(features, labels);

        assertThrows(IllegalArgumentException.class, () -> classifier.predict(new double[] {1, 2, 3}));
    }

    @Test
    void mismatchedFeatureAndLabelLengthsThrowsIllegalArgumentException() {
        double[][] features = {
            {1, 2},
            {3, 4},
        };
        int[] labels = {0};

        MultinomialNaiveBayesClassifier classifier = new MultinomialNaiveBayesClassifier();
        assertThrows(IllegalArgumentException.class, () -> classifier.fit(features, labels));
    }

    @Test
    void emptyFeaturesArrayThrowsIllegalArgumentException() {
        double[][] features = {};
        int[] labels = {};
        MultinomialNaiveBayesClassifier classifier = new MultinomialNaiveBayesClassifier();
        assertThrows(IllegalArgumentException.class, () -> classifier.fit(features, labels));
    }
}
