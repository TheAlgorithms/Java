package com.thealgorithms.randomized;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Reservoir Sampling Algorithm
 *
 * Use Case:
 * - Efficient for selecting k random items from a stream of unknown size
 * - Used in streaming systems, big data, and memory-limited environments
 *
 * Time Complexity: O(n)
 * Space Complexity: O(k)
 *
 * @author Michael Alexander Montoya (@cureprotocols)
 * @see <a href="https://en.wikipedia.org/wiki/Reservoir_sampling">Reservoir Sampling - Wikipedia</a>
 */
public final class ReservoirSampling {

    // Prevent instantiation of utility class
    private ReservoirSampling() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Selects k random elements from a stream using reservoir sampling.
     *
     * @param stream     The input stream as an array of integers.
     * @param sampleSize The number of elements to sample.
     * @return A list containing k randomly selected elements.
     */
    public static List<Integer> sample(int[] stream, int sampleSize) {
        if (sampleSize > stream.length) {
            throw new IllegalArgumentException("Sample size cannot exceed stream size.");
        }

        List<Integer> reservoir = new ArrayList<>(sampleSize);
        Random rand = new Random();

        for (int i = 0; i < stream.length; i++) {
            if (i < sampleSize) {
                reservoir.add(stream[i]);
            } else {
                int j = rand.nextInt(i + 1);
                if (j < sampleSize) {
                    reservoir.set(j, stream[i]);
                }
            }
        }

        return reservoir;
    }
}
