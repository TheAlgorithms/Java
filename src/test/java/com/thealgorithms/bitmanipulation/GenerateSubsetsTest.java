package com.thealgorithms.bitmanipulation;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class GenerateSubsetsTest {

    @Test
    void testGenerateSubsetsWithTwoElements() {
        int[] set = {1, 2};
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>());
        expected.add(singletonList(1));
        expected.add(singletonList(2));
        expected.add(Arrays.asList(1, 2));

        List<List<Integer>> result = GenerateSubsets.generateSubsets(set);
        assertEquals(expected, result);
    }

    @Test
    void testGenerateSubsetsWithOneElement() {
        int[] set = {3};
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>());
        expected.add(singletonList(3));

        List<List<Integer>> result = GenerateSubsets.generateSubsets(set);
        assertEquals(expected, result);
    }

    @Test
    void testGenerateSubsetsWithThreeElements() {
        int[] set = {4, 5, 6};
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>());
        expected.add(singletonList(4));
        expected.add(singletonList(5));
        expected.add(Arrays.asList(4, 5));
        expected.add(singletonList(6));
        expected.add(Arrays.asList(4, 6));
        expected.add(Arrays.asList(5, 6));
        expected.add(Arrays.asList(4, 5, 6));

        List<List<Integer>> result = GenerateSubsets.generateSubsets(set);
        assertEquals(expected, result);
    }
}
