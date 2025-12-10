package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class AbsoluteValueTest {

    @Test
    void testGetAbsValue() {
        Stream.generate(() -> ThreadLocalRandom.current().nextInt()).limit(1000).forEach(number -> assertEquals(Math.abs(number), AbsoluteValue.getAbsValue(number)));
    }

    @Test
    void testZero() {
        assertEquals(0, AbsoluteValue.getAbsValue(0));
    }

    @Test
    void testPositiveNumbers() {
        assertEquals(5, AbsoluteValue.getAbsValue(5));
        assertEquals(123456, AbsoluteValue.getAbsValue(123456));
        assertEquals(Integer.MAX_VALUE, AbsoluteValue.getAbsValue(Integer.MAX_VALUE));
    }

    @Test
    void testNegativeNumbers() {
        assertEquals(5, AbsoluteValue.getAbsValue(-5));
        assertEquals(123456, AbsoluteValue.getAbsValue(-123456));
        assertEquals(Integer.MAX_VALUE, AbsoluteValue.getAbsValue(-Integer.MAX_VALUE));
    }

    @Test
    void testMinIntEdgeCase() {
        assertEquals(Integer.MIN_VALUE, AbsoluteValue.getAbsValue(Integer.MIN_VALUE));
    }
}
