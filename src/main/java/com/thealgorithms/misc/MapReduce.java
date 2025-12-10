package com.thealgorithms.misc;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * MapReduce is a programming model for processing and generating large data sets
 * using a parallel, distributed algorithm on a cluster.
 * It consists of two main phases:
 * - Map: the input data is split into smaller chunks and processed in parallel.
 * - Reduce: the results from the Map phase are aggregated to produce the final output.
 *
 * See also: https://en.wikipedia.org/wiki/MapReduce
 */
public final class MapReduce {

    private MapReduce() {
    }

    /**
     * Counts the frequency of each word in a given sentence using a simple MapReduce-style approach.
     *
     * @param sentence the input sentence
     * @return a string representing word frequencies in the format "word: count,word: count,..."
     */
    public static String countWordFrequencies(String sentence) {
        // Map phase: split the sentence into words
        List<String> words = Arrays.asList(sentence.trim().split("\\s+"));

        // Group and count occurrences of each word, maintain insertion order
        Map<String, Long> wordCounts = words.stream().collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

        // Reduce phase: format the result
        return wordCounts.entrySet().stream().map(entry -> entry.getKey() + ": " + entry.getValue()).collect(Collectors.joining(","));
    }
}
