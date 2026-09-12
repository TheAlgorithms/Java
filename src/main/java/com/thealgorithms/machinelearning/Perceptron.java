package com.thealgorithms.machinelearning;

/**
 * A binary Perceptron classifier.
 *
 * <p>The Perceptron is a single-layer neural network that learns a linear
 * decision boundary. It updates its weights whenever a training sample is
 * misclassified. Convergence is guaranteed for linearly separable data, but
 * training stops after the configured epoch limit for non-separable data.
 * Labels must be either {@code 0} or {@code 1}.
 *
 * <p>The prediction rule is {@code 1} when the weighted sum plus bias is
 * greater than or equal to zero, and {@code 0} otherwise. For a
 * misclassified sample, the update is {@code weight += learningRate * error *
 * feature} and {@code bias += learningRate * error}, where {@code error} is
 * the true label minus the prediction.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Perceptron">Perceptron</a>
 */
public final class Perceptron {
    private final double learningRate;
    private final int maxEpochs;
    private double[] weights;
    private double bias;
    private int numFeatures;
    private int epochsRun;
    private boolean converged;

    /**
     * Constructs a Perceptron with the given training hyperparameters.
     *
     * @param learningRate positive step size used for each update
     * @param maxEpochs positive maximum number of passes over the training data
     * @throws IllegalArgumentException if a hyperparameter is invalid
     */
    public Perceptron(double learningRate, int maxEpochs) {
        if (!Double.isFinite(learningRate) || learningRate <= 0.0) {
            throw new IllegalArgumentException("learningRate must be finite and greater than 0");
        }
        if (maxEpochs <= 0) {
            throw new IllegalArgumentException("maxEpochs must be greater than 0");
        }
        this.learningRate = learningRate;
        this.maxEpochs = maxEpochs;
    }

    /**
     * Fits the classifier using binary training labels.
     *
     * <p>Fitting resets the weights and bias to zero before training. The
     * method records whether an entire epoch completed without an update.
     *
     * @param features training feature vectors
     * @param labels corresponding binary labels, each either {@code 0} or
     *                {@code 1}
     * @throws IllegalArgumentException if the training data is invalid
     */
    public void fit(double[][] features, int[] labels) {
        validateTrainingData(features, labels);

        numFeatures = features[0].length;
        weights = new double[numFeatures];
        bias = 0.0;
        epochsRun = 0;
        converged = false;

        for (int epoch = 0; epoch < maxEpochs; epoch++) {
            boolean updated = false;

            for (int sampleIndex = 0; sampleIndex < features.length; sampleIndex++) {
                int prediction = predict(features[sampleIndex]);
                int error = labels[sampleIndex] - prediction;

                if (error != 0) {
                    update(features[sampleIndex], error);
                    updated = true;
                }
            }

            epochsRun = epoch + 1;
            if (!updated) {
                converged = true;
                break;
            }
        }
    }

    /**
     * Predicts the binary label for one sample.
     *
     * @param sample feature vector to classify
     * @return {@code 0} or {@code 1}
     * @throws IllegalStateException if the classifier has not been fitted
     * @throws IllegalArgumentException if the sample is invalid
     */
    public int predict(double[] sample) {
        ensureFitted();
        validateSample(sample);

        double weightedSum = bias;
        for (int featureIndex = 0; featureIndex < numFeatures; featureIndex++) {
            weightedSum += weights[featureIndex] * sample[featureIndex];
        }
        return weightedSum >= 0.0 ? 1 : 0;
    }

    /**
     * Predicts binary labels for a batch of samples.
     *
     * @param samples feature vectors to classify
     * @return one prediction for each sample
     * @throws IllegalStateException if the classifier has not been fitted
     * @throws IllegalArgumentException if the batch or one of its samples is
     *                                  invalid
     */
    public int[] predict(double[][] samples) {
        ensureFitted();
        if (samples == null) {
            throw new IllegalArgumentException("samples cannot be null");
        }

        int[] predictions = new int[samples.length];
        for (int sampleIndex = 0; sampleIndex < samples.length; sampleIndex++) {
            predictions[sampleIndex] = predict(samples[sampleIndex]);
        }
        return predictions;
    }

    /**
     * Returns a defensive copy of the learned feature weights.
     *
     * @return learned weights in feature order
     * @throws IllegalStateException if the classifier has not been fitted
     */
    public double[] getWeights() {
        ensureFitted();
        return weights.clone();
    }

    /**
     * Returns the learned bias term.
     *
     * @return learned bias
     * @throws IllegalStateException if the classifier has not been fitted
     */
    public double getBias() {
        ensureFitted();
        return bias;
    }

    /**
     * Reports whether training completed with an update-free epoch.
     *
     * @return {@code true} if an epoch completed without an update
     * @throws IllegalStateException if the classifier has not been fitted
     */
    public boolean hasConverged() {
        ensureFitted();
        return converged;
    }

    /**
     * Returns the number of epochs performed by the last fit.
     *
     * @return number of completed epochs
     * @throws IllegalStateException if the classifier has not been fitted
     */
    public int getEpochsRun() {
        ensureFitted();
        return epochsRun;
    }

    private void update(double[] sample, int error) {
        for (int featureIndex = 0; featureIndex < numFeatures; featureIndex++) {
            weights[featureIndex] += learningRate * error * sample[featureIndex];
        }
        bias += learningRate * error;
    }

    private void ensureFitted() {
        if (weights == null) {
            throw new IllegalStateException("classifier has not been fitted");
        }
    }

    private void validateTrainingData(double[][] features, int[] labels) {
        if (features == null || labels == null) {
            throw new IllegalArgumentException("features and labels cannot be null");
        }
        if (features.length == 0 || labels.length == 0) {
            throw new IllegalArgumentException("features and labels cannot be empty");
        }
        if (features.length != labels.length) {
            throw new IllegalArgumentException("features and labels must have the same length");
        }
        if (features[0] == null || features[0].length == 0) {
            throw new IllegalArgumentException("feature vectors cannot be null or empty");
        }

        int featureCount = features[0].length;
        for (int sampleIndex = 0; sampleIndex < features.length; sampleIndex++) {
            double[] sample = features[sampleIndex];
            if (sample == null || sample.length != featureCount) {
                throw new IllegalArgumentException("all feature vectors must have the same dimension");
            }
            validateFiniteValues(sample);
            if (labels[sampleIndex] != 0 && labels[sampleIndex] != 1) {
                throw new IllegalArgumentException("labels must be either 0 or 1");
            }
        }
    }

    private void validateSample(double[] sample) {
        if (sample == null || sample.length != numFeatures) {
            throw new IllegalArgumentException("sample must match the training feature dimension");
        }
        validateFiniteValues(sample);
    }

    private static void validateFiniteValues(double[] values) {
        for (double value : values) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("feature values must be finite");
            }
        }
    }
}
