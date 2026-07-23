package com.thealgorithms.machinelearning;

import java.util.HashMap;
import java.util.Map;

/**
 * Multinomial Naive Bayes classifier.
 *
 * <p>Suited to discrete, count-based features (e.g. word frequencies in text
 * classification). Class priors and feature likelihoods are estimated from
 * training data with Laplace (add-alpha) smoothing to avoid zero
 * probabilities for unseen feature/class combinations. Predictions are made
 * by comparing summed log-probabilities across classes, which avoids the
 * numerical underflow that repeated multiplication of small probabilities
 * would cause.
 *
 * <p>Reference: <a href="https://en.wikipedia.org/wiki/Naive_Bayes_classifier">
 * Naive Bayes classifier</a>
 *
 * @author Vraj Prajapati(Rosander0)
 */
public final class MultinomialNaiveBayesClassifier {

    private final double alpha;
    private final Map<Integer, Double> logPriors;
    private final Map<Integer, double[]> logLikelihoods;
    private int numFeatures;

    /**
     * Constructs a classifier with the given Laplace smoothing parameter.
     *
     * @param alpha smoothing constant; must be greater than 0. A value of 1.0
     *              corresponds to standard Laplace smoothing.
     */
    public MultinomialNaiveBayesClassifier(double alpha) {
        if (alpha <= 0) {
            throw new IllegalArgumentException("alpha must be greater than 0");
        }
        this.alpha = alpha;
        this.logPriors = new HashMap<>();
        this.logLikelihoods = new HashMap<>();
    }

    /** Constructs a classifier using the standard Laplace smoothing constant of 1.0. */
    public MultinomialNaiveBayesClassifier() {
        this(1.0);
    }

    /**
     * Fits the classifier on the given feature matrix and labels.
     *
     * @param features training samples, each row a vector of non-negative
     *                 feature counts
     * @param labels   class label for each row of {@code features}
     */
    public void fit(double[][] features, int[] labels) {
        if (features.length == 0 || features.length != labels.length) {
            throw new IllegalArgumentException("features and labels must be non-empty and of equal length");
        }
        numFeatures = features[0].length;

        Map<Integer, Integer> classCounts = new HashMap<>();
        Map<Integer, double[]> featureSums = new HashMap<>();
        Map<Integer, Double> totalFeatureCount = new HashMap<>();

        for (int i = 0; i < features.length; i++) {
            int label = labels[i];
            classCounts.merge(label, 1, Integer::sum);
            double[] sums = featureSums.computeIfAbsent(label, k -> new double[numFeatures]);
            double total = totalFeatureCount.getOrDefault(label, 0.0);
            for (int j = 0; j < numFeatures; j++) {
                sums[j] += features[i][j];
                total += features[i][j];
            }
            totalFeatureCount.put(label, total);
        }

        int totalSamples = features.length;
        for (Map.Entry<Integer, double[]> entry : featureSums.entrySet()) {
            int label = entry.getKey();
            double[] sums = entry.getValue();
            int count = classCounts.getOrDefault(label, 0);
            double total = totalFeatureCount.getOrDefault(label, 0.0);

            logPriors.put(label, Math.log((double) count / totalSamples));

            double denom = total + alpha * numFeatures;
            double[] logLikelihood = new double[numFeatures];
            for (int j = 0; j < numFeatures; j++) {
                logLikelihood[j] = Math.log((sums[j] + alpha) / denom);
            }
            logLikelihoods.put(label, logLikelihood);
        }
    }

    /**
     * Predicts the most likely class for a single sample.
     *
     * @param sample feature vector of non-negative counts
     * @return the predicted class label
     */
    public int predict(double[] sample) {
        if (logPriors.isEmpty()) {
            throw new IllegalStateException("classifier has not been fitted");
        }
        if (sample.length != numFeatures) {
            throw new IllegalArgumentException("sample length must match training feature count");
        }

        int bestLabel = -1;
        double bestScore = Double.NEGATIVE_INFINITY;

        for (Map.Entry<Integer, double[]> entry : logLikelihoods.entrySet()) {
            int label = entry.getKey();
            double[] logLikelihood = entry.getValue();
            double score = logPriors.getOrDefault(label, Double.NEGATIVE_INFINITY);
            for (int j = 0; j < numFeatures; j++) {
                score += sample[j] * logLikelihood[j];
            }
            if (score > bestScore) {
                bestScore = score;
                bestLabel = label;
            }
        }
        return bestLabel;
    }

    /**
     * Predicts class labels for a batch of samples.
     *
     * @param samples feature vectors of non-negative counts
     * @return predicted class label for each row of {@code samples}
     */
    public int[] predict(double[][] samples) {
        int[] predictions = new int[samples.length];
        for (int i = 0; i < samples.length; i++) {
            predictions[i] = predict(samples[i]);
        }
        return predictions;
    }
}
