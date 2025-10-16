package com.thealgorithms.compression;

import java.util.Map;

/**
 * Implementation of Arithmetic Coding algorithm.
 * Reference: https://en.wikipedia.org/wiki/Arithmetic_coding
 */

public class ArithmeticCoding {

    private ArithmeticCoding() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static double encode(String input, Map<Character, Double> probabilities) {
        double low = 0.0;
        double high = 1.0;

        for (char symbol : input.toCharArray()) {
            double range = high - low;
            double cumProb = 0.0;

            for (Map.Entry<Character, Double> entry : probabilities.entrySet()) {
                char current = entry.getKey();
                double prob = entry.getValue();
                double next = cumProb + prob;

                if (symbol == current) {
                    high = low + range * next;
                    low = low + range * cumProb;
                    break;
                }
                cumProb = next;
            }
        }
        return (low + high) / 2.0;
    }
}
