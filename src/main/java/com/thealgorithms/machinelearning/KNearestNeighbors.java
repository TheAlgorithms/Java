package com.thealgorithms.machinelearning;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * K-Nearest Neighbors (KNN) classifier.
 *
 * <p>
 * K-Nearest Neighbors is a supervised machine learning algorithm that
 * classifies a sample based on the majority class among its {@code k}
 * nearest training samples using the Euclidean distance metric.
 *
 * <p>
 * The classifier stores the training dataset during the fitting phase and
 * predicts class labels for new samples without building an explicit model.
 *
 * @see <a href="https://en.wikipedia.org/wiki/K-nearest_neighbors_algorithm">
 *      K-Nearest Neighbors</a>
 */
public final class KNearestNeighbors {
    private final int k;

    /**
     * Constructs a K-Nearest Neighbors classifier with the specified number
     * of neighbors.
     *
     * @param k the number of nearest neighbors to consider during prediction
     */
    public KNearestNeighbors(int k) {

        if (k <= 0) {
            throw new IllegalArgumentException("k must be greater than 0.");
        }

        this.k = k;
    }

    /**
     * Represents a neighboring training sample and its distance from the test
     * sample.
     */
    private static final class Neighbor {

        private final double distance;

        private final int label;

        Neighbor(double distance, int label) {
            this.distance = distance;
            this.label = label;
        }
    }

    private double[][] trainingFeatures;
    private int[] trainingLabels;
    private int numFeatures;

    /**
     * Fits the classifier using the provided training dataset.
     *
     * <p>
     * The training feature vectors and their corresponding class labels are
     * stored for use during prediction.
     *
     * @param features the training feature vectors
     * @param labels   the corresponding class labels
     */
    public void fit(double[][] features, int[] labels) {

        if (features == null || labels == null) {
            throw new IllegalArgumentException("Features and labels cannot be null.");
        }

        if (features.length == 0 || labels.length == 0) {
            throw new IllegalArgumentException("Features and labels cannot be empty.");
        }

        if (features.length != labels.length) {
            throw new IllegalArgumentException("Features and labels must have the same length.");
        }

        if (features[0] == null) {
            throw new IllegalArgumentException("Feature vectors cannot be null.");
        }

        numFeatures = features[0].length;

        if (numFeatures == 0) {
            throw new IllegalArgumentException("Feature vectors cannot be empty.");
        }

        for (double[] sample : features) {
            if (sample == null) {
                throw new IllegalArgumentException("Feature vectors cannot be null.");
            }

            if (sample.length != numFeatures) {
                throw new IllegalArgumentException("All feature vectors must have the same dimension.");
            }
        }

        this.trainingFeatures = features;
        this.trainingLabels = labels;
    }

    /**
     * Computes the Euclidean distance between two feature vectors.
     *
     * @param first  the first feature vector
     * @param second the second feature vector
     * @return the Euclidean distance between the two vectors
     */
    private static double euclideanDistance(double[] first, double[] second) {
        double sum = 0.0;

        for (int i = 0; i < first.length; i++) {
            double difference = first[i] - second[i];
            sum += difference * difference;
        }

        return Math.sqrt(sum);
    }

    /**
     * Predicts the class label for a single sample.
     *
     * <p>
     * The prediction is made by finding the {@code k} nearest neighbors
     * among the training samples and selecting the class with the highest
     * number of votes. In the event of a tie, the smaller class label is
     * returned.
     *
     * @param testPoint the sample to classify
     * @return the predicted class label
     */
    public int predict(double[] testPoint) {
        if (trainingFeatures == null) {
            throw new IllegalStateException("Classifier has not been fitted.");
        }

        if (trainingLabels == null) {
            throw new IllegalStateException("Classifier has not been fitted.");
        }

        if (testPoint == null) {
            throw new IllegalArgumentException("Sample cannot be null.");
        }

        if (testPoint.length != numFeatures) {
            throw new IllegalArgumentException("Sample length must match training feature count.");
        }

        List<Neighbor> neighbors = new ArrayList<>(trainingFeatures.length);

        for (int i = 0; i < trainingFeatures.length; i++) {
            double distance = euclideanDistance(trainingFeatures[i], testPoint);
            neighbors.add(new Neighbor(distance, trainingLabels[i]));
        }

        neighbors.sort(Comparator.comparingDouble(neighbor -> neighbor.distance));

        Map<Integer, Integer> votes = new HashMap<>();

        if (k > trainingFeatures.length) {
            throw new IllegalArgumentException("k cannot be greater than the number of training samples.");
        }

        for (int i = 0; i < k; i++) {
            int label = neighbors.get(i).label;
            votes.merge(label, 1, Integer::sum);
        }

        int predictedLabel = -1;
        int maxVotes = -1;

        for (Map.Entry<Integer, Integer> entry : votes.entrySet()) {
            int label = entry.getKey();
            int count = entry.getValue();

            if (count > maxVotes || (count == maxVotes && label < predictedLabel)) {
                maxVotes = count;
                predictedLabel = label;
            }
        }

        return predictedLabel;
    }

    /**
     * Predicts class labels for multiple samples.
     *
     * @param samples the samples to classify
     * @return an array containing the predicted class label for each sample
     */
    public int[] predict(double[][] samples) {

        if (samples == null) {
            throw new IllegalArgumentException("Samples cannot be null.");
        }

        int[] predictions = new int[samples.length];

        for (int i = 0; i < samples.length; i++) {
            predictions[i] = predict(samples[i]);
        }

        return predictions;
    }
}
