package com.thealgorithms.datastructures.crdt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PNCounterTest {

    @Test
    public void testIncrement() {
        PNCounter counter = new PNCounter(0, 3);
        counter.increment();
        assertEquals(1, counter.value());
    }

    @Test
    public void testDecrement() {
        PNCounter counter = new PNCounter(0, 3);
        counter.decrement();
        assertEquals(-1, counter.value());
    }

    @Test
    public void testIncrementAndDecrement() {
        PNCounter counter = new PNCounter(0, 3);
        counter.increment();
        counter.increment();
        counter.decrement();
        assertEquals(1, counter.value());
    }

    @Test
    public void testCompare() {
        PNCounter counter1 = new PNCounter(0, 3);
        counter1.increment();
        PNCounter counter2 = new PNCounter(1, 3);
        assertTrue(counter1.compare(counter2));
        counter2.increment();
        assertTrue(counter2.compare(counter1));
        counter1.decrement();
        assertFalse(counter1.compare(counter2));
    }

    @Test
    public void testMerge() {
        PNCounter counter1 = new PNCounter(0, 3);
        counter1.increment();
        counter1.increment();
        PNCounter counter2 = new PNCounter(1, 3);
        counter2.increment();
        counter1.merge(counter2);
        assertEquals(3, counter1.value());
    }
}
