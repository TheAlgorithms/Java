package com.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class RepeatElementTest {

    @Test
    void testRepeatElement() {
        int[] arrTest1 = {1, 2, 2, 10, 4, 6, 7, 6, 6, 10, 5};
        Assertions.assertArrayEquals(new int[]{1, 4, 5, 7}, RepeatElement.findRepeatElement(arrTest1, 1));
        Assertions.assertArrayEquals(new int[]{2, 10}, RepeatElement.findRepeatElement(arrTest1, 2));
        Assertions.assertArrayEquals(new int[]{6}, RepeatElement.findRepeatElement(arrTest1, 3));
        Assertions.assertArrayEquals(new int[]{}, RepeatElement.findRepeatElement(arrTest1, Integer.MAX_VALUE));


        int[] arrTest3 = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        Assertions.assertArrayEquals(new int[]{}, RepeatElement.findRepeatElement(arrTest3, 1));
        Assertions.assertArrayEquals(new int[]{1}, RepeatElement.findRepeatElement(arrTest3, 9));
        Assertions.assertArrayEquals(new int[]{}, RepeatElement.findRepeatElement(arrTest3, Integer.MAX_VALUE));
    }

    @Test
    void testRepeatElementWithEmptyArray() {
        int[] arrTest2 = {};
        Assertions.assertArrayEquals(new int[]{}, RepeatElement.findRepeatElement(arrTest2, 1));
        Assertions.assertArrayEquals(new int[]{}, RepeatElement.findRepeatElement(arrTest2, Integer.MAX_VALUE));
    }

    @Test
    void testRepeatElementThrowException() {
        try {
            RepeatElement.findRepeatElement(null, 1);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), IllegalArgumentException.class);
            Assertions.assertEquals(e.getMessage(), "You can't pass null arrays or times values minor than 0 as parameter.");
        }
        try {
            RepeatElement.findRepeatElement(new int[]{1, 2, 3}, -1);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), IllegalArgumentException.class);
            Assertions.assertEquals(e.getMessage(), "You can't pass null arrays or times values minor than 0 as parameter.");
        }
        try {
            RepeatElement.findRepeatElement(null, -1);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), IllegalArgumentException.class);
            Assertions.assertEquals(e.getMessage(), "You can't pass null arrays or times values minor than 0 as parameter.");
        }
    }
}
