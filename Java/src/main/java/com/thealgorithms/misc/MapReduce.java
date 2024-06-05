package com.thealgorithms.misc;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
* MapReduce is a programming model for processing and generating large data sets with a parallel,
distributed algorithm on a cluster.
* It has two main steps: the Map step, where the data is divided into smaller chunks and processed in parallel,
and the Reduce step, where the results from the Map step are combined to produce the final output.
* Wikipedia link : https://en.wikipedia.org/wiki/MapReduce
*/

public final class MapReduce {
    private MapReduce() {
    }
    /*
     *Counting all the words frequency within a sentence.
     */
    public static String mapreduce(String sentence) {
        List<String> wordList = Arrays.stream(sentence.split(" ")).toList();

        // Map step
        Map<String, Long> wordCounts = wordList.stream().collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

        // Reduce step
        StringBuilder result = new StringBuilder();
        wordCounts.forEach((word, count) -> result.append(word).append(": ").append(count).append(","));

        // Removing the last ',' if it exists
        if (!result.isEmpty()) {
            result.setLength(result.length() - 1);
        }

        return result.toString();
    }
}
