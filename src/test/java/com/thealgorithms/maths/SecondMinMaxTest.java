package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SecondMinMaxTest {

    private static final String EXP_MSG_ARR_LEN_LESS_2 = "Input array must have length of at least two";
    private static final String EXP_MSG_ARR_SAME_ELE = "Input array should have at least 2 distinct elements";

    @Test
    public void testForEmptyInputArray() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> SecondMinMax.findSecondMin(new int[] {}));
        assertEquals(exception.getMessage(), EXP_MSG_ARR_LEN_LESS_2);
    }

    @Test
    public void testForArrayWithSingleElement() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> SecondMinMax.findSecondMax(new int[] {1}));
        assertEquals(exception.getMessage(), EXP_MSG_ARR_LEN_LESS_2);
    }

    @Test
    public void testForArrayWithSameElements() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> SecondMinMax.findSecondMin(new int[] {1, 1, 1, 1}));
        assertEquals(exception.getMessage(), EXP_MSG_ARR_SAME_ELE);
    }

    @ParameterizedTest
    @MethodSource("inputStreamMin")
    void numberTestsMin(int expected, int[] input) {
        Assertions.assertEquals(expected, SecondMinMax.findSecondMin(input));
    }

    private static Stream<Arguments> inputStreamMin() {
        return Stream.of(Arguments.of(2, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), Arguments.of(5, new int[] {5, 4, 5, 5, 5}), Arguments.of(0, new int[] {-1, 0}), Arguments.of(-9, new int[] {-10, -9, -8, -7, -6, -5, -4, -3, -2, -1}), Arguments.of(-2, new int[] {3, -2, 3, 9, -4, -4, 8}));
    }

    @ParameterizedTest
    @MethodSource("inputStreamMax")
    void numberTestsMax(int expected, int[] input) {
        Assertions.assertEquals(expected, SecondMinMax.findSecondMax(input));
    }

    private static Stream<Arguments> inputStreamMax() {
        return Stream.of(Arguments.of(9, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), Arguments.of(4, new int[] {5, 5, 4, 5, 5}), Arguments.of(-1, new int[] {-1, 0}), Arguments.of(-2, new int[] {-10, -9, -8, -7, -6, -5, -4, -3, -2, -1}), Arguments.of(8, new int[] {3, -2, 3, 9, -4, -4, 8}));
    }
}
