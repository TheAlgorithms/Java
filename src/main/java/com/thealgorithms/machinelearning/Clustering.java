package com.thealgorithms.machinelearning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Centroid-based partitional clustering algorithms.
 *
 * <p>This class currently provides two Lloyd-style iterative clustering algorithms that share
 * the same assign/update/converge loop and differ only in their distance metric and how a
 * cluster's center is recomputed:
 *
 * <ul>
 *   <li><b>K-Means</b> — minimizes squared Euclidean distance; each center is the
 *       coordinate-wise <i>mean</i> of its cluster. Fast and simple, but sensitive to
 *       outliers.</li>
 *   <li><b>K-Medians</b> — minimizes Manhattan (L1) distance; each center is the
 *       coordinate-wise <i>median</i> of its cluster. More robust to outliers than K-Means,
 *       at the cost of an O(n log n) sort per dimension during each update step.</li>
 * </ul>
 *
 * <p>Both algorithms:
 * <ol>
 *   <li>Start from a set of {@code k} centers (supplied explicitly, or sampled from the
 *       dataset using a seeded {@link Random} for reproducibility).</li>
 *   <li><b>Assignment step:</b> assign every point to its nearest center.</li>
 *   <li><b>Update step:</b> recompute each center from the points assigned to it.</li>
 *   <li>Repeat steps 2-3 until no point changes cluster, every center moves less than a given
 *       tolerance, or a maximum number of iterations is reached.</li>
 * </ol>
 *
 * <p><b>Time complexity:</b> O(n * k * d * iterations) for K-Means;
 * O(n * k * d * iterations + k * d * n log n) for K-Medians (due to the per-dimension sort
 * used to compute the median).
 *
 * <p><b>Limitations (both algorithms):</b>
 * <ul>
 *   <li>Sensitive to the initial choice of centers; poor initialization can converge to a
 *       suboptimal local minimum (see k-means++ for a smarter seeding strategy).</li>
 *   <li>The number of clusters {@code k} must be chosen in advance.</li>
 *   <li>Assume clusters are roughly convex and similarly sized/dense.</li>
 * </ul>
 *
 * @see <a href="https://en.wikipedia.org/wiki/K-means_clustering">K-means clustering (Wikipedia)</a>
 * @see <a href="https://en.wikipedia.org/wiki/K-medians_clustering">K-medians clustering (Wikipedia)</a>
 */
public final class Clustering {

    private Clustering() {
        // Utility class: only static entry points are exposed.
    }

    // ------------------------------------------------------------------
    //  K-Means
    // ------------------------------------------------------------------

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
        double[][] centers = validateAndCopyCenters(points, initialCentroids);
        return run(points, centers, maxIterations, tolerance, Clustering::squaredEuclideanDistance, Clustering::mean);
    }

    /**
     * Runs K-Means, sampling {@code k} distinct points from the dataset (via a seeded
     * {@link Random}) as initial centroids. Reproducible across runs given the same seed.
     *
     * @param points        the dataset to cluster; non-empty, at least {@code k} points
     * @param k             the number of clusters; must be positive and &le; number of points
     * @param seed          seed used to pick initial centroids
     * @param maxIterations maximum number of iterations; must be positive
     * @param tolerance     convergence tolerance on center movement; must be non-negative
     * @return the clustering result
     */
    public static ClusteringResult kMeans(double[][] points, int k, long seed, int maxIterations, double tolerance) {
        validateParameters(maxIterations, tolerance);
        double[][] centers = randomInitialCenters(points, k, seed);
        return run(points, centers, maxIterations, tolerance, Clustering::squaredEuclideanDistance, Clustering::mean);
    }

    // ------------------------------------------------------------------
    //  K-Medians
    // ------------------------------------------------------------------

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
        double[][] centers = validateAndCopyCenters(points, initialCenters);
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
        double[][] centers = randomInitialCenters(points, k, seed);
        return run(points, centers, maxIterations, tolerance, Clustering::manhattanDistance, Clustering::median);
    }

    // ------------------------------------------------------------------
    // Shared iterative core
    // ------------------------------------------------------------------

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

    // ------------------------------------------------------------------
    // Distance functions
    // ------------------------------------------------------------------

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

    // ------------------------------------------------------------------
    // Center functions
    // ------------------------------------------------------------------

    private static double[] mean(Collection<double[]> clusterPoints, int dimension) {
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

    // ------------------------------------------------------------------
    // Validation & initialization helpers
    // ------------------------------------------------------------------

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

    private static double[][] validateAndCopyCenters(double[][] points, double[][] initialCenters) {
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

    private static double[][] randomInitialCenters(double[][] points, int k, long seed) {
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

    // ------------------------------------------------------------------
    // Result holder
    // ------------------------------------------------------------------

    /**
     * The outcome of a clustering run: final centers, per-point cluster labels, and metadata
     * about how the run terminated.
     */
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

        /** Returns whether the algorithm converged before hitting {@code maxIterations}. */
        public boolean hasConverged() {
            return converged;
        }
    }
}
