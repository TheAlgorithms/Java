package com.sorts;

import java.util.ArrayList;
import java.util.List;

public class StrandSort<T> {
    private List<T> sorted = new ArrayList<>();

    /**
     * A method that sorts a list of objects using the Strand Sort Algorithm. Along with the inputted unsorted list,
     * the algorithm creates two new lists, "subList" and "sorted". The "sorted" list is the resulting list after
     * strand sorting, and the "subList" list acts as a mediator during the algorithm. This method is recursive and uses
     * a helper function, "rearrangeInSorted(subList, result)". For more information about Strand Sort algorithm,
     * check out this Wikipedia article: https://en.wikipedia.org/wiki/Strand_sort
     *
     * @param unsorted
     * @return final sorted list after the completion of the strand sort algorithm
     */
    public <T extends Comparable<T>> List<T> strandSort(List<T> unsorted) {
        List<T> subList = new ArrayList<>();
        if (unsorted.isEmpty() && subList.isEmpty()) {
            System.out.println("final sorted: " + sorted);
            return (List<T>) sorted;
        }
        subList.add(unsorted.get(0));
        unsorted.remove(0);
        System.out.println("Beginning unsorted: " + unsorted);
        System.out.println("Beginning subList: " + subList);
        if (unsorted.isEmpty() && !subList.isEmpty()) {
            System.out.println("Changed subList: " + subList);
            rearrangeInSorted(subList, (List<T>) sorted);
            return strandSort(unsorted);
        }
        int comparisonPointer = 0;
        while (!unsorted.isEmpty() && subList.get(comparisonPointer).compareTo(unsorted.get(0)) <= 0) {
            subList.add(unsorted.get(0));
            unsorted.remove(0);
            comparisonPointer++;
        }
        System.out.println("Changed subList: " + subList);
        rearrangeInSorted(subList, (List<T>) sorted);
        return strandSort(unsorted);
    }

    /**
     * A helper function to combine the subList with the sorted list, before returning to another
     * round of recursion of strand sort for the rest of the elements in the unsorted list.
     *
     * @param subList
     * @param result
     * @return list sorted after the combining of the subList and result list.
     */
    public <T extends Comparable<T>> List<T> rearrangeInSorted(List<T> subList, List<T> result) {
        if (result.isEmpty()) {
            for (T value : subList)
                result.add(value);
            System.out.println("sorted: " + sorted);
            return result;
        }

        int lastIndex = result.size() - 1;
        if (result.get(lastIndex).compareTo(subList.get(0)) <= 0) {
            for (T value : subList)
                result.add(value);
        } else {
            int counter = 0;
            for (T value : subList) {
                for (int i = counter; i < result.size(); i++) {
                    if (value.compareTo(result.get(i)) <= 0) {
                        result.add(i, value);
                        counter = i;
                        break;
                    } else if (result.get(result.size() - 1) == result.get(i)) {
                        result.add(value);
                        counter = i;
                        break;
                    }
                }
            }
        }
        while (subList.size() > 0) {
            subList.remove(0);
        }
        System.out.println("sorted: " + sorted);
        return result;
    }
}
