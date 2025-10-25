package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides a method to find the majority element(s) in an array of integers.
 * A majority element is defined as an element that appears at least ⌊n/2⌋ times,
 * where n is the length of the array. If multiple elements qualify as majority elements,
 * they are all returned in a list.
 */
public final class MajorityElement {
    private MajorityElement() {
    }

    /**
     * Returns a list of majority element(s) from the given array of integers.
     *
     * @param nums an array of integers
     * @return a list containing the majority element(s); returns an empty list if none exist or input is null/empty
     */
    public static List<Integer> majority(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        Map<Integer, Integer> numToCount = new HashMap<>();
        for (final var num : nums) {
            numToCount.merge(num, 1, Integer::sum);
        }

        List<Integer> majorityElements = new ArrayList<>();
        for (final var entry : numToCount.entrySet()) {
            if (entry.getValue() >= nums.length / 2) {
                majorityElements.add(entry.getKey());
            }
        }
        return majorityElements;
    }
}
