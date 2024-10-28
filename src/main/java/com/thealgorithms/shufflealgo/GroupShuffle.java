package com.thealgorithms.shufflealgo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public
final class GroupShuffle {

    private
    GroupShuffle() {
        // Prevent instantiation
    }

    /**
     * Groups and shuffles elements in the array.
     *
     * @param array     the input array to shuffle
     * @param groupSize the size of each group
     * @return a list of shuffled groups
     */
    public
    static List<List<Integer>> groupShuffle(int[] array, int groupSize) {
        List<List<Integer>> groups = new ArrayList<>();

        // Edge case: Check if the group size is valid
        if (array == null || groupSize <= 0) {
            return groups;
        }

        for (int i = 0; i < array.length; i += groupSize) {
            List<Integer> group = new ArrayList<>();
            for (int j = i; j < Math.min(i + groupSize, array.length); j++) {
                group.add(array[j]);
            }
            groups.add(group);
        }

        // Shuffle only if the group size is greater than 1
        if (groupSize > 1) {
            Collections.shuffle(groups);
        }

        return groups;
    }

    public
    static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<List<Integer>> shuffledGroups = groupShuffle(array, 3);

        System.out.println("Shuffled Groups:");
        for (List<Integer> group : shuffledGroups) {
            System.out.println(group);
        }
    }
}
