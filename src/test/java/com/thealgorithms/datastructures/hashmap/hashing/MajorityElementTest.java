package com.thealgorithms.datastructures.hashmap.hashing;

import com.thealgorithms.datastructures.hashmap.hashing.MajorityElement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;

import java.util.ArrayList;

public class MajorityElementTest{
    @Test
    void testMajorityWithSingleMajorityElement() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9};
        List<Integer> expected = new ArrayList<>();
        expected.add(9);
        List<Integer> actual = MajorityElement.majority(nums);
        assertEquals(expected, actual);
    }

    @Test
    void testMajorityWithMultipleMajorityElements() {
        int[] nums = {1, 2, 3, 3, 3, 4, 4, 4};
        List<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(4);
        List<Integer> actual = MajorityElement.majority(nums);
        assertEquals(expected, actual);
    }

    @Test
    void testMajorityWithNoMajorityElement() {
        int[] nums = {1, 2, 3, 4, 5, 6};
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = MajorityElement.majority(nums);
        assertEquals(expected, actual);
    }

    @Test
    void testMajorityWithEmptyArray() {
        int[] nums = {};
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = MajorityElement.majority(nums);
        assertEquals(expected, actual);
    }
}
