package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MergeSortedArrayListTest {

    @ParameterizedTest(name = "{3}")
    @MethodSource("provideMergeTestData")
    void testMergeParameterizedScenarios(List<Integer> listA, List<Integer> listB, List<Integer> expected, String scenarioName) {
        List<Integer> result = new ArrayList<>();
        MergeSortedArrayList.merge(listA, listB, result);
        assertEquals(expected, result, () -> "Failed scenario: " + scenarioName);
    }

    private static Stream<Arguments> provideMergeTestData() {
        return Stream.of(Arguments.of(Arrays.asList(1, 3, 5, 7, 9), Arrays.asList(2, 4, 6, 8, 10), Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), "Standard alternating sorted lists"), Arguments.of(Arrays.asList(1, 2, 3), new ArrayList<>(), Arrays.asList(1, 2, 3), "Merge with empty second list"),
            Arguments.of(new ArrayList<>(), Arrays.asList(4, 5, 6), Arrays.asList(4, 5, 6), "Merge with empty first list"), Arguments.of(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "Merge with both lists empty"),
            Arguments.of(Arrays.asList(1, 2, 2, 3), Arrays.asList(2, 3, 4), Arrays.asList(1, 2, 2, 2, 3, 3, 4), "Handling duplicate elements gracefully"),
            Arguments.of(Arrays.asList(-3, -1, 2), Arrays.asList(-2, 0, 3), Arrays.asList(-3, -2, -1, 0, 2, 3), "Handling negative numbers mixed with positive numbers"));
    }

    @Test
    void testMergeThrowsExceptionWhenListAIsNull() {
        List<Integer> listB = Arrays.asList(1, 2, 3);
        List<Integer> result = new ArrayList<>();
        assertThrows(NullPointerException.class, () -> MergeSortedArrayList.merge(null, listB, result));
    }

    @Test
    void testMergeThrowsExceptionWhenListBIsNull() {
        List<Integer> listA = Arrays.asList(1, 2, 3);
        List<Integer> result = new ArrayList<>();
        assertThrows(NullPointerException.class, () -> MergeSortedArrayList.merge(listA, null, result));
    }

    @Test
    void testMergeThrowsExceptionWhenResultCollectionIsNull() {
        List<Integer> listA = Arrays.asList(1, 2, 3);
        List<Integer> listB = Arrays.asList(4, 5, 6);
        assertThrows(NullPointerException.class, () -> MergeSortedArrayList.merge(listA, listB, null));
    }
}
