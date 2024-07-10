package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.thealgorithms.maths.BinomialCoefficient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ArrayCombinationTest {
    @ParameterizedTest
    @MethodSource("regularInputs")
    void testCombination(int n, int k, List<List<Integer>> expected) {
        assertEquals(expected.size(), BinomialCoefficient.binomialCoefficient(n, k));
        assertEquals(expected, ArrayCombination.combination(n, k));
    }

    @ParameterizedTest
    @MethodSource("wrongInputs")
    void testCombinationThrows(int n, int k) {
        assertThrows(IllegalArgumentException.class, () -> ArrayCombination.combination(n, k));
    }

    private static Stream<Arguments> regularInputs() {
        return Stream.of(Arguments.of(0, 0, List.of(new ArrayList<Integer>())), Arguments.of(1, 0, List.of(new ArrayList<Integer>())), Arguments.of(1, 1, List.of(List.of(0))), Arguments.of(3, 0, List.of(new ArrayList<Integer>())), Arguments.of(3, 1, List.of(List.of(0), List.of(1), List.of(2))),
            Arguments.of(4, 2, List.of(List.of(0, 1), List.of(0, 2), List.of(0, 3), List.of(1, 2), List.of(1, 3), List.of(2, 3))));
    }

    private static Stream<Arguments> wrongInputs() {
        return Stream.of(Arguments.of(-1, 0), Arguments.of(0, -1), Arguments.of(2, 100));
    }
}
