package com.thealgorithms.machinelearning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KNN {
    public static class DataPoint {
        double[] features;
        String label;

        public DataPoint(double[] features, String label) {
            this.features = features;
            this.label = label;
        }
    }

    private static class DistancePair {
        double distance;
        String label;

        public DistancePair(double distance, String label) {
            this.distance = distance;
            this.label = label;
        }
    }

    public static double calculateEuclideanDistance(double[] point1, double[] point2) {
        double sum = 0.0;
        for (int i = 0; i < point1.length; i++) {
            sum += Math.pow(point1[i] - point2[i], 2);
        }
        return Math.sqrt(sum);
    }

    public static String classify(List<DataPoint> dataset, double[] queryPoint, int k) {
        List<DistancePair> distances = new ArrayList<>();

        for (DataPoint p : dataset) {
            double dist = calculateEuclideanDistance(p.features, queryPoint);
            distances.add(new DistancePair(dist, p.label));
        }

        distances.sort(Comparator.comparingDouble(d -> d.distance));

        List<String> topKLabels = new ArrayList<>();
        for (int i = 0; i < Math.min(k, distances.size()); i++) {
            topKLabels.add(distances.get(i).label);
        }

        String bestLabel = null;
        int maxCount = -1;
        for (String label : topKLabels) {
            int count = Collections.frequency(topKLabels, label);
            if (count > maxCount) {
                maxCount = count;
                bestLabel = label;
            }
        }
        return bestLabel;
    }

    public static void main(String[] args) {
        List<DataPoint> trainData = new ArrayList<>();
        trainData.add(new DataPoint(new double[] {1.0, 2.0}, "ClassA"));
        trainData.add(new DataPoint(new double[] {2.0, 3.0}, "ClassA"));
        trainData.add(new DataPoint(new double[] {7.0, 8.0}, "ClassB"));

        double[] target = new double[] {1.5, 2.5};
        String prediction = classify(trainData, target, 3);
        System.out.println("Predicted Category: " + prediction);
    }
}
