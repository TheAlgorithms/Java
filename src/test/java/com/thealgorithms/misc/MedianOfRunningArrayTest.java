package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

/**
 * Test case for Two sum Problem.
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public class MedianOfRunningArrayTest {
    private static final String EXCEPTION_MESSAGE = "Enter at least 1 element, Median of empty list is not defined!";

    @Test
    public void testWhenInvalidInoutProvidedShouldThrowException() {
        MedianOfRunningArray stream = new MedianOfRunningArray();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> stream.median());
        assertEquals(exception.getMessage(), EXCEPTION_MESSAGE);
    }

    @Test
    public void testWithNegativeValues() {
        MedianOfRunningArray stream = new MedianOfRunningArray();
        stream.insert(-1);
        assertEquals(-1, stream.median());
        stream.insert(-2);
        assertEquals(-1.5, stream.median());
        stream.insert(-3);
        assertEquals(-2, stream.median());
    }

    @Test
    public void testWithSingleValues() {
        MedianOfRunningArray stream = new MedianOfRunningArray();
        stream.insert(-1);
        assertEquals(-1, stream.median());
    }

    @Test
    public void testWithRandomValues() {
        MedianOfRunningArray stream = new MedianOfRunningArray();
        stream.insert(10);
        assertEquals(10.0, stream.median());

        stream.insert(5);
        assertEquals(7.5, stream.median());

        stream.insert(20);
        assertEquals(10.0, stream.median());

        stream.insert(15);
        assertEquals(12.5, stream.median());

        stream.insert(25);
        assertEquals(15.0, stream.median());

        stream.insert(30);
        assertEquals(17.5, stream.median());

        stream.insert(35);
        assertEquals(20.0, stream.median());

        stream.insert(1);
        assertEquals(17.5, stream.median());
    }

    @Test
    public void testWithNegativeAndPositiveValues() {
        MedianOfRunningArray stream = new MedianOfRunningArray();
        stream.insert(-1);
        assertEquals(-1, stream.median());
        stream.insert(2);
        assertEquals(0.5, stream.median());
        stream.insert(-3);
        assertEquals(-1, stream.median());
    }

    @Test
    public void testWithDuplicateValues() {
        MedianOfRunningArray stream = new MedianOfRunningArray();
        stream.insert(-1);
        assertEquals(-1, stream.median());
        stream.insert(-1);
        assertEquals(-1, stream.median());
        stream.insert(-1);
        assertEquals(-1, stream.median());
    }

    @Test
    public void testWithLargeValues() {
        MedianOfRunningArray stream = new MedianOfRunningArray();
        stream.insert(1000000);
        assertEquals(1000000, stream.median());
        stream.insert(12000);
        assertEquals(506000, stream.median());
        stream.insert(15000000);
        assertEquals(1000000, stream.median());
        stream.insert(2300000);
        assertEquals(1650000.00, stream.median());
    }

    @Test
    public void testWithLargeCountOfValues() {
        MedianOfRunningArray stream = new MedianOfRunningArray();
        for (int i = 1; i <= 1000; i++) stream.insert(i);
        assertEquals(500.5, stream.median());
    }
}