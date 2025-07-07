package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test case for Median Of Running Array Problem.
 * @author Ansh Shah (https://github.com/govardhanshah456)
 */

public class MedianOfRunningArrayTest {
    private static final String EXCEPTION_MESSAGE = "Median is undefined for an empty data set.";

    @Test
    public void testWhenInvalidInoutProvidedShouldThrowException() {
        var stream = new MedianOfRunningArrayInteger();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, stream::getMedian);
        assertEquals(exception.getMessage(), EXCEPTION_MESSAGE);
    }

    @Test
    public void testWithNegativeValues() {
        var stream = new MedianOfRunningArrayInteger();
        stream.insert(-1);
        assertEquals(-1, stream.getMedian());
        stream.insert(-2);
        assertEquals(-1, stream.getMedian());
        stream.insert(-3);
        assertEquals(-2, stream.getMedian());
    }

    @Test
    public void testWithSingleValues() {
        var stream = new MedianOfRunningArrayInteger();
        stream.insert(-1);
        assertEquals(-1, stream.getMedian());
    }

    @Test
    public void testWithRandomValues() {
        var stream = new MedianOfRunningArrayInteger();
        stream.insert(10);
        assertEquals(10, stream.getMedian());

        stream.insert(5);
        assertEquals(7, stream.getMedian());

        stream.insert(20);
        assertEquals(10, stream.getMedian());

        stream.insert(15);
        assertEquals(12, stream.getMedian());

        stream.insert(25);
        assertEquals(15, stream.getMedian());

        stream.insert(30);
        assertEquals(17, stream.getMedian());

        stream.insert(35);
        assertEquals(20, stream.getMedian());

        stream.insert(1);
        assertEquals(17, stream.getMedian());
    }

    @Test
    public void testWithNegativeAndPositiveValues() {
        var stream = new MedianOfRunningArrayInteger();
        stream.insert(-1);
        assertEquals(-1, stream.getMedian());
        stream.insert(2);
        assertEquals(0, stream.getMedian());
        stream.insert(-3);
        assertEquals(-1, stream.getMedian());
    }

    @Test
    public void testWithDuplicateValues() {
        var stream = new MedianOfRunningArrayInteger();
        stream.insert(-1);
        assertEquals(-1, stream.getMedian());
        stream.insert(-1);
        assertEquals(-1, stream.getMedian());
        stream.insert(-1);
        assertEquals(-1, stream.getMedian());
    }

    @Test
    public void testWithDuplicateValuesB() {
        var stream = new MedianOfRunningArrayInteger();
        stream.insert(10);
        stream.insert(20);
        stream.insert(10);
        stream.insert(10);
        stream.insert(20);
        stream.insert(0);
        stream.insert(50);
        assertEquals(10, stream.getMedian());
    }

    @Test
    public void testWithLargeValues() {
        var stream = new MedianOfRunningArrayInteger();
        stream.insert(1000000);
        assertEquals(1000000, stream.getMedian());
        stream.insert(12000);
        assertEquals(506000, stream.getMedian());
        stream.insert(15000000);
        assertEquals(1000000, stream.getMedian());
        stream.insert(2300000);
        assertEquals(1650000, stream.getMedian());
    }

    @Test
    public void testWithLargeCountOfValues() {
        var stream = new MedianOfRunningArrayInteger();
        for (int i = 1; i <= 1000; i++) {
            stream.insert(i);
        }
        assertEquals(500, stream.getMedian());
    }

    @Test
    public void testWithThreeValuesInDescendingOrder() {
        var stream = new MedianOfRunningArrayInteger();
        stream.insert(30);
        stream.insert(20);
        stream.insert(10);
        assertEquals(20, stream.getMedian());
    }

    @Test
    public void testWithThreeValuesInOrder() {
        var stream = new MedianOfRunningArrayInteger();
        stream.insert(10);
        stream.insert(20);
        stream.insert(30);
        assertEquals(20, stream.getMedian());
    }

    @Test
    public void testWithThreeValuesNotInOrderA() {
        var stream = new MedianOfRunningArrayInteger();
        stream.insert(30);
        stream.insert(10);
        stream.insert(20);
        assertEquals(20, stream.getMedian());
    }

    @Test
    public void testWithThreeValuesNotInOrderB() {
        var stream = new MedianOfRunningArrayInteger();
        stream.insert(20);
        stream.insert(10);
        stream.insert(30);
        assertEquals(20, stream.getMedian());
    }

    @Test
    public void testWithFloatValues() {
        var stream = new MedianOfRunningArrayFloat();
        stream.insert(20.0f);
        assertEquals(20.0f, stream.getMedian());
        stream.insert(10.5f);
        assertEquals(15.25f, stream.getMedian());
        stream.insert(30.0f);
        assertEquals(20.0f, stream.getMedian());
    }

    @Test
    public void testWithByteValues() {
        var stream = new MedianOfRunningArrayByte();
        stream.insert((byte) 120);
        assertEquals((byte) 120, stream.getMedian());
        stream.insert((byte) -120);
        assertEquals((byte) 0, stream.getMedian());
        stream.insert((byte) 127);
        assertEquals((byte) 120, stream.getMedian());
    }

    @Test
    public void testWithLongValues() {
        var stream = new MedianOfRunningArrayLong();
        stream.insert(120000000L);
        assertEquals(120000000L, stream.getMedian());
        stream.insert(92233720368547757L);
        assertEquals(46116860244273878L, stream.getMedian());
    }

    @Test
    public void testWithDoubleValues() {
        var stream = new MedianOfRunningArrayDouble();
        stream.insert(12345.67891);
        assertEquals(12345.67891, stream.getMedian());
        stream.insert(23456789.98);
        assertEquals(11734567.83, stream.getMedian(), .01);
    }
}
