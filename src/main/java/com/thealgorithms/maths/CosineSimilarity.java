package com.thealgorithms.maths;

import java.util.Scanner;

/**
 * Cosine Similarity is a correlation coefficient and a measure of similarity
 * between two non-zero vectors in an Inner Product Space.
 *  Cosine similarity is the cosine of the angle between the vectors;
 *  that is, it is the dot product of the vectors divided by the product of their lengths.
 * Reference: https://en.wikipedia.org/wiki/Cosine_similarity
 */

public class CosineSimilarity {

    // Function to compute the euclidean magnitude of the vector.
    public static double vectorMagnitude(double[] vector) {
        double magnitude = 0;
        for (int i = 0; i < vector.length; i++) {
            magnitude += vector[i] * vector[i];
        }
        return Math.pow(magnitude, 0.5);
    }
    // Function to compute the cosine similarity between vector1 and vector2
    public static double cosineSimilarity(double vector1[], double vector2[]) {
        int lengthOfSmallerVector = vector1.length < vector2.length ? vector1.length : vector2.length;
        double dotProduct = 0;

        for (int i = 0; i < lengthOfSmallerVector; i++) {
            dotProduct += vector1[i] * vector2[i];
        }

        double vector1Magnitude = vectorMagnitude(vector1);
        double vector2Magnitude = vectorMagnitude(vector2);

        if (vector1Magnitude == 0 || vector2Magnitude == 0) {
            throw new IllegalArgumentException("Cosine Similarity cannot be computed. Zero Vectors are not allowed.");
        }

        double cosineSimilarityValue = (dotProduct) / (vector1Magnitude * vector2Magnitude);
        return cosineSimilarityValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the length of 1st Vector");
        int len1 = sc.nextInt();
        double[] vector1 = new double[len1];
        System.out.println("Enter the vector");
        for (int i = 0; i < len1; i++) {
            vector1[i] = sc.nextInt();
        }

        System.out.println("Enter the length of 2nd Vector");
        int len2 = sc.nextInt();
        double[] vector2 = new double[len2];
        System.out.println("Enter the vector");
        for (int i = 0; i < len2; i++) {
            vector2[i] = sc.nextInt();
        }

        System.out.println("The Cosine Similarity is = " + cosineSimilarity(vector1, vector2));

        sc.close();
    }
}
