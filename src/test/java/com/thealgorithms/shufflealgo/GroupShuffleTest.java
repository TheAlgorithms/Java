package com.thealgorithms.shufflealgo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public
class GroupShuffleTest {

    // Test case to check basic functionality
    @Test void testGroupShuffleBasic() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<List<Integer>> shuffledGroups = GroupShuffle.groupShuffle(array, 3);

        assertEquals(3, shuffledGroups.size()); // Expect 3 groups
        assertTrue(shuffledGroups.stream().allMatch(
                group->group.size() <= 3)); // All groups should have size <= 3
        System.out.println("Shuffled Groups (Basic Test): " + shuffledGroups);
    }

    // Test case to check when group size is larger than array length
    @Test void testGroupShuffleLargeGroupSize() {
        int[] array = {1, 2, 3};
        List<List<Integer>> shuffledGroups = GroupShuffle.groupShuffle(array, 5);

        assertEquals(1, shuffledGroups.size()); // Expect 1 group with all elements
        assertEquals(
                Arrays.asList(1, 2, 3),
                shuffledGroups.get(0)); // The group should contain all elements
        System.out.println("Shuffled Groups (Large Group Size Test): " +
                shuffledGroups);
    }

    // Test case to check when the array is null
    @Test void testGroupShuffleNullArray() {
        List<List<Integer>> shuffledGroups = GroupShuffle.groupShuffle(null, 3);

        assertTrue(shuffledGroups.isEmpty()); // Expect empty list
        System.out.println("Shuffled Groups (Null Array Test): " + shuffledGroups);
    }

    // Test case to check when group size is less than or equal to zero
    @Test void testGroupShuffleZeroOrNegativeGroupSize() {
        int[] array = {1, 2, 3};
        List<List<Integer>> shuffledGroups = GroupShuffle.groupShuffle(array, 0);

        assertTrue(
                shuffledGroups.isEmpty()); // Expect empty list for group size zero
        shuffledGroups = GroupShuffle.groupShuffle(array, -1);
        assertTrue(
                shuffledGroups.isEmpty()); // Expect empty list for negative group size
        System.out.println("Shuffled Groups (Zero or Negative Group Size Test): " +
                shuffledGroups);
    }

    // Test case to check when the array has fewer than 3 elements
    @Test void testGroupShuffleSmallArray() {
        int[] array = {1, 2};
        List<List<Integer>> shuffledGroups = GroupShuffle.groupShuffle(array, 2);

        assertEquals(1, shuffledGroups.size()); // Expect 1 group with all elements
        assertEquals(
                Arrays.asList(1, 2),
                shuffledGroups.get(0)); // The group should contain all elements
        System.out.println("Shuffled Groups (Small Array Test): " + shuffledGroups);
    }

    // Test case to check the behavior when the group size is 1
    @Test void testGroupShuffleGroupSizeOne() {
        int[] array = {1, 2, 3, 4, 5};
        List<List<Integer>> shuffledGroups = GroupShuffle.groupShuffle(array, 1);

        assertEquals(5, shuffledGroups.size()); // Expect 5 groups
        for (int i = 0; i < 5; i++) {
            assertEquals(
                    Arrays.asList(i + 1),
                    shuffledGroups.get(i)); // Each group should contain one element
        }
        System.out.println("Shuffled Groups (Group Size One Test): " +
                shuffledGroups);
    }
}