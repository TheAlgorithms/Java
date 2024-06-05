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

    public static class TestCase {
        public TestCase(final int[] inInputArray, final int inSecondMin, final int inSecondMax) {
            inputArray = inInputArray;
            secondMin = inSecondMin;
            secondMax = inSecondMax;
        }
        final int[] inputArray;
        final int secondMin;
        final int secondMax;
    }

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
    @MethodSource("inputStream")
    void numberTests(final TestCase tc) {
        Assertions.assertEquals(tc.secondMax, SecondMinMax.findSecondMax(tc.inputArray));
        Assertions.assertEquals(tc.secondMin, SecondMinMax.findSecondMin(tc.inputArray));
    }

    private static Stream<Arguments> inputStream() {
        return Stream.of(Arguments.of(new TestCase(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 2, 9)), Arguments.of(new TestCase(new int[] {5, 4, 5, 5, 5}, 5, 4)), Arguments.of(new TestCase(new int[] {-1, 0}, 0, -1)),
            Arguments.of(new TestCase(new int[] {-10, -9, -8, -7, -6, -5, -4, -3, -2, -1}, -9, -2)), Arguments.of(new TestCase(new int[] {3, -2, 3, 9, -4, -4, 8}, -2, 8)));
    }
}
