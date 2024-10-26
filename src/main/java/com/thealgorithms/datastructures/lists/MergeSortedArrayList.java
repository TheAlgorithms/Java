package com.thealgorithms.datastructures.lists;

import java.util.Collection;
import java.util.List;

/**
 * Utility class for merging two sorted ArrayLists of integers into a single sorted collection.
 *
 * <p>This class provides a static `merge` method to combine two pre-sorted lists of integers into a
 * single sorted list. It does so without modifying the input lists by adding elements from both lists in sorted order
 * into the result list.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * List<Integer> listA = Arrays.asList(1, 3, 5, 7, 9);
 * List<Integer> listB = Arrays.asList(2, 4, 6, 8, 10);
 * List<Integer> result = new ArrayList<>();
 * MergeSortedArrayList.merge(listA, listB, result);
 * </pre>
 *
 * <p>The resulting `result` list will be [1, 2, 3, 4, 5, 6, 7, 8, 9, 10].</p>
 *
 * <p>Note: This class cannot be instantiated as it is designed to be used only with its static `merge` method.</p>
 *
 * <p>This implementation assumes the input lists are already sorted in ascending order.</p>
 *
 * @author https://github.com/shellhub
 * @see List
 */
public final class MergeSortedArrayList {

    private MergeSortedArrayList() {
    }

    /**
     * Merges two sorted lists of integers into a single sorted collection.
     *
     * <p>This method does not alter the original lists (`listA` and `listB`). Instead, it inserts elements from both
     * lists into `listC` in a way that maintains ascending order.</p>
     *
     * @param listA The first sorted list of integers.
     * @param listB The second sorted list of integers.
     * @param listC The collection to hold the merged result, maintaining sorted order.
     * @throws NullPointerException if any of the input lists or result collection is null.
     */
    public static void merge(List<Integer> listA, List<Integer> listB, Collection<Integer> listC) {
        if (listA == null || listB == null || listC == null) {
            throw new NullPointerException("Input lists and result collection must not be null.");
        }

        int pa = 0;
        int pb = 0;

        while (pa < listA.size() && pb < listB.size()) {
            if (listA.get(pa) <= listB.get(pb)) {
                listC.add(listA.get(pa++));
            } else {
                listC.add(listB.get(pb++));
            }
        }

        // Add remaining elements from listA, if any
        while (pa < listA.size()) {
            listC.add(listA.get(pa++));
        }
        // Add remaining elements from listB, if any
        while (pb < listB.size()) {
            listC.add(listB.get(pb++));
        }
    }
}
