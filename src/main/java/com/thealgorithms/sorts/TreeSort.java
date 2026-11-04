package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.print;

import com.thealgorithms.datastructures.trees.BSTRecursiveGeneric;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1> Implementation of the Tree Sort algorithm</h1>
 *
 * <p>
 * Tree Sort: A sorting algorithm which constructs a Binary Search Tree using
 * the unsorted data and then outputs the data by inorder traversal of the tree.
 *
 * Reference: https://en.wikipedia.org/wiki/Tree_sort
 * </p>
 *
 * @author Madhur Panwar (https://github.com/mdrpanwar)
 */
public class TreeSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsortedArray) {
        return doTreeSortArray(unsortedArray);
    }

    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> unsortedList) {
        return doTreeSortList(unsortedList);
    }

    private <T extends Comparable<T>> T[] doTreeSortArray(T[] unsortedArray) {
        // sort through the list path and copy the result back into the initial array
        List<T> sortedList = doTreeSortList(Arrays.asList(unsortedArray));
        return sortedList.toArray(unsortedArray);
    }

    private <T extends Comparable<T>> List<T> doTreeSortList(Iterable<T> unsortedList) {
        // the BST ignores duplicate keys, so pair every element with its position to keep equal values apart
        BSTRecursiveGeneric<Keyed<T>> tree = new BSTRecursiveGeneric<>();

        // add all elements to the tree
        int position = 0;
        for (T element : unsortedList) {
            tree.add(new Keyed<>(element, position++));
        }

        // get the sorted list by inorder traversal of the tree and return it
        List<T> sortedList = new ArrayList<>();
        for (Keyed<T> keyed : tree.inorderSort()) {
            sortedList.add(keyed.value());
        }
        return sortedList;
    }

    /**
     * An element plus its original position. Equal elements are ordered by position,
     * which also makes the sort stable.
     */
    private record Keyed<T extends Comparable<T>>(T value, int position) implements Comparable<Keyed<T>> {
        @Override
        public int compareTo(Keyed<T> other) {
            int byValue = value.compareTo(other.value);
            return byValue != 0 ? byValue : Integer.compare(position, other.position);
        }
    }

    public static void main(String[] args) {
        TreeSort treeSort = new TreeSort();

        // ==== Integer Array =======
        System.out.println("Testing for Integer Array....");
        Integer[] a = {3, -7, 45, 1, 343, -5, 2, 9};
        System.out.printf("%-10s", "unsorted: ");
        print(a);
        a = treeSort.sort(a);
        System.out.printf("%-10s", "sorted: ");
        print(a);
        System.out.println();

        // ==== Integer List =======
        System.out.println("Testing for Integer List....");
        List<Integer> intList = List.of(3, -7, 45, 1, 343, -5, 2, 9);
        System.out.printf("%-10s", "unsorted: ");
        print(intList);
        intList = treeSort.sort(intList);
        System.out.printf("%-10s", "sorted: ");
        print(intList);
        System.out.println();

        // ==== String Array =======
        System.out.println("Testing for String Array....");
        String[] b = {
            "banana",
            "berry",
            "orange",
            "grape",
            "peach",
            "cherry",
            "apple",
            "pineapple",
        };
        System.out.printf("%-10s", "unsorted: ");
        print(b);
        b = treeSort.sort(b);
        System.out.printf("%-10s", "sorted: ");
        print(b);
        System.out.println();

        // ==== String List =======
        System.out.println("Testing for String List....");
        List<String> stringList = List.of("banana", "berry", "orange", "grape", "peach", "cherry", "apple", "pineapple");
        System.out.printf("%-10s", "unsorted: ");
        print(stringList);
        stringList = treeSort.sort(stringList);
        System.out.printf("%-10s", "sorted: ");
        print(stringList);
    }
}
