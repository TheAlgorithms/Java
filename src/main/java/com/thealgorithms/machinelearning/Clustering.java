package com.thealgorithms.machinelearning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public final class Clustering {

    private Clustering() {

    }
    /**
     * Runs K-Means using explicit, caller-supplied initial centroids. Deterministic — the
     * recommended entry point for reproducible results and tests.
     *
     * @param points           the dataset to cluster; non-empty, consistent dimensionality
     * @param initialCentroids exactly {@code k} initial centroids, matching {@code points}'
     *                         dimensionality
     * @param maxIterations    maximum number of iterations; must be positive
     * @param tolerance        convergence tolerance on center movement; must be non-negative
     * @return the clustering result
     */

    public static ClusteringResult kMeans(double[][] points, double[][] initialCentroids, int maxIterations, double tolerance) {
        validateParameters(maxIterations, tolerance);
        double[][] centroids = validateAndCopyCentroids(points, initialCentroids);
        return run(points, centroids, maxIterations, tolerance, Clustering::squaredEuclideanDistance, Clustering::mean);
    }
    public static ClusteringResult kMeans(double[][] points, int k, long seed, int maxIterations, double tolerance) {
        validateParameters(maxIterations, tolerance);
        double[][] centroids = randomInitialCentroids(points, k, seed);
        return run(points, centroids, maxIterations, tolerance, Clustering::squaredEuclideanDistance, Clustering::mean);
    }

    /**
     * Runs K-Medians using explicit, caller-supplied initial centers. Deterministic — the
     * recommended entry point for reproducible results and tests.
     *
     * @param points        the dataset to cluster; non-empty, consistent dimensionality
     * @param initialCenters exactly {@code k} initial centers, matching {@code points}'
     *                       dimensionality
     * @param maxIterations maximum number of iterations; must be positive
     * @param tolerance     convergence tolerance on center movement; must be non-negative
     * @return the clustering result
     */

    public static ClusteringResult kMedians(double[][] points, double[][] initialCenters, int maxIterations, double tolerance) {
        validateParameters(maxIterations, tolerance);
        double[][] centers = validateAndCopyCentroids(points, initialCenters);
        return run(points, centers, maxIterations, tolerance, Clustering::manhattanDistance, Clustering::median);
    }

    /**
     * Runs K-Medians, sampling {@code k} distinct points from the dataset (via a seeded
     * {@link Random}) as initial centers. Reproducible across runs given the same seed.
     *
     * @param points        the dataset to cluster; non-empty, at least {@code k} points
     * @param k             the number of clusters; must be positive and &le; number of points
     * @param seed          seed used to pick initial centers
     * @param maxIterations maximum number of iterations; must be positive
     * @param tolerance     convergence tolerance on center movement; must be non-negative
     * @return the clustering result
     */

    public static ClusteringResult kMedians(double[][] points, int k, long seed, int maxIterations, double tolerance) {
        validateParameters(maxIterations, tolerance);
        double[][] centers = randomInitialCentroids(points, k, seed);
        return run(points, centers, maxIterations, tolerance, Clustering::manhattanDistance, Clustering::median);
    }


    @FunctionalInterface
    private interface DistanceFunction {
        double distance(double[] a, double[] b);
    }

    @FunctionalInterface
    private interface CenterFunction {
        double[] center(List<double[]> clusterPoints, int dimension);
    }

    private static ClusteringResult run(double[][] points, double[][] initialCenters, int maxIterations, double tolerance, DistanceFunction assignmentDistance, CenterFunction centerFunction) {
        int n = points.length;
        int k = initialCenters.length;
        int dimension = points[0].length;
        double[][] centers = initialCenters;
        int[] labels = new int[n];
        Arrays.fill(labels, -1);

        int iteration = 0;
        boolean converged = false;

        while (iteration < maxIterations && !converged) {
            boolean anyAssignmentChanged = assign(points, centers, labels, assignmentDistance);
            double[][] newCenters = updateCenters(points, labels, centers, k, dimension, centerFunction);
            double maxShift = maxCenterShift(centers, newCenters);
            centers = newCenters;
            iteration++;
            converged = !anyAssignmentChanged || maxShift < tolerance;
        }

        return new ClusteringResult(centers, labels, iteration, converged);
    }

    private static boolean assign(double[][] points, double[][] centers, int[] labels, DistanceFunction distanceFunction) {
        boolean changed = false;
        for (int i = 0; i < points.length; i++) {
            int best = 0;
            double bestDist = distanceFunction.distance(points[i], centers[0]);
            for (int c = 1; c < centers.length; c++) {
                double dist = distanceFunction.distance(points[i], centers[c]);
                if (dist < bestDist) {
                    bestDist = dist;
                    best = c;
                }
            }
            if (labels[i] != best) {
                labels[i] = best;
                changed = true;
            }
        }
        return changed;
    }

    private static double[][] updateCenters(double[][] points, int[] labels, double[][] oldCenters, int k, int dimension, CenterFunction centerFunction) {
        List<List<double[]>> groups = new ArrayList<>();
        for (int c = 0; c < k; c++) {
            groups.add(new ArrayList<>());
        }
        for (int i = 0; i < points.length; i++) {
            groups.get(labels[i]).add(points[i]);
        }
        double[][] newCenters = new double[k][];
        for (int c = 0; c < k; c++) {
            if (groups.get(c).isEmpty()) {
                // Keep the previous center if the cluster lost all its points.
                newCenters[c] = Arrays.copyOf(oldCenters[c], dimension);
            } else {
                newCenters[c] = centerFunction.center(groups.get(c), dimension);
            }
        }
        return newCenters;
    }

    private static double maxCenterShift(double[][] oldCenters, double[][] newCenters) {
        double max = 0.0;
        for (int c = 0; c < oldCenters.length; c++) {
            max = Math.max(max, euclideanDistance(oldCenters[c], newCenters[c]));
        }
        return max;
    }

    private static double squaredEuclideanDistance(double[] a, double[] b) {
        double sum = 0.0;
        for (int d = 0; d < a.length; d++) {
            double diff = a[d] - b[d];
            sum += diff * diff;
        }
        return sum;
    }

    private static double euclideanDistance(double[] a, double[] b) {
        return Math.sqrt(squaredEuclideanDistance(a, b));
    }

    private static double manhattanDistance(double[] a, double[] b) {
        double sum = 0.0;
        for (int d = 0; d < a.length; d++) {
            sum += Math.abs(a[d] - b[d]);
        }
        return sum;
    }

    private static double[] mean(List<double[]> clusterPoints, int dimension) {
        double[] result = new double[dimension];
        for (double[] p : clusterPoints) {
            for (int d = 0; d < dimension; d++) {
                result[d] += p[d];
            }
        }
        for (int d = 0; d < dimension; d++) {
            result[d] /= clusterPoints.size();
        }
        return result;
    }

    private static double[] median(List<double[]> clusterPoints, int dimension) {
        int n = clusterPoints.size();
        double[] result = new double[dimension];
        double[] values = new double[n];
        for (int d = 0; d < dimension; d++) {
            for (int i = 0; i < n; i++) {
                values[i] = clusterPoints.get(i)[d];
            }
            Arrays.sort(values);
            if (n % 2 == 1) {
                result[d] = values[n / 2];
            } else {
                result[d] = (values[n / 2 - 1] + values[n / 2]) / 2.0;
            }
        }
        return result;
    }

    private static void validateParameters(int maxIterations, double tolerance) {
        if (maxIterations <= 0) {
            throw new IllegalArgumentException("maxIterations must be positive, got " + maxIterations);
        }
        if (tolerance < 0) {
            throw new IllegalArgumentException("tolerance must be non-negative, got " + tolerance);
        }
    }

    private static void validatePoints(double[][] points, int k) {
        if (points == null || points.length == 0) {
            throw new IllegalArgumentException("Dataset must not be empty");
        }
        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive, got " + k);
        }
        if (k > points.length) {
            throw new IllegalArgumentException("k (" + k + ") cannot exceed the number of points (" + points.length + ")");
        }
        int dimension = points[0].length;
        if (dimension == 0) {
            throw new IllegalArgumentException("Points must have at least one dimension");
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null || points[i].length != dimension) {
                throw new IllegalArgumentException("All points must share the same dimensionality; point " + i + " does not match");
            }
        }
    }

    private static double[][] validateAndCopyCentroids(double[][] points, double[][] initialCenters) {
        Objects.requireNonNull(initialCenters, "initial centers must not be null");
        validatePoints(points, initialCenters.length);
        int dimension = points[0].length;
        double[][] centers = new double[initialCenters.length][];
        for (int i = 0; i < initialCenters.length; i++) {
            if (initialCenters[i] == null || initialCenters[i].length != dimension) {
                throw new IllegalArgumentException("Initial center " + i + " has inconsistent dimensionality");
            }
            centers[i] = Arrays.copyOf(initialCenters[i], dimension);
        }
        return centers;
    }

    private static double[][] randomInitialCentroids(double[][] points, int k, long seed) {
        validatePoints(points, k);
        int[] indices = new int[points.length];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }
        Random random = new Random(seed);
        for (int i = indices.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int tmp = indices[i];
            indices[i] = indices[j];
            indices[j] = tmp;
        }
        double[][] centers = new double[k][];
        for (int i = 0; i < k; i++) {
            centers[i] = Arrays.copyOf(points[indices[i]], points[indices[i]].length);
        }
        return centers;
    }

    public static final class ClusteringResult {
        private final double[][] centers;
        private final int[] labels;
        private final int iterations;
        private final boolean converged;

        ClusteringResult(double[][] centers, int[] labels, int iterations, boolean converged) {
            this.centers = centers;
            this.labels = labels;
            this.iterations = iterations;
            this.converged = converged;
        }

        public double[][] getCenters() {
            double[][] copy = new double[centers.length][];
            for (int i = 0; i < centers.length; i++) {
                copy[i] = Arrays.copyOf(centers[i], centers[i].length);
            }
            return copy;
        }

        public int[] getLabels() {
            return Arrays.copyOf(labels, labels.length);
        }

        public int getIterations() {
            return iterations;
        }

        public boolean hasConverged() {
            return converged;
        }
    }
}