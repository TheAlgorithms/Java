package com.thealgorithms.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class SubsequenceTest {

    @Test
    void testNoElement() {
        List<List<Object>> emptyResult = Subsequence.generateAllSubsequences(new ArrayList<>());
        assertEquals(1, emptyResult.size());
        assertEquals(0, emptyResult.get(0).size());
    }

    @Test
    void testLengthOfTwo() {
        List<List<Integer>> expected = List.of(
                List.of(),
                List.of(2),
                List.of(1),
                List.of(1, 2)
        );
        List<List<Object>> actual = Subsequence.generateAllSubsequences(List.of(1, 2));
        assertIterableEquals(expected, actual);
    }

    @Test
    void testLengthOfThree() {
        List<List<String>> expected = List.of(
                List.of(),
                List.of("C"),
                List.of("B"),
                List.of("B", "C"),
                List.of("A"),
                List.of("A", "C"),
                List.of("A", "B"),
                List.of("A", "B", "C")
        );
        List<List<Object>> actual = Subsequence.generateAllSubsequences(List.of("A", "B", "C"));
        assertIterableEquals(expected, actual);
    }

    @Test
    void testLengthOfThreeInteger() {
        List<List<Integer>> expected = List.of(
                List.of(),
                List.of(3),
                List.of(2),
                List.of(2, 3),
                List.of(1),
                List.of(1, 3),
                List.of(1, 2),
                List.of(1, 2, 3)
        );
        List<List<Object>> actual = Subsequence.generateAllSubsequences(List.of(1, 2, 3));
        assertIterableEquals(expected, actual);
    }
}
