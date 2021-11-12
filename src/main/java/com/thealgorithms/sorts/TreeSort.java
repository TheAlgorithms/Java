package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.print;
import com.thealgorithms.datastructures.trees.BSTRecursiveGeneric;

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
        // create a generic BST tree
        BSTRecursiveGeneric<T> tree = new BSTRecursiveGeneric<T>();

        // add all elements to the tree
        for (T element : unsortedArray) {
            tree.add(element);
        }

        // get the sorted list by inorder traversal of the tree
        List<T> sortedList = tree.inorderSort();

        // add the elements back to the initial array
        int i = 0;
        for (T element : sortedList) {
            unsortedArray[i++] = element;
        }

        // return the array
        return unsortedArray;
    }

    private <T extends Comparable<T>> List<T> doTreeSortList(List<T> unsortedList) {
        // create a generic BST tree
        BSTRecursiveGeneric<T> tree = new BSTRecursiveGeneric<T>();

        // add all elements to the tree
        for (T element : unsortedList) {
            tree.add(element);
        }

        // get the sorted list by inorder traversal of the tree and return it
        return tree.inorderSort();
    }

    public static void main(String[] args) {
        TreeSort treeSort = new TreeSort();

        // ==== Integer Array =======
        System.out.println("Testing for Integer Array....");
        Integer[] a = {3, -7, 45, 1, 343, -5, 2, 9};
        System.out.print(String.format("%-10s", "unsorted: "));
        print(a);
        a = treeSort.sort(a);
        System.out.print(String.format("%-10s", "sorted: "));
        print(a);
        System.out.println();

        // ==== Integer List =======
        System.out.println("Testing for Integer List....");
        List<Integer> intList = List.of(3, -7, 45, 1, 343, -5, 2, 9);
        System.out.print(String.format("%-10s", "unsorted: "));
        print(intList);
        intList = treeSort.sort(intList);
        System.out.print(String.format("%-10s", "sorted: "));
        print(intList);
        System.out.println();

        // ==== String Array =======
        System.out.println("Testing for String Array....");
        String[] b = {"banana", "berry", "orange", "grape", "peach", "cherry", "apple", "pineapple"};
        System.out.print(String.format("%-10s", "unsorted: "));
        print(b);
        b = treeSort.sort(b);
        System.out.print(String.format("%-10s", "sorted: "));
        print(b);
        System.out.println();

        // ==== String List =======
        System.out.println("Testing for String List....");
        List<String> stringList = List.of("banana", "berry", "orange", "grape", "peach", "cherry", "apple", "pineapple");
        System.out.print(String.format("%-10s", "unsorted: "));
        print(stringList);
        stringList = treeSort.sort(stringList);
        System.out.print(String.format("%-10s", "sorted: "));
        print(stringList);

    }

}
