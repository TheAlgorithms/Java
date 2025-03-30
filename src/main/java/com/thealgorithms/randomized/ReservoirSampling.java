package com.thealgorithms.randomized;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

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
 * Author: Michael Alexander Montoya (@cureprotocols)
 */
public class ReservoirSampling {

    /**
     * Selects k random elements from a stream using reservoir sampling.
     *
     * @param stream     The input stream as an array of integers.
     * @param sampleSize The number of elements to sample.
     * @return A list containing k randomly selected elements.
     */
    public static List<Integer> sample(int[] stream, int sampleSize) {
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

    // Demo usage
    public static void main(String[] args) {
        int[] streamData = new int[1000];
        for (int i = 0; i < 1000; i++) {
            streamData[i] = i + 1;
        }

        List<Integer> result = ReservoirSampling.sample(streamData, 10);
        System.out.println("Random sample of 10 items:");
        for (int value : result) {
            System.out.print(value + " ");
        }
    }
}
