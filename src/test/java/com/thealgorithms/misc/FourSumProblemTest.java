package com.thealgorithms.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class FourSumProblemTest {

    @Test
    void TestFourSum() {
        // Test Case 1
        int[] input1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;
        List<List<Integer>> expected1 = new ArrayList<>(Arrays.asList(
                Arrays.asList(-2, -1, 1, 2),
                Arrays.asList(-2, 0, 0, 2),
                Arrays.asList(-1, 0, 0, 1)
        ));
        List<List<Integer>> actual1 = FourSumProblem.Hashing4Sum(input1, target1);

        // Convert expected and actual results to sets for unordered comparison.
        Set<Set<Integer>> expectedSet1 = new HashSet<>();
        Set<Set<Integer>> actualSet1 = new HashSet<>();
        for (List<Integer> list : expected1) {
            expectedSet1.add(new HashSet<>(list));
        }
        for (List<Integer> list : actual1) {
            actualSet1.add(new HashSet<>(list));
        }
        Assertions.assertEquals(expectedSet1, actualSet1);
    }
}

