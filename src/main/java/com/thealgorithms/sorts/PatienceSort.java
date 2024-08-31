package com.thealgorithms.sorts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This class implements the Patience Sort algorithm. Patience Sort is a sorting algorithm that
 * is particularly good for sorting sequences that are already partially sorted.
 */
public class PatienceSort implements SortAlgorithm {

    /**
     * Sorts an array of comparable elements using the Patience Sort algorithm.
     *
     * @param array the array to be sorted
     * @param <T> the type of elements in the array, must be comparable
     * @return the sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array.length == 0) {
            return array;
        }

        final List<List<T>> piles = formPiles(array);
        final PriorityQueue<PileNode<T>> pq = mergePiles(piles);
        extractPiles(array, pq);

        return array;
    }

    /**
     * Forms piles from the given array. Each pile is a list of elements where
     * each element is smaller than the one before it. Binary search is used
     * to find the appropriate pile for each element.
     *
     * @param array the array of elements to be organized into piles
     * @param <T> the type of elements in the array, must be comparable
     * @return a list of piles
     */
    private static <T extends Comparable<T>> List<List<T>> formPiles(final T[] array) {
        final List<List<T>> piles = new ArrayList<>();
        final List<T> lastElements = new ArrayList<>();

        for (T x : array) {
            int pos = Collections.binarySearch(lastElements, x);
            if (pos < 0) {
                pos = -pos - 1;
            }

            if (pos < piles.size()) {
                piles.get(pos).add(x);
                lastElements.set(pos, x);
            } else {
                List<T> newPile = new ArrayList<>();
                newPile.add(x);
                piles.add(newPile);
                lastElements.add(x);
            }
        }

        return piles;
    }

    /**
     * Merges the piles into a priority queue where the smallest elements are
     * prioritized.
     *
     * @param piles the list of piles to be merged
     * @param <T> the type of elements in the piles, must be comparable
     * @return a priority queue containing the top element of each pile
     */
    private static <T extends Comparable<T>> PriorityQueue<PileNode<T>> mergePiles(final List<List<T>> piles) {
        PriorityQueue<PileNode<T>> pq = new PriorityQueue<>();
        for (List<T> pile : piles) {
            pq.add(new PileNode<>(pile.removeLast(), pile));
        }
        return pq;
    }

    /**
     * Extracts elements from the priority queue to form the sorted array.
     *
     * @param array the array to be filled with sorted elements
     * @param pq the priority queue containing the elements to be extracted
     * @param <T> the type of elements in the array, must be comparable
     */
    private static <T extends Comparable<T>> void extractPiles(final T[] array, final PriorityQueue<PileNode<T>> pq) {
        int index = 0;
        while (!pq.isEmpty()) {
            PileNode<T> node = pq.poll();
            array[index++] = node.value;
            if (!node.pile.isEmpty()) {
                pq.add(new PileNode<>(node.pile.removeLast(), node.pile));
            }
        }
    }

    /**
     * A helper record representing a node in the priority queue.
     *
     * @param <T> the type of elements in the node, must be comparable
     */
    private record PileNode<T extends Comparable<T>>(T value, List<T> pile) implements Comparable<PileNode<T>> {
        @Override
        public int compareTo(PileNode<T> other) {
            return this.value.compareTo(other.value);
        }
    }
}
