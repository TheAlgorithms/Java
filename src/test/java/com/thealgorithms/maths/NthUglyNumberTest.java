package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

public class NthUglyNumberTest {
    @Test
    public void testGetWithNewObject() {
        HashMap<Integer, Long> testCases = new HashMap<>();
        testCases.put(0, 1L);
        testCases.put(1, 2L);
        testCases.put(2, 3L);
        testCases.put(3, 4L);
        testCases.put(4, 5L);
        testCases.put(5, 6L);
        testCases.put(9, 12L);
        testCases.put(19, 36L);
        testCases.put(52, 270L);
        testCases.put(1078, 84934656L);
        testCases.put(1963, 6973568802L);

        for (final var tc : testCases.entrySet()) {
            var uglyNumbers = new NthUglyNumber(new int[] {2, 3, 5});
            assertEquals(uglyNumbers.get(tc.getKey()), tc.getValue());

            var otherUglyNumbers = new NthUglyNumber(new int[] {5, 25, 6, 2, 3, 5});
            assertEquals(otherUglyNumbers.get(tc.getKey()), tc.getValue());
        }
    }

    @Test
    public void testGetWithSameObject() {
        HashMap<Integer, Long> testCases = new HashMap<>();
        testCases.put(0, 1L);
        testCases.put(1, 2L);
        testCases.put(2, 3L);
        testCases.put(3, 4L);
        testCases.put(4, 5L);
        testCases.put(5, 6L);
        testCases.put(6, 7L);
        testCases.put(1499, 1984500L);
        testCases.put(1572, 2449440L);
        testCases.put(1658, 3072000L);
        testCases.put(6625, 4300800000L);

        var uglyNumbers = new NthUglyNumber(new int[] {7, 2, 5, 3});
        for (final var tc : testCases.entrySet()) {
            assertEquals(uglyNumbers.get(tc.getKey()), tc.getValue());
        }

        assertEquals(uglyNumbers.get(999), 385875);
    }

    @Test
    public void testGetWithBase1() {
        var uglyNumbers = new NthUglyNumber(new int[] {1});
        assertEquals(uglyNumbers.get(10), 1);
    }

    @Test
    public void testGetWithBase2() {
        var uglyNumbers = new NthUglyNumber(new int[] {2});
        assertEquals(uglyNumbers.get(5), 32);
    }

    @Test
    public void testGetThrowsAnErrorForNegativeInput() {
        var uglyNumbers = new NthUglyNumber(new int[] {1, 2});
        assertThrows(IllegalArgumentException.class, () -> uglyNumbers.get(-1));
    }

    @Test
    public void testConstructorThrowsAnErrorForEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> new NthUglyNumber(new int[] {}));
    }
}
