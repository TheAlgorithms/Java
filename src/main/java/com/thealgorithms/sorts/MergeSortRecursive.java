package com.thealgorithms.sorts;

import java.util.ArrayList;
import java.util.List;

public class MergeSortRecursive {

    List<Integer> arr;

    public MergeSortRecursive(List<Integer> arr) {
        this.arr = arr;
    }

    public List<Integer> mergeSort() {
        return merge(arr);
    }

    private static List<Integer> merge(List<Integer> arr) {
        // base condition
        if (arr.size() <= 1) {
            return arr;
        }

        int arrLength = arr.size();
        int half = arrLength / 2;
        List<Integer> arrA = arr.subList(0, half);
        List<Integer> arrB = arr.subList(half, arr.size());

        // recursion
        arrA = merge(arrA);
        arrB = merge(arrB);

        return sort(arrA, arrB);
    }

    private static List<Integer> sort(List<Integer> unsortedA, List<Integer> unsortedB) {
        if (unsortedA.isEmpty() && unsortedB.isEmpty()) {
            return new ArrayList<>();
        }
        if (unsortedA.isEmpty()) {
            return unsortedB;
        }
        if (unsortedB.isEmpty()) {
            return unsortedA;
        }
        if (unsortedA.get(0) <= unsortedB.get(0)) {
            List<Integer> newAl = new ArrayList<Integer>() {
                { add(unsortedA.get(0)); }
            };
            newAl.addAll(sort(unsortedA.subList(1, unsortedA.size()), unsortedB));
            return newAl;
        } else {
            List<Integer> newAl = new ArrayList<Integer>() {
                { add(unsortedB.get(0)); }
            };
            newAl.addAll(sort(unsortedA, unsortedB.subList(1, unsortedB.size())));
            return newAl;
        }
    }
}
