package com.thealgorithms.misc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
* MapReduce is a programming model for processing and generating large data sets with a parallel,
distributed algorithm on a cluster.
* It has two main steps: the Map step, where the data is divided into smaller chunks and processed in parallel,
and the Reduce step, where the results from the Map step are combined to produce the final output.
* Wikipedia link : https://en.wikipedia.org/wiki/MapReduce
*/

public class MapReduce {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");

        // Map step
        Map<String, Long> wordCounts = words.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        // Reduce step
        wordCounts.forEach((word, count) -> System.out.println(word + ": " + count));
    }
}
