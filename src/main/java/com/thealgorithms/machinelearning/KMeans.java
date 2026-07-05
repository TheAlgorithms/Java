package com.thealgorithms.machinelearning;

/**
 * Implements the K-Means clustering algorithm using Lloyd's algorithm.
 *
 * <p>
 * K-Means partitions observations into k clusters by iteratively assigning each
 * point to its nearest centroid and recomputing centroid positions until
 * convergence or the maximum number of iterations is reached.
 */
public final class KMeans {

    private KMeans() {

        // Utility class
    }

    /**
     * Computes the squared Euclidean distance between two points.
     *
     * @param point1 first point
     * @param point2 second point
     * @return squared Euclidean distance
     */
    private static double squaredDistance(double[] point1, double[] point2) {
        double sum = 0.0;
        for (int i = 0; i < point1.length; i++) {
            double diff = point1[i] - point2[i];
            sum += diff * diff;
        }
        return sum;
    }

    /**
     * Finds the nearest centroid for the given point.
     *
     * @param point point to classify
     * @param centroids current centroids
     * @return index of the nearest centroid
     */
    private static int nearestCentroid(double[] point, double[][] centroids) {
        int nearest = 0;
        double minimumDistance = squaredDistance(point, centroids[0]);

        for (int i = 1; i < centroids.length; i++) {
            double distance = squaredDistance(point, centroids[i]);
            if (distance < minimumDistance) {
                minimumDistance = distance;
                nearest = i;
            }
        }

        return nearest;
    }

    /**
     * Clusters the given points using K-Means.
     *
     * @param points input data points
     * @param initialCentroids initial centroid positions
     * @param maxIterations maximum number of iterations
     * @param tolerance convergence tolerance
     * @return cluster assignment for each point
     * @throws IllegalArgumentException if the input is invalid
     */
    public static int[] cluster(
            double[][] points,
            double[][] initialCentroids,
            int maxIterations,
            double tolerance) {

        if (points == null || initialCentroids == null) {
            throw new IllegalArgumentException("Input arrays cannot be null.");
        }

        if (points.length == 0) {
            throw new IllegalArgumentException("Dataset cannot be empty.");
        }

        if (initialCentroids.length == 0) {
            throw new IllegalArgumentException("At least one centroid is required.");
        }

        if (initialCentroids.length > points.length) {
            throw new IllegalArgumentException("Number of centroids cannot exceed number of points.");
        }

        if (maxIterations <= 0) {
            throw new IllegalArgumentException("Maximum iterations must be positive.");
        }

        if (tolerance < 0) {
            throw new IllegalArgumentException("Tolerance cannot be negative.");
        }

        if (points[0] == null) {
            throw new IllegalArgumentException("Points cannot contain null rows.");
        }

        int dimensions = points[0].length;

        if (dimensions == 0) {
            throw new IllegalArgumentException("Points must have at least one dimension.");
        }

        for (double[] point : points) {
            if (point == null) {
                throw new IllegalArgumentException("Points cannot contain null rows.");
            }

            if (point.length != dimensions) {
                throw new IllegalArgumentException("All points must have the same dimension.");
            }
        }

        for (double[] centroid : initialCentroids) {
            if (centroid == null) {
                throw new IllegalArgumentException("Centroids cannot contain null rows.");
            }

            if (centroid.length != dimensions) {
                throw new IllegalArgumentException("Centroid dimensions must match point dimensions.");
            }
        }

        int k = initialCentroids.length;
        int[] assignments = new int[points.length];
        double[][] centroids = new double[k][dimensions];

        for (int i = 0; i < k; i++) {
            System.arraycopy(initialCentroids[i], 0, centroids[i], 0, dimensions);
        }

        boolean changed = true;
        int iterations = 0;

        while (changed && iterations < maxIterations) {
            changed = false;
            iterations++;

            // Assign points to nearest centroid
            for (int i = 0; i < points.length; i++) {
                int nearest = nearestCentroid(points[i], centroids);
                if (assignments[i] != nearest) {
                    assignments[i] = nearest;
                    changed = true;
                }
            }

            // Compute new centroids
            double[][] newCentroids = new double[k][dimensions];
            int[] clusterSizes = new int[k];

            for (int i = 0; i < points.length; i++) {
                int cluster = assignments[i];
                clusterSizes[cluster]++;

                for (int j = 0; j < dimensions; j++) {
                    newCentroids[cluster][j] += points[i][j];
                }
            }

            for (int i = 0; i < k; i++) {
                if (clusterSizes[i] == 0) {
                    System.arraycopy(centroids[i], 0, newCentroids[i], 0, dimensions);
                    continue;
                }

                for (int j = 0; j < dimensions; j++) {
                    newCentroids[i][j] /= clusterSizes[i];
                }
            }

            double maxShift = 0.0;

            for (int i = 0; i < k; i++) {
                double shift = squaredDistance(centroids[i], newCentroids[i]);
                if (shift > maxShift) {
                    maxShift = shift;
                }
            }

            centroids = newCentroids;

            if (maxShift <= tolerance * tolerance) {
                break;
            }
        }

        return assignments;
    }
}
