package com.thealgorithms.shufflealogrithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupShuffle {

    /**
     * Divides the array into k equal-sized groups and shuffles each group separately.
     * Returns an empty list if the group count exceeds the array length.
     *
     * @param array the input array to split into groups
     * @param k the number of groups to create
     * @return a list of groups, where each group is a shuffled sublist of the input array
     */
    public static List<List<Integer>> groupShuffle(int[] array, int k) {
        List<List<Integer>> groups = new ArrayList<>();

        // Edge case: Check if grouping is possible
        if (k > array.length || k <= 0) {
            return groups;
        }

        List<Integer> shuffledList = new ArrayList<>();
        for (int num : array) {
            shuffledList.add(num);
        }
        Collections.shuffle(shuffledList);

        int groupSize = array.length / k;
        int remainder = array.length % k;

        // Distribute elements into k groups
        for (int i = 0; i < k; i++) {
            int end = (i + 1) * groupSize + (i < remainder ? 1 : 0);
            groups.add(new ArrayList<>(shuffledList.subList(i * groupSize + Math.min(i, remainder), end)));
        }

        return groups;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6};
        int k = 3;
        List<List<Integer>> groups = groupShuffle(array, k);

        System.out.println("Shuffled Groups:");
        for (List<Integer> group : groups) {
            System.out.println(group);
        }
    }
}
