package com.thealgorithms.sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * CountingSort is a generic implementation of the counting sort algorithm.
 * This implementation sorts elements that implement the Comparable interface.
 *
 * @author Youssef Ali (https://github.com/youssefAli11997)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 */
class PseudoCountingSortUsingTreeMap implements SortAlgorithm {
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        return sort(Arrays.asList(array)).toArray(array);
    }

    /**
     * Sorts the provided list using the counting sort algorithm.
     *
     * @param list The list to be sorted.
     * @param <T> The type of elements in the list, must be Comparable.
     * @return A sorted list in increasing order.
     */
    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> list) {
        return extractSortedArray(computeFequencyMap(list));
    }

    private static <T extends Comparable<T>> List<T> extractSortedArray(final Map<T, Integer> frequencyMap) {
        List<T> sortedList = new ArrayList<>();
        for (final Map.Entry<T, Integer> entry : frequencyMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                sortedList.add(entry.getKey());
            }
        }
        return sortedList;
    }

    private static <T extends Comparable<T>> Map<T, Integer> computeFequencyMap(final List<T> list) {
        Map<T, Integer> frequencyMap = new TreeMap<>();
        for (final T element : list) {
            frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
        }
        return frequencyMap;
    }
}
