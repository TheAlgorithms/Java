package com.thealgorithms.sorts;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class PseudoCountingSortUsingTreeMapAndStream implements SortAlgorithm {
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        return streamSort(Arrays.asList(array)).toArray(array);
    }

    /**
     * Sorts the provided list using the counting sort algorithm with the Stream API.
     *
     * @param list The list to be sorted.
     * @param <T> The type of elements in the list, must be Comparable.
     * @return A sorted list in increasing order.
     */
    private static <T extends Comparable<T>> List<T> streamSort(List<T> list) {
        return list.stream().collect(toMap(k -> k, v -> 1, Integer::sum, TreeMap::new)).entrySet().stream().flatMap(entry -> IntStream.rangeClosed(1, entry.getValue()).mapToObj(t -> entry.getKey())).collect(toList());
    }
}
