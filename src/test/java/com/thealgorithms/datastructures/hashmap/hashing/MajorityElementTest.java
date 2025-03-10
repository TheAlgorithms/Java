package com.thealgorithms.datastructures.hashmap.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MajorityElementTest {
    @Test
    void testMajorityWithSingleMajorityElement() {
        int[] nums = {1, 2, 3, 9, 9, 6, 7, 8, 9, 9, 9, 9};
        List<Integer> expected = new ArrayList<>();
        expected.add(9);
        List<Integer> actual = MajorityElement.majority(nums);
        assertEquals(expected, actual);
    }

    @Test
    void testMajorityWithMultipleMajorityElements() {
        int[] nums = {1, 2, 3, 3, 4, 4, 4, 4};
        List<Integer> expected = new ArrayList<>();
        expected.add(4);
        List<Integer> actual = MajorityElement.majority(nums);
        assertEquals(expected, actual);
    }

    @Test
    void testMajorityWithNoMajorityElement() {
        int[] nums = {1, 2, 4, 4, 5, 4};
        List<Integer> expected = new ArrayList<>();
        expected.add(4);
        List<Integer> actual = MajorityElement.majority(nums);
        assertEquals(expected, actual);
    }

    @Test
    void testMajorityWithEmptyArray() {
        int[] nums = {};
        List<Integer> expected = Collections.emptyList();
        List<Integer> actual = MajorityElement.majority(nums);
        assertEquals(expected, actual);
    }

    @Test
    void testMajorityWithAllElementsSame() {
        int[] nums = {5, 5, 5, 5, 5};
        List<Integer> expected = new ArrayList<>();
        expected.add(5);
        List<Integer> actual = MajorityElement.majority(nums);
        assertEquals(expected, actual);
    }

    @Test
    void testMajorityWithEvenCountAndOneMajorityElement() {
        int[] nums = {1, 2, 2, 3, 3, 2};
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        List<Integer> actual = MajorityElement.majority(nums);
        assertEquals(expected, actual);
    }

    @Test
    void testMajorityWithNoElementsEqualToHalf() {
        int[] nums = {1, 1, 2, 2, 3, 3, 4};
        List<Integer> expected = Collections.emptyList();
        List<Integer> actual = MajorityElement.majority(nums);
        assertEquals(expected, actual);
    }

    @Test
    void testMajorityWithLargeArray() {
        int[] nums = {1, 2, 3, 1, 1, 1, 2, 1, 1};
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        List<Integer> actual = MajorityElement.majority(nums);
        assertEquals(expected, actual);
    }
}
