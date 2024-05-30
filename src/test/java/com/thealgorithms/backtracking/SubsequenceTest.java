package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SubsequenceTest {

    @ParameterizedTest
    @MethodSource("getSubsequenceTestData")
    void testSubsequences(SubsequenceTestData testData) {
        List<List<Object>> actual = SubsequenceFinder.generateAllSubsequences(testData.input());
        assertIterableEquals(testData.expected(), actual);
    }

    static Stream<SubsequenceTestData> getSubsequenceTestData() {
        return Stream.of(new SubsequenceTestData(new ArrayList<>(), List.of(List.of())), new SubsequenceTestData(List.of(1, 2), List.of(List.of(), List.of(2), List.of(1), List.of(1, 2))),
                new SubsequenceTestData(List.of("A", "B", "C"), List.of(List.of(), List.of("C"), List.of("B"), List.of("B", "C"), List.of("A"), List.of("A", "C"), List.of("A", "B"), List.of("A", "B", "C"))),
                new SubsequenceTestData(List.of(1, 2, 3), List.of(List.of(), List.of(3), List.of(2), List.of(2, 3), List.of(1), List.of(1, 3), List.of(1, 2), List.of(1, 2, 3))));
    }

    record SubsequenceTestData(List<Object> input, List<List<Object>> expected) {
    }
}
