package com.thealgorithms.backtracking;


// Author: Jivan Jamdar 
/*
K-Means Clustering

Problem Description:
K-Means clustering is an unsupervised machine learning algorithm used to partition a set of data points into 𝑘
k distinct clusters based on feature similarity. The algorithm aims to minimize the variance within each cluster while maximizing the variance between clusters.

|| Steps of K-Means Algorithm ||

1) Initialization: Randomly select 𝑘 data points as initial centroids.
2) Assignment: Assign each data point to the nearest centroid, forming 𝑘 clusters.
3) Update: Recalculate the centroids of the clusters based on the assigned points.
4) Repeat: Repeat the assignment and update steps until the centroids no longer change or a maximum number of iterations is reached.

(e.g.)
Data Points:

(1.0, 2.0)
(1.5, 1.8)
(5.0, 8.0)
(8.0, 8.0)
(1.0, 0.6)
(9.0, 11.0)
(8.0, 2.0)
(10.0, 2.0)
(9.0, 3.0)
K = 2 (Number of clusters)

Initial Centroids (Randomly selected):

Centroid 1: (1.0, 2.0)
Centroid 2: (5.0, 8.0)

Final Clusters After Execution:

Cluster 1:

(1.0, 2.0)
(1.5, 1.8)
(1.0, 0.6)
(8.0, 2.0)
(10.0, 2.0)
(9.0, 3.0)

Cluster 2:

(5.0, 8.0)
(8.0, 8.0)
(9.0, 11.0)

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class KMeans {

    // Method to perform K-Means clustering
    public List<List<double[]>> kMeans(double[][] data, int k, int maxIterations) {
        int n = data.length;
        int dimensions = data[0].length;
        double[][] centroids = new double[k][dimensions];
        Random rand = new Random();

        // Step 1: Initialize centroids randomly
        for (int i = 0; i < k; i++) {
            centroids[i] = data[rand.nextInt(n)];
        }

        List<List<double[]>> clusters = new ArrayList<>();

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            // Step 2: Assign data points to nearest centroid
            clusters = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                clusters.add(new ArrayList<>());
            }

            for (double[] point : data) {
                int nearestCentroid = getNearestCentroid(point, centroids);
                clusters.get(nearestCentroid).add(point);
            }

            // Step 3: Recalculate centroids
            boolean centroidsChanged = false;
            for (int i = 0; i < k; i++) {
                double[] newCentroid = calculateCentroid(clusters.get(i), dimensions);
                if (!Arrays.equals(centroids[i], newCentroid)) {
                    centroids[i] = newCentroid;
                    centroidsChanged = true;
                }
            }

            // Step 4: Stop if centroids didn't change
            if (!centroidsChanged) {
                break;
            }
        }

        return clusters;
    }

    // Helper method to calculate the centroid of a cluster
    private double[] calculateCentroid(List<double[]> cluster, int dimensions) {
        double[] centroid = new double[dimensions];
        for (double[] point : cluster) {
            for (int i = 0; i < dimensions; i++) {
                centroid[i] += point[i];
            }
        }
        for (int i = 0; i < dimensions; i++) {
            centroid[i] /= cluster.size();
        }
        return centroid;
    }

    // Helper method to find the nearest centroid for a given point
    private int getNearestCentroid(double[] point, double[][] centroids) {
        double minDistance = Double.MAX_VALUE;
        int nearest = 0;

        for (int i = 0; i < centroids.length; i++) {
            double distance = calculateDistance(point, centroids[i]);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = i;
            }
        }

        return nearest;
    }

    // Helper method to calculate Euclidean distance between two points
    private double calculateDistance(double[] point1, double[] point2) {
        double sum = 0;
        for (int i = 0; i < point1.length; i++) {
            sum += Math.pow(point1[i] - point2[i], 2);
        }
        return Math.sqrt(sum);
    }
}
