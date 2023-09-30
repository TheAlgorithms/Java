package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SecondMinMaxTest {

    private static final String EXP_MSG_ARR_IS_EMPTY = "Cant find Second Maximum / Minimum for empty array";
    private static final String EXP_MSG_ARR_SINGLE_ELE = "Cant find Second Maximum / Minimum for single element";
    private static final String EXP_MSG_ARR_SAME_ELE = "Cant find Second Maximum / Minimum in array full of same numbers";

    @Test
    public void testForEmptyInputArray() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> SecondMinMax.findSecondMin(new int[] {}));
        assertEquals(exception.getMessage(), EXP_MSG_ARR_IS_EMPTY);
    }

    @Test
    public void testForArrayWithSingleElement() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> SecondMinMax.findSecondMax(new int[] {1}));
        assertEquals(exception.getMessage(), EXP_MSG_ARR_SINGLE_ELE);
    }

    @Test
    public void testForArrayWithSameElements() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> SecondMinMax.findSecondMin(new int[] {1, 1, 1, 1}));
        assertEquals(exception.getMessage(), EXP_MSG_ARR_SAME_ELE);
    }

    @Test
    public void testForValidOutput() {
        assertEquals(2, SecondMinMax.findSecondMin(new int[] {1, 2}));
        assertEquals(1, SecondMinMax.findSecondMax(new int[] {1, 2}));
        assertEquals(2, SecondMinMax.findSecondMin(new int[] {10, 1, 2, 4, 6, 3, 5, 7, 3, 2, 5, 7, 4, 2, 1, 212, 343, 565, 2232, 65565}));
        assertEquals(2232, SecondMinMax.findSecondMax(new int[] {10, 1, 2, 4, 6, 3, 5, 7, 3, 2, 5, 7, 4, 2, 1, 212, 343, 565, 2232, 65565}));
    }
}
