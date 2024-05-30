package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SubsequenceFinderTest {

    @ParameterizedTest
    @MethodSource("getTestCases")
    void testGenerateAll(TestCase testData) {
        final var actual = SubsequenceFinder.generateAll(testData.input());
        assertIterableEquals(testData.expected(), actual);
    }

    static Stream<TestCase> getTestCases() {
        return Stream.of(new TestCase(new ArrayList<>(), List.of(List.of())), new TestCase(List.of(1, 2), List.of(List.of(), List.of(2), List.of(1), List.of(1, 2))),
            new TestCase(List.of("A", "B", "C"), List.of(List.of(), List.of("C"), List.of("B"), List.of("B", "C"), List.of("A"), List.of("A", "C"), List.of("A", "B"), List.of("A", "B", "C"))),
            new TestCase(List.of(1, 2, 3), List.of(List.of(), List.of(3), List.of(2), List.of(2, 3), List.of(1), List.of(1, 3), List.of(1, 2), List.of(1, 2, 3))), new TestCase(List.of(2, 2), List.of(List.of(), List.of(2), List.of(2), List.of(2, 2))));
    }

    record TestCase(List<Object> input, List<List<Object>> expected) {
    }
}
