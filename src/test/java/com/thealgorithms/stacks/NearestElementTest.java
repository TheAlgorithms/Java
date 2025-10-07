package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NearestElementTest {

    @ParameterizedTest
    @MethodSource("provideGreaterToRightTestCases")
    void testNearestGreaterToRight(int[] input, int[] expected) {
        assertArrayEquals(expected, NearestElement.nearestGreaterToRight(input));
    }

    static Stream<Arguments> provideGreaterToRightTestCases() {
        return Stream.of(Arguments.of(new int[] {4, 5, 2, 10, 8}, new int[] {5, 10, 10, -1, -1}), Arguments.of(new int[] {5}, new int[] {-1}), Arguments.of(new int[] {}, new int[] {}));
    }

    @ParameterizedTest
    @MethodSource("provideGreaterToLeftTestCases")
    void testNearestGreaterToLeft(int[] input, int[] expected) {
        assertArrayEquals(expected, NearestElement.nearestGreaterToLeft(input));
    }

    static Stream<Arguments> provideGreaterToLeftTestCases() {
        return Stream.of(Arguments.of(new int[] {4, 5, 2, 10, 8}, new int[] {-1, -1, 5, -1, 10}), Arguments.of(new int[] {5}, new int[] {-1}), Arguments.of(new int[] {}, new int[] {}));
    }

    @ParameterizedTest
    @MethodSource("provideSmallerToRightTestCases")
    void testNearestSmallerToRight(int[] input, int[] expected) {
        assertArrayEquals(expected, NearestElement.nearestSmallerToRight(input));
    }

    static Stream<Arguments> provideSmallerToRightTestCases() {
        return Stream.of(Arguments.of(new int[] {4, 5, 2, 10, 8}, new int[] {2, 2, -1, 8, -1}), Arguments.of(new int[] {5}, new int[] {-1}), Arguments.of(new int[] {}, new int[] {}));
    }

    @ParameterizedTest
    @MethodSource("provideSmallerToLeftTestCases")
    void testNearestSmallerToLeft(int[] input, int[] expected) {
        assertArrayEquals(expected, NearestElement.nearestSmallerToLeft(input));
    }

    static Stream<Arguments> provideSmallerToLeftTestCases() {
        return Stream.of(Arguments.of(new int[] {4, 5, 2, 10, 8}, new int[] {-1, 4, -1, 2, 2}), Arguments.of(new int[] {5}, new int[] {-1}), Arguments.of(new int[] {}, new int[] {}));
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> NearestElement.nearestGreaterToRight(null));
        assertThrows(IllegalArgumentException.class, () -> NearestElement.nearestGreaterToLeft(null));
        assertThrows(IllegalArgumentException.class, () -> NearestElement.nearestSmallerToRight(null));
        assertThrows(IllegalArgumentException.class, () -> NearestElement.nearestSmallerToLeft(null));
    }
}