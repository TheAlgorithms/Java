package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ThreeSumProblemTest {

    private ThreeSumProblem tsp;

    @BeforeEach
    public void setup() {
        tsp = new ThreeSumProblem();
    }

    @ParameterizedTest
    @MethodSource("bruteForceTestProvider")
    public void testBruteForce(int[] nums, int target, List<List<Integer>> expected) {
        assertEquals(expected, tsp.bruteForce(nums, target));
    }

    @ParameterizedTest
    @MethodSource("twoPointerTestProvider")
    public void testTwoPointer(int[] nums, int target, List<List<Integer>> expected) {
        assertEquals(expected, tsp.twoPointer(nums, target));
    }

    @ParameterizedTest
    @MethodSource("hashMapTestProvider")
    public void testHashMap(int[] nums, int target, List<List<Integer>> expected) {
        assertEquals(expected, tsp.hashMap(nums, target));
    }

    private static Stream<Arguments> bruteForceTestProvider() {
        return Stream.of(Arguments.of(new int[] {1, 2, -3, 4, -2, -1}, 0, Arrays.asList(Arrays.asList(-3, 1, 2), Arrays.asList(-3, -1, 4))), Arguments.of(new int[] {1, 2, 3, 4, 5}, 50, new ArrayList<>()));
    }

    private static Stream<Arguments> twoPointerTestProvider() {
        return Stream.of(Arguments.of(new int[] {0, -1, 2, -3, 1}, 0, Arrays.asList(Arrays.asList(-3, 1, 2), Arrays.asList(-1, 0, 1))), Arguments.of(new int[] {-5, -4, -3, -2, -1}, -10, Arrays.asList(Arrays.asList(-5, -4, -1), Arrays.asList(-5, -3, -2))));
    }

    private static Stream<Arguments> hashMapTestProvider() {
        return Stream.of(Arguments.of(new int[] {1, 2, -1, -4, 3, 0}, 2, Arrays.asList(Arrays.asList(-1, 0, 3), Arrays.asList(-1, 1, 2))), Arguments.of(new int[] {5, 7, 9, 11}, 10, new ArrayList<>()), Arguments.of(new int[] {}, 0, new ArrayList<>()));
    }
}
